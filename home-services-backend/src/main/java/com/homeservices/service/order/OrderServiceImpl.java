package com.homeservices.service.order;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.custom_exceptions.ResourceNotFoundException;
import com.homeservices.dao.OrderRepository;
import com.homeservices.dao.PartnerRepository;
import com.homeservices.dao.ServiceRepository;
import com.homeservices.dao.UserRepository;
import com.homeservices.dto.request.OrderRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.entities.Order;
import com.homeservices.entities.Partner;
import com.homeservices.entities.User;
import com.homeservices.entities.ProvidedService;
import com.homeservices.utils.OrderStatus;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepo;
	private final PartnerRepository partnerRepo;
	private final UserRepository userRepo;
	private final ServiceRepository serviceRepo;
	private final ModelMapper modelMapper;
	
	@Override
	public ApiResponse createOrder(OrderRequestDto dto,Long userId, Long serviceId) {
		ProvidedService service =serviceRepo.findById(serviceId).orElseThrow(()-> new ResourceNotFoundException("Service Not Found"));
		User user =userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
		Order order = modelMapper.map(dto, Order.class);
		order.setTotalCost(service.getPrice());		

		order.setOrderStatus(OrderStatus.PENDING);
		order.setServiceDate(dto.serviceDate());
		order.setServiceTime(dto.serviceTime());
		user.getOrders().add(order);
		order.setService(service);
		orderRepo.save(order);
		return new ApiResponse("Order Created Successfully");
	}

	@Override
	public Order getOrderById(Long orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Found"));
		return order;
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
		List<Order> orders = user.getOrders();
		if(orders.isEmpty()) {
			throw new ResourceNotFoundException("No Orders Found");
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersByPartnerId(Long partnerId) {
		Partner partner = partnerRepo.findById(partnerId).orElseThrow(()-> new ResourceNotFoundException("Partner Not Found"));
		List<Order> orders = partner.getMyOrders();
		if(orders.isEmpty()) {
			throw new ResourceNotFoundException("Orders Not Found");
		}
		return orders;
	}

	@Override
	public ApiResponse updateOrderStatus(Long orderId) {
		Order order = orderRepo.findById(orderId).
				orElseThrow(()-> new ResourceNotFoundException("Order Not Found"));
		
		if(order.getOrderStatus().equals(OrderStatus.PENDING)) {
			order.setOrderStatus(OrderStatus.CONFIRMED);
		}else if(order.getOrderStatus().equals(OrderStatus.CONFIRMED)) {
			order.setOrderStatus(OrderStatus.INPROGRESS);
		}else if(order.getOrderStatus().equals(OrderStatus.INPROGRESS)) {
			order.setOrderStatus(OrderStatus.COMPLETED);
			order.setCompletionDate(LocalDate.now());
		}
		
		orderRepo.save(order);
		return new ApiResponse("Order Status Updated");
	}

	@Override
	public ApiResponse cancelOrderById(Long orderId) {
		Order order = orderRepo.findById(orderId).
				orElseThrow(()-> new ResourceNotFoundException("Order Not Found"));
		order.setOrderStatus(OrderStatus.CANCELLED);
		orderRepo.save(order);
		return new ApiResponse("Order Cancelled");
	}
	

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders =orderRepo.findAll();
		if(orders.isEmpty()) {
			throw new ResourceNotFoundException("Orders Not Found");
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersByStatus(String status) {
		OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
		List<Order> orders=orderRepo.findByOrderStatus(orderStatus);
		if(orders.isEmpty()) {
			throw new ResourceNotFoundException("Orders Not Found By Status : "+status);
		}
		return orders;
	}
	
}

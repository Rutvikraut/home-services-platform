package com.homeservices.service.order;

import java.util.List;
import java.util.Optional;

import com.homeservices.dto.request.OrderRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.entities.Order;

public interface OrderService {
	ApiResponse createOrder(OrderRequestDto dto,Long userId, Long serviceId);

	Order getOrderById(Long orderId);

	List<Order> getOrdersByUserId(Long userId);

	List<Order> getOrdersByPartnerId(Long partnerId);

	ApiResponse updateOrderStatus(Long orderId);

	ApiResponse cancelOrderById(Long orderId);

	List<Order> getAllOrders();

	List<Order> getOrdersByStatus(String status);
	
}

package com.homeservices.service.order;

import java.net.URI;

import org.springframework.stereotype.Service;

import com.homeservices.dao.OrderRepository;
import com.homeservices.dto.request.OrderRequestDto;
import com.homeservices.entities.Order;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository orderRepo;

	public Order createOrder(OrderRequestDto dto) {
		return null;
	}
	
}

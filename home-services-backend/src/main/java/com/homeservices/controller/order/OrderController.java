package com.homeservices.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.request.OrderRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.entities.Order;
import com.homeservices.service.order.OrderService;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
	private OrderService orderService;
	
//	POST   /api/orders                           → Create a new order
	@PostMapping("/user/{userId}/service/{serviceId}")
	public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequestDto dto,@PathVariable Long userId,@PathVariable Long serviceId){
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dto,userId,serviceId));
	}
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(){
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status){
		return ResponseEntity.ok(orderService.getOrdersByStatus(status));
	}
	
//	GET    /api/orders/{id}                      → Get order by ID
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderId){
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}
//	GET    /api/orders/user/{userId}             → Get all orders by user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
	}
//	GET    /api/orders/partner/{partnerId}       → Get all orders by partner
	@GetMapping("/partner/{partnerId}")
	public ResponseEntity<List<Order>> getOrdersByPartnerId(@PathVariable Long partnerId){
		return ResponseEntity.ok(orderService.getOrdersByPartnerId(partnerId));
	}
//	PUT    /api/orders/{id}/status               → Update order status
	@PutMapping("/{orderId}/status")
	public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable Long orderId){
		return ResponseEntity.ok(orderService.updateOrderStatus(orderId));
	}
//	DELETE /api/orders/{id}                      → Cancel order
	@DeleteMapping("/{orderId}")
	public ResponseEntity<ApiResponse> cancelOrder(@PathVariable Long orderId){
		return ResponseEntity.ok(orderService.cancelOrderById(orderId));
	}


}

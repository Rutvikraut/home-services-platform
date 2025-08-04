package com.homeservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeservices.entities.Order;
import com.homeservices.entities.Partner;
import com.homeservices.entities.User;

import java.util.List;
import com.homeservices.utils.OrderStatus;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//	List<Order> findByPartner(Partner partner);
//	List<Order> findByUser(User user);
	List<Order> findByOrderStatus(OrderStatus orderStatus);
}

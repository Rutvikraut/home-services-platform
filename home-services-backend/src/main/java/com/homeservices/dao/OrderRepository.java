package com.homeservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeservices.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

package com.homeservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeservices.entities.Booking;
import com.homeservices.entities.Partner;
import com.homeservices.entities.User;

import java.util.List;
import com.homeservices.utils.BookingStatus;



@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
//	List<Order> findByPartner(Partner partner);
//	List<Order> findByUser(User user);
	List<Booking> findByBookingStatus(BookingStatus orderStatus);
}

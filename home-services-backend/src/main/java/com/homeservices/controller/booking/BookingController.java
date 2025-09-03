package com.homeservices.controller.booking;

import java.util.List;


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

import com.homeservices.dto.request.BookingRequestDto;
import com.homeservices.dto.response.AllBookingResponseDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.BookingResponse;
import com.homeservices.entities.Booking;
import com.homeservices.service.booking.BookingService;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {
	private BookingService bookingService;
	
	@PostMapping("/user/{userId}/service")
	public ResponseEntity<ApiResponse> createBooking(@RequestBody BookingRequestDto dto,@PathVariable Long userId){
		System.out.println(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(dto,userId));
	}
	@GetMapping
	public ResponseEntity<List<AllBookingResponseDto>> getAllBookings(){
		return ResponseEntity.ok(bookingService.getAllBookings());
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable String status){
		return ResponseEntity.ok(bookingService.getBookingsByStatus(status));
	}
	
	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long bookingId){
		return ResponseEntity.ok(bookingService.getBookingById(bookingId));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<BookingResponse>> getBookingsByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
	}

	@GetMapping("/partner/{partnerId}")
	public ResponseEntity<List<Booking>> getBookingsByPartnerId(@PathVariable Long partnerId){
		return ResponseEntity.ok(bookingService.getBookingsByPartnerId(partnerId));
	}

	@PutMapping("/{bookingId}/status")
	public ResponseEntity<ApiResponse> updateBookingStatus(@PathVariable Long bookingId){
		return ResponseEntity.ok(bookingService.updateBookingStatus(bookingId));
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<ApiResponse> cancelBooking(@PathVariable Long bookingId){
		return ResponseEntity.ok(bookingService.cancelBookingById(bookingId));
	}


}

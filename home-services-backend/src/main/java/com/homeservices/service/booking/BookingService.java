package com.homeservices.service.booking;

import java.util.List;
import com.homeservices.dto.request.BookingRequestDto;
import com.homeservices.dto.response.AllBookingResponseDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.BookingResponse;
import com.homeservices.entities.Booking;

public interface BookingService {
	ApiResponse createBooking(BookingRequestDto dto,Long userId);

	BookingResponse getBookingById(Long bookingId);

	List<BookingResponse> getBookingsByUserId(Long userId);

	List<Booking> getBookingsByPartnerId(Long partnerId);

	ApiResponse updateBookingStatus(Long BookingId);

	ApiResponse cancelBookingById(Long BookingId);

	List<AllBookingResponseDto> getAllBookings();

	List<Booking> getBookingsByStatus(String status);
	
}

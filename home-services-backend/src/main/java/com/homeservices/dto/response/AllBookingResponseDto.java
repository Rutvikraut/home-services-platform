package com.homeservices.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.homeservices.entities.ProvidedService;
import com.homeservices.entities.UserAddress;
import com.homeservices.utils.BookingStatus;

import lombok.Data;

@Data
public class AllBookingResponseDto {
	public Long id;
	public LocalDate serviceDate;
	public LocalTime serviceTime;
	public LocalDate completionDate;
	public BookingStatus bookingStatus;
	public Double totalCost;
	public String service;
	public Long categoryId;
//	public String address;
}

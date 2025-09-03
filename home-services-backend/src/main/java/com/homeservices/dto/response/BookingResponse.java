package com.homeservices.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.homeservices.entities.ProvidedService;
import com.homeservices.entities.UserAddress;
import com.homeservices.utils.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookingResponse {
	public Long id;
	public LocalDate serviceDate;
	public LocalTime serviceTime;
	public LocalDate completionDate;
	public BookingStatus orderStatus;
	public Double totalCost;
	public String service;
	public String address;
}

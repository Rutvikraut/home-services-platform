package com.homeservices.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.homeservices.utils.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PartnerBookingDTO {
	public Long id;
	public LocalDate serviceDate;
	public LocalTime serviceTime;
	public LocalDate completionDate;
	public BookingStatus orderStatus;
	public Double totalCost;
	private String service;
	private String address;

}

package com.homeservices.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeservices.utils.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Bookings")
@NoArgsConstructor
@Data
public class Booking extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "service_date")
	private LocalDate serviceDate;

	@Column(name="service_time")
	private LocalTime serviceTime;

	@Column(name = "completion_date")
	private LocalDate completionDate;

	@Column(name = "booking_status")
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
	@Column(name = "total_cost")

	private Double totalCost;
	@ManyToOne
	@JoinColumn(name="service_id")
	@JsonIgnore
	private ProvidedService service;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	@JsonIgnore
	private UserAddress address;

}

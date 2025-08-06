package com.homeservices.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerResponseDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate birthDate;
	private int experience;
	private float rating;
	private int noOfOrders;
	private double totalEarning;
	private PartnerCategoryDTO category;
	private PartnerAddressDTO myAddress;

}

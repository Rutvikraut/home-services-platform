package com.homeservices.dto.request;

import java.time.LocalDate;

import com.homeservices.dto.response.PartnerAddressDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePartnerDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private LocalDate birthDate;
	private int experience;
	private Long categoryId;
	private PartnerAddressDTO myAddress;

}

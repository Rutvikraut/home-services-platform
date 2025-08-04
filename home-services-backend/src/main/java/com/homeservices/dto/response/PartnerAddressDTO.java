package com.homeservices.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerAddressDTO {
	private String address;
	
	private String pincode;

	private String city;

	private String state;
	
	private String country;
	

}

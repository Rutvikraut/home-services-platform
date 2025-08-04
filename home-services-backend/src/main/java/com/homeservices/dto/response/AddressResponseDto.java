package com.homeservices.dto.response;

import lombok.Data;

@Data
public class AddressResponseDto {


	private String address;
	private String pincode;
	private String city;
	private String state;
	private String country;
}

package com.homeservices.dto.request;

import lombok.Data;

@Data
public class ProvidedServiceRequestDto {
	private String name;
	private String description;
	private double price;
}

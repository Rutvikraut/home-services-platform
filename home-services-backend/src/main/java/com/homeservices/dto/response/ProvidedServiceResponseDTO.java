package com.homeservices.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvidedServiceResponseDTO {	
	private int id;
	private String name;
	private String description;
	private double price;
}

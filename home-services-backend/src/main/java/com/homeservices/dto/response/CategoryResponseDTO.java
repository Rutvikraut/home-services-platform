package com.homeservices.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDTO {
	private Long id;
	private String name;
	private String description;
	private int noOfPartners;
}

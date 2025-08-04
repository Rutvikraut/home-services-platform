package com.homeservices.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserResponseDto {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String profileImg;
	private LocalDate birthDate;
}

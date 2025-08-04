package com.homeservices.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UpdateUserDto {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private String profileImg;
	private LocalDate birthDate;

}

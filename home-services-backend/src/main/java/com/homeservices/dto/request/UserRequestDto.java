package com.homeservices.dto.request;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class UserRequestDto {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private LocalDate birthDate;

}

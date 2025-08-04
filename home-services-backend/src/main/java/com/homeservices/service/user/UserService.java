package com.homeservices.service.user;

import com.homeservices.dto.request.UpdateUserDto;
import com.homeservices.dto.request.UserLoginDto;
import com.homeservices.dto.request.UserRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.UserResponseDto;

public interface UserService {

	UserResponseDto registerUser(UserRequestDto dto);

	UserResponseDto getUserById(Long id);

	UserResponseDto updateUser(Long id, UpdateUserDto dto);

	ApiResponse deleteUser(Long id);

	UserResponseDto userLogin(UserLoginDto dto);

}

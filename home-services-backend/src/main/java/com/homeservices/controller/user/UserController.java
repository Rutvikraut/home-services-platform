package com.homeservices.controller.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.request.AddressRequestDto;
import com.homeservices.dto.request.UpdateAddressDto;
import com.homeservices.dto.request.UpdatePasswordDto;
import com.homeservices.dto.request.UpdateUserDto;
import com.homeservices.dto.request.UserLoginDto;
import com.homeservices.dto.request.UserRequestDto;
import com.homeservices.dto.response.AddressResponseDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.ChangePasswordDto;
import com.homeservices.dto.response.UserResponseDto;
import com.homeservices.service.user.UserAddressService;
import com.homeservices.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserAddressService userAddressService;


	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(dto));
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> userLogin(@RequestBody UserLoginDto dto) {
		return ResponseEntity.ok(userService.userLogin(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserDto dto, @PathVariable Long id) {
		return ResponseEntity.ok(userService.updateUser(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}
	
	@PutMapping("/{userId}/password")
	public ResponseEntity<ChangePasswordDto> updatePassword(@PathVariable Long userId , @RequestBody UpdatePasswordDto dto){
		return ResponseEntity.ok(userService.updatePassword(userId,dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
		System.out.println(id);
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@PostMapping("/{userId}/addresses")
	public ResponseEntity<AddressResponseDto> addAddress(@PathVariable Long userId, @RequestBody AddressRequestDto dto) {
		AddressResponseDto response = userAddressService.addAddress(userId, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{userid}/addresses")
	public ResponseEntity<List<AddressResponseDto>> getUserAddresses(@PathVariable("userid") Long id) {
		return ResponseEntity.ok(userAddressService.getUserAddresses(id));
	}

	@PutMapping("/{userid}/addresses/{addressid}") 
	public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable("userid") Long userId,
			@RequestBody UpdateAddressDto dto, @PathVariable("addressid") Long addressId) {
		return ResponseEntity.ok(userAddressService.updateAddress(userId, dto, addressId));
	}

	@DeleteMapping("/{userId}/addresses/{addressId}") // delete user address
	public ResponseEntity<ApiResponse> deleteAddressByUserIdAndAddressId(@PathVariable Long userId,
			@PathVariable Long addressId) {
		return ResponseEntity.ok(userAddressService.deleteAddressByUserIdAndAddressId(userId, addressId));
	}

}

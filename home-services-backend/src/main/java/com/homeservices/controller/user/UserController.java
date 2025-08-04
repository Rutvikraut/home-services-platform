package com.homeservices.controller.user;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.homeservices.dto.request.*;
import com.homeservices.dto.response.*;
import com.homeservices.service.user.UserAddressService;
import com.homeservices.service.user.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserAddressService userAddressService;

	// user

	@PostMapping("/register") // Register a new user
	public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(dto));
	}

	@PostMapping("/login") // Login and receive token
	public ResponseEntity<UserResponseDto> userLogin(@RequestBody UserLoginDto dto) {
		return ResponseEntity.ok(userService.userLogin(dto));
	}

	@GetMapping("/{id}") // Get user by ID
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@PutMapping("/{id}") // Update user profile
	public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserDto dto, @PathVariable Long id) {
		return ResponseEntity.ok(userService.updateUser(id, dto));
	}

	@DeleteMapping("/{id}") // Delete user profile
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}

	// user address

	@PostMapping("/{userid}/addresses") // add User Address
	public ResponseEntity<ApiResponse> addAddress(@RequestBody AddressRequestDto dto, @PathVariable("userid") Long id) {
		userAddressService.addAddress(id, dto);
		ApiResponse response = new ApiResponse("address added Successfully");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{userid}/addresses") // get User Address
	public ResponseEntity<List<AddressResponseDto>> getUserAddresses(@PathVariable("userid") Long id) {
		return ResponseEntity.ok(userAddressService.getUserAddresses(id));
	}

	@PutMapping("/{userid}/addresses/{addressid}") // update User Address
	public ResponseEntity<AddressResponseDto> updateAddress(@RequestBody AddressRequestDto dto, @PathVariable("userid") Long userId , @PathVariable("addressid") Long addressId ) {
		return ResponseEntity.ok(userAddressService.updateAddress(userId, dto,addressId));
	}

	@DeleteMapping("/{userId}/addresses/{addressId}") // delete user address
	public ResponseEntity<ApiResponse> deleteAddressByUserIdAndAddressId(@PathVariable Long userId,@PathVariable Long addressId) {
		return ResponseEntity.ok(userAddressService.deleteAddressByUserIdAndAddressId(userId,addressId));
	}

}

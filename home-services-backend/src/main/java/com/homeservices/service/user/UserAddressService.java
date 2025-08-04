package com.homeservices.service.user;

import java.util.List;

import com.homeservices.dto.request.AddressRequestDto;
import com.homeservices.dto.response.AddressResponseDto;
import com.homeservices.dto.response.ApiResponse;

public interface UserAddressService {

	ApiResponse addAddress(Long id, AddressRequestDto dto);

	List<AddressResponseDto> getUserAddresses(Long id);

	AddressResponseDto updateAddress(Long id, AddressRequestDto dto, Long addressId);

	ApiResponse deleteAddressByUserIdAndAddressId(Long userId, Long addressId);

	

	

}

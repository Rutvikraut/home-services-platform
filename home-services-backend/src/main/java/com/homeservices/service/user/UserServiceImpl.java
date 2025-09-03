package com.homeservices.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.custom_exceptions.ApiException;
import com.homeservices.custom_exceptions.ResourceNotFoundException;
import com.homeservices.dao.AppUserRepository;
import com.homeservices.dao.UserRepository;
import com.homeservices.dto.request.UpdatePasswordDto;
import com.homeservices.dto.request.UpdateUserDto;
import com.homeservices.dto.request.UserLoginDto;
import com.homeservices.dto.request.UserRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.ChangePasswordDto;
import com.homeservices.dto.response.UserResponseDto;
import com.homeservices.entities.AppUser;
import com.homeservices.entities.User;
import com.homeservices.entities.UserAddress;
import com.homeservices.utils.Role;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper userMapper;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public UserResponseDto registerUser(UserRequestDto dto) {
		if (userRepository.existsByEmailOrPhone(dto.getEmail(), dto.getPhone())) {
			throw new ApiException("Email id or phone number already registered");
		}
		User newUser = userMapper.map(dto, User.class);

		newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
		newUser.setVerified(false);
		newUser.setDeleted(false);

		User savedUser = userRepository.save(newUser);
		AppUser appUser = AppUser.builder().email(savedUser.getEmail()).password(savedUser.getPassword())
				.role(Role.USER).referenceId(savedUser.getId()).entityType("USER").build();
		appUserRepository.save(appUser);

		return userMapper.map(savedUser, UserResponseDto.class);
	}

	@Override
	public UserResponseDto userLogin(UserLoginDto dto) {
		User findUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("invalid email and password"));
		return userMapper.map(findUser, UserResponseDto.class);
	}

	@Override
	public UserResponseDto updateUser(Long id, UpdateUserDto dto) {
	    User oldUser = userRepository.findByIdAndIsDeletedFalse(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found !!"));

	    if (dto.getFirstName() != null) {
	        oldUser.setFirstName(dto.getFirstName());
	    }
	    if (dto.getLastName() != null) {
	        oldUser.setLastName(dto.getLastName());
	    }
	    if (dto.getEmail() != null) {
	        oldUser.setEmail(dto.getEmail());
	    }
	    if (dto.getPhone() != null) {
	        oldUser.setPhone(dto.getPhone());
	    }
	    if (dto.getProfileImg() != null) {
	        oldUser.setProfileImg(dto.getProfileImg());
	    }

	    return userMapper.map(userRepository.save(oldUser), UserResponseDto.class);
	}

	@Override
	public ApiResponse deleteUser(Long id) {
		User deletedUser = userRepository.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		deletedUser.setDeleted(true);
		userRepository.save(deletedUser);
		return new ApiResponse("user deleted");
	}

	@Override
	public ChangePasswordDto updatePassword(Long userId , UpdatePasswordDto dto) {
		   User user = userRepository.findByIdAndIsDeletedFalse(userId)
				   .orElseThrow(()-> new ResourceNotFoundException("user not found "));
		   user.setPassword(passwordEncoder.encode(dto.getPassword()));
		   AppUser appUser = appUserRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found "));
		   appUser.setPassword(passwordEncoder.encode(dto.getPassword()));
		   appUserRepository.save(appUser);
		return userMapper.map(userRepository.save(user), ChangePasswordDto.class);
	}

	@Override
	public UserResponseDto getUserById(Long id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    UserResponseDto dto = new UserResponseDto();
	    dto.setId(user.getId());
	    dto.setFirstName(user.getFirstName());
	    dto.setLastName(user.getLastName());
	    dto.setEmail(user.getEmail());
	    dto.setPhone(user.getPhone());
	    dto.setProfileImg(user.getProfileImg());
	    dto.setBirthDate(user.getBirthDate());
	    dto.setVerified(user.isVerified());

	    return dto;
	}



}

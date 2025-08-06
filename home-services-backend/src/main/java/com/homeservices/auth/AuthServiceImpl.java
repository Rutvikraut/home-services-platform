package com.homeservices.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.homeservices.dao.AppUserRepository;
import com.homeservices.entities.AppUser;
import com.homeservices.security.JWTUtil;

import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

//	@Override
//	public AuthResponseDTO login(AuthRequestDTO request) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	private final AuthenticationManager authManager;
//	private final AppUserRepository appUserRepository;
//	private final JWTUtil jwtUtil;
//
//	@Override
//	public AuthResponseDTO login(AuthRequestDTO request) {
//
//		Authentication authentication = authManager
//				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//		
//		AppUser appUser = appUserRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//		
//		String token = jwtUtil.generateToken(request.getEmail());
//
//		// TODO Auto-generated method stub
//		return AuthResponseDTO.builder()
//                .token(token)
//                .role(appUser.getRole().name())
//                .entityType(appUser.getEntityType())
//                .build();
//	}

}

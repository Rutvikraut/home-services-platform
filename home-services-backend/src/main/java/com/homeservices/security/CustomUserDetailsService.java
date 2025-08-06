package com.homeservices.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.homeservices.dao.AppUserRepository;
import com.homeservices.entities.AppUser;

import lombok.AllArgsConstructor;

//@Service
//@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	private final AppUserRepository appUserRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		AppUser appUser = appUserRepository.findByEmail(username)
//				.orElseThrow(() -> new UsernameNotFoundException("Email Not Found"));
//
//		return new org.springframework.security.core.userdetails.User(appUser.getEmail(), appUser.getPassword(),
//				AuthorityUtils.createAuthorityList("ROLE_" + appUser.getRole().name()));
//	}

}

package com.homeservices.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
//@RequiredArgsConstructor
public abstract class JWTAuthorizationFilter extends OncePerRequestFilter {
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private final JWTUtil jwtUtil;
//	private final CustomUserDetailsService userDetailsService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		String authHeader = request.getHeader("Authorization");
//
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//
//		String token = authHeader.substring(7);
//
//		try {
//			if (jwtUtil.validateToken(token)) {
//				String username = jwtUtil.extractUsername(token);
//
//				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//			} else {
//				// Explicitly clear the context on invalid token
//				SecurityContextHolder.clearContext();
//			}
//		} catch (Exception e) {
//			// Or if any error occurs during token processing
//			SecurityContextHolder.clearContext();
//		}
//
//		filterChain.doFilter(request, response);
//	}
}
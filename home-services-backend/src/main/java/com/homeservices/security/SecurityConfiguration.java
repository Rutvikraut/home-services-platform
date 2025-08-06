package com.homeservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor

public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.cors(cors -> cors.configurationSource(corsConfigurationSource())).csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/login", "/user/register", "/partner/register").permitAll()
						 .requestMatchers(HttpMethod.PUT, "/partner/*/orders/*/status").permitAll()
						.requestMatchers(HttpMethod.PUT, "/partners/{partnerId}/orders/{orderId}/status").permitAll()
						.requestMatchers(HttpMethod.GET, "/partner/**").permitAll()
						.requestMatchers(HttpMethod.GET,
                                "/categories",                  
                                "/order/service",               
                                "/categories/*/services"        
                        ).permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/partner/**").hasRole("PARTNER")
						.requestMatchers("/user/**").hasRole("USER").anyRequest().authenticated())
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

        return http.build();
    }
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//	private final JWTAuthorizationFilter jwtAuthorizationFilter;
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf(csrf -> csrf.disable())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/auth/login", "/user/register", "/partner/register").permitAll()
//						.requestMatchers("/admin/**").hasRole("ADMIN")
//						.requestMatchers("/partner/**").hasRole("PARTNER")
//						.requestMatchers("/user/**").hasRole("USER")
//						.anyRequest().authenticated())
//				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//				.build();
//
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}

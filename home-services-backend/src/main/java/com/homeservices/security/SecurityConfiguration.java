package com.homeservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.cors(cors -> cors.configurationSource(corsConfigurationSource())).csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/login", "/user/register", "/partner/register").permitAll()
						.requestMatchers("/api/admin/**").permitAll()
						.requestMatchers("/partner/**").permitAll()
						.requestMatchers("/booking/**").permitAll()
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/auth/reset-password").permitAll()
						.requestMatchers(HttpMethod.GET, "/categories/*/partner").permitAll()
						.requestMatchers("/categories/**").permitAll()
						 .requestMatchers(HttpMethod.PUT, "/partner/*/bookings/*/status").permitAll()
						.requestMatchers(HttpMethod.PUT, "/partners/{partnerId}/bookings/{bookingId}/status").permitAll()
						.requestMatchers(HttpMethod.GET, "/partner/**").permitAll()
						.requestMatchers(HttpMethod.GET,
                                "/categories/**",                  
                                "/booking/service",               
                                "/categories/*/services"        
                        ).permitAll()
//						.requestMatchers("/admin/**").hasRole("ADMIN")
//						.requestMatchers("/partner/**").hasRole("PARTNER")
						.requestMatchers("/user/**").hasRole("USER").anyRequest().authenticated())
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
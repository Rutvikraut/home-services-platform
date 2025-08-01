package com.homeservices.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeservices.entities.UserAddress;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "user")

public class User extends BaseEntity {

	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "phone", nullable = false)
	private String phone;
	@Column(name = "pasword_hash", nullable = false)
	private String password;
	@Column(name = "profile_img")
	private String profileImg;
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "auth_token")
	private String authToken;
	@Column(name = "auth_token_expiry")
	private LocalDate authTokenExpiry;

	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<UserAddress> addresses = new ArrayList<>();
	
	@OneToMany(mappedBy= "user",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	
}

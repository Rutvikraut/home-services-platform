package com.homeservices.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name= "partner_address" )
public class PartnerAddress extends BaseEntity {
	
	@Column(name = "address", nullable = false, length = 255)
	private String address;
	@Column(name = "pincode", nullable = false, length = 8)
	private String pincode;
	@Column(name = "city", nullable = false, length = 50)
	private String city;
	@Column(name = "state", nullable = false, length = 50)
	private String state;
	@Column(name = "country", nullable = false, length = 50)
	private String country;
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;
}

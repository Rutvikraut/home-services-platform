package com.homeservices.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "partner")
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class Partner extends BaseEntity {
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	private String password;
	@Column(name = "birth_date")
	private LocalDate birthDate;
	private int experience;
	@Column(name = "is_verified")
	private boolean isVerified;
	private float rating;
	@Column(name = "no_of_orders")
	private int noOfOrders;
	@Column(name = "total_earning")
	private double totalEarning;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "deletedAt")
	private LocalDateTime deletedAt;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private PartnerAddress myAddress;
	
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="partner_id")
	private List<Order> myOrders = new ArrayList<>();

	private boolean status = true;

//	public void addOrders(Order order) {
//		this.myOrders.add(order);
//		order.setPartner(this);
//	}

//	public void cancelOrder(Order order) {
//		this.myOrders.remove(order);
//		order.setPartner(null);
//	}
}

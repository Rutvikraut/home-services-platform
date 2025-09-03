package com.homeservices.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
@Table(name = "Service")
public class ProvidedService extends BaseEntity{
	
	private String name;
	
	private String description;
	
	private double price;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@JoinColumn(name = "category_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	@OneToMany(mappedBy = "service",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Booking> orders = new ArrayList<>();
}

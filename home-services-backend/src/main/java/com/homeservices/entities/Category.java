package com.homeservices.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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

@Table(name = "Category")
public class Category extends BaseEntity{
	private String name;
	private String description;
	@Column(name = "no_of_partners")
	private int noOfPartners;
	@CreationTimestamp
	@Column(name = "deleted_date")
	private LocalDate deletionAt;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ProvidedService> services = new ArrayList<>();
}

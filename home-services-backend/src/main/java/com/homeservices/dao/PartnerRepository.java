package com.homeservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeservices.entities.Partner;

@Repository

public interface PartnerRepository extends JpaRepository<Partner, Long> {
	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);
	
}

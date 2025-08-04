package com.homeservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeservices.entities.ProvidedService;

@Repository
public interface ServiceRepository extends JpaRepository<ProvidedService, Long>{

	boolean existsByName(String name);

}

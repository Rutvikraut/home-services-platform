package com.homeservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeservices.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}

package com.homeservices.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmailOrPhone(String email, String phone);

	Optional<User> findByIdAndIsDeletedFalse(Long id);

	Optional<User> findByEmailAndPassword(String email, String password);

}

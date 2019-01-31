package com.isa.airflights.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.isa.airflights.model.AbstractUser;

@Repository
public interface AbstractUserRepository extends JpaRepository<AbstractUser, Long> {

	AbstractUser findOneByIndex(String index);
	
	Optional<AbstractUser> findByEmail(String email);


    Boolean existsByEmail(String email);
}

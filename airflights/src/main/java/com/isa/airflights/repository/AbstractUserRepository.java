package com.isa.airflights.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isa.airflights.model.AbstractUser;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AbstractUserRepository extends JpaRepository<AbstractUser, Long> {

	AbstractUser findOneByIndex(String index);
	
	Optional<AbstractUser> findByEmail(String email);


    Boolean existsByEmail(String email);
}

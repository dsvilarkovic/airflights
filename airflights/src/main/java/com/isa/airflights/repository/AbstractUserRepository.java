package com.isa.airflights.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    List<AbstractUser> findAllByFirstNameAndLastName(String firstName, String lastName);
    
    Page<AbstractUser> findAllByFirstNameAndLastName(String firstName, String lastName, Pageable pageRequest);
}

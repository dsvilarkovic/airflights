package com.isa.airflights.repository;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.airflights.model.*;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, Long> {
	
	@Query("select b from RentACar b where b.city = ?1")
    List<RentACar> findByCity(String city);
	
}

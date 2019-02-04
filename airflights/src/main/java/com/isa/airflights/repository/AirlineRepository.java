package com.isa.airflights.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.Airline;

@Repository
public interface AirlineRepository  extends JpaRepository<Airline, Long>{
	
	Page<Airline> findAll(Pageable pageRequest);

}

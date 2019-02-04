package com.isa.airflights.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long>{
	
	Page<Airplane> findAll(Pageable pageRequest);

	Page<Airplane> findAllByAirlineId(Long airline_id, Pageable pageRequest);

}

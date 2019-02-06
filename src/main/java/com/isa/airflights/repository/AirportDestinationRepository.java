package com.isa.airflights.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.airflights.model.AirportDestination;

public interface AirportDestinationRepository extends JpaRepository<AirportDestination, Long>{


	Page<AirportDestination> findAllByAirlines_Id(Long airline_id, Pageable pageRequest);

}

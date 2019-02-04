package com.isa.airflights.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

	public List<Flight> findAllByArrivalDestinationAndDepartureDestinationAndDepartureDatetimeAndArrivalDatetime
							(Long arrival_id, Long departure_id, Date departureDatetime, Date ArrivalDatetime);
	
	public Page<Flight> findAllByArrivalDestinationAndDepartureDestinationAndDepartureDatetimeAndArrivalDatetime
	(Long arrival_id, Long departure_id, Date departureDatetime, Date ArrivalDatetime, Pageable pageRequest);
	
	public Page<Flight> findAll(Pageable pageRequest);

	public Page<Flight> findAllByAirlineId(Long airline_id, Pageable pageRequest);
	
}

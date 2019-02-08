package com.isa.airflights.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.FlightTicket;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Long>{

	Page<FlightTicket> findAllByAbstractUser_Id(Long abstractUserId, Pageable pageRequest);

	Page<FlightTicket> findAllByFlight_Airline_Id(Long airline_id, Pageable pageRequest);

	Page<FlightTicket> findAllByFlight_Id(Long flight_id, Pageable pageRequest);

	List<FlightTicket> findAllByFlight_Id(Long flight_id);

	
}
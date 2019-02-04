package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.FlightTicket;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Long>{

	
}

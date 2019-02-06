package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.airflights.model.FlightClassPrice;
import com.isa.airflights.model.enumtypes.AirlineClassType;

public interface FlightClassPriceRepository extends JpaRepository<FlightClassPrice, Long> {

	FlightClassPrice findByFlightIdAndAirlineClassType(Long flightId, AirlineClassType airlineClassType);

}

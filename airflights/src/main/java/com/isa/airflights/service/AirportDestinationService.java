package com.isa.airflights.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Airline;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.repository.AirlineRepository;
import com.isa.airflights.repository.AirportDestinationRepository;

@Service
public class AirportDestinationService {
	
	@Autowired
	AirportDestinationRepository airportDestinationRepository;
	
	@Autowired
	AirlineRepository airlineRepository;
	
	public AirportDestination getAirportDestination(Long id) {
		return airportDestinationRepository.getOne(id);
	}
	

	/**
	 * Pored dodavanja na destinaciju, mora se znati i za koju aviokompaniju se izvrsava dodavanje
	 * @param airportDestination - destinacija koja se dodaje
	 * @param airline_id - za koju aviokompaniju
	 */
	public void addAirportDestination(AirportDestination airportDestination, Long airline_id) {		
		//podesiti i na aviokompaniju
		Airline airline = airlineRepository.getOne(airline_id);
		
		//sacuvati u airportDestination
		airportDestination.getAirlines().add(airline);
		//snimiti destinaciju
		airportDestinationRepository.save(airportDestination);
		//snimiti ponovo i airline
		airlineRepository.save(airline);
	}
	
	/**
	 * Brisanje destinacije
	 * @param airport_destination_id
	 * @return
	 */
	public Boolean deleteAirportDestination(Long airport_destination_id) {
		
		try {
			airportDestinationRepository.getOne(airport_destination_id);
			airportDestinationRepository.deleteById(airport_destination_id);			
		}
		catch(IllegalArgumentException exception ) {
			return false;
		}				
		catch(EntityNotFoundException exception) {
			return false;
		}
		return true;
	}
	
	/**
	 * Azuriranje aviodestinaticije
	 * @param airportDestination
	 * @return
	 */
	public Boolean updateAirportDestination(AirportDestination airportDestination) {
		try {
			AirportDestination foundAirportDestination = airportDestinationRepository.getOne(airportDestination.getId());
			
			foundAirportDestination.setFullName(airportDestination.getFullName());
			
			airportDestinationRepository.save(foundAirportDestination);
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		return true;
	}
	
	

}

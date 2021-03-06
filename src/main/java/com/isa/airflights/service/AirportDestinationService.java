package com.isa.airflights.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.airflights.dto.AirportDestinationDTO;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.repository.AirlineRepository;
import com.isa.airflights.repository.AirportDestinationRepository;

@Service
@Transactional(readOnly = true)
public class AirportDestinationService {
	
	@Autowired
	private AirportDestinationRepository airportDestinationRepository;
	
	@Autowired
	private AirlineRepository airlineRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AirportDestination getAirportDestination(Long id) {
		return airportDestinationRepository.getOne(id);
	}
	

	/**
	 * Pored dodavanja na destinaciju, mora se znati i za koju aviokompaniju se izvrsava dodavanje
	 * @param airportDestination - destinacija koja se dodaje
	 * @param airline_id - za koju aviokompaniju
	 */
	@Transactional(readOnly = false)
	public void addAirportDestination(AirportDestinationDTO airportDestination, Long airline_id) {		
		//podesiti i na aviokompaniju
		Airline airline = airlineRepository.getOne(airline_id);
		AirportDestination ad = new AirportDestination();
		ad.getAirlines().add(airline);
		ad.setFullName(airportDestination.getFullName());
		//sacuvati u airportDestination
		//airportDestination.getAirlines().add(airline);
		//snimiti destinaciju
		airportDestinationRepository.save(ad);
		//snimiti ponovo i airline
		airlineRepository.save(airline);
	}
	
	/**
	 * Brisanje destinacije
	 * @param airport_destination_id
	 * @return
	 */
	@Transactional(readOnly = false)
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
	@Transactional(readOnly = false)
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
	
	
	public  AirportDestinationDTO convertToDTO(AirportDestination airportDestination) {
		AirportDestinationDTO airportDestinationDTO = modelMapper.map(airportDestination, AirportDestinationDTO.class);
		return airportDestinationDTO;
	}
	public AirportDestination convertToEntity(AirportDestinationDTO airportDestinationDTO) {
		AirportDestination airportDestination = modelMapper.map(airportDestinationDTO, AirportDestination.class);
		return airportDestination;
	}


	public List<AirportDestinationDTO> getAirportDestinations() {
		List<AirportDestination> dests = airportDestinationRepository.findAll();
		List<AirportDestinationDTO> destsDTO = new ArrayList<>();
		for(AirportDestination dest : dests) {
			AirportDestinationDTO d = new AirportDestinationDTO(dest);
			destsDTO.add(d);
		}
		
		return destsDTO;
	}

}
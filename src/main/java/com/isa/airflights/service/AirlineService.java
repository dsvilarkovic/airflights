package com.isa.airflights.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.airflights.dto.AirlineDTO;
import com.isa.airflights.dto.LuggagePriceListDTO;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.LuggagePrice;
import com.isa.airflights.model.LuggagePriceList;
import com.isa.airflights.repository.AirlineRepository;
import com.isa.airflights.repository.AirplaneRepository;
import com.isa.airflights.repository.AirportDestinationRepository;
import com.isa.airflights.repository.FlightRepository;
import com.isa.airflights.repository.LuggagePriceListRepository;
import com.isa.airflights.repository.LuggagePriceRepository;

@Service
public class AirlineService {
	@Autowired
	private AirlineRepository airlineRepository;
	
	@Autowired
	private AirplaneRepository airplaneRepository;
	
	@Autowired
	private LuggagePriceRepository luggagePriceRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private LuggagePriceListRepository luggagePriceListRepository;
	
	@Autowired
	private AirportDestinationRepository airportDestinationRepostory;
	
	
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void saveAirline(Airline airline) {
		airlineRepository.save(airline);	
	}
	
	public void addAirline(Airline airline) {
		
		airlineRepository.save(airline);		
		
		LuggagePriceList luggagePriceList = new LuggagePriceList();
		//podesi sa obe strane
		airline.setLuggagePriceList(luggagePriceList);
		luggagePriceList.setAirline(airline);
		
		//snimi prtljag
		luggagePriceListRepository.save(luggagePriceList);
	}
	
	public Boolean updateAirline(Airline airline) {
		Airline foundAirline;
		try {
			foundAirline = airlineRepository.getOne(airline.getId());
			
			foundAirline.setFullName(airline.getFullName());
			foundAirline.setPromoInfo(airline.getPromoInfo());
			airlineRepository.save(foundAirline);		
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		
		return true;
	}
	
	public Boolean deleteAirline(Long id) {
		try {
			airlineRepository.deleteById(id);
		}
		catch(IllegalArgumentException exception) {
			return false;
		}
		return true;
	}
	
	public Airline getAirline(Long id) {
		return airlineRepository.getOne(id);
	}
	
	public List<Airline> findAllAirlines(){
		return airlineRepository.findAll();
	}
	
	public Page<Airline> findAirlinesByPage(Pageable pageRequest){
		return airlineRepository.findAll(pageRequest);
	}
	
	public AirlineDTO convertToDTO(Airline airline) {
		AirlineDTO airlineDTO = modelMapper.map(airline, AirlineDTO.class);
		airlineDTO.setLuggageClassPriceList(new LuggagePriceListDTO(airline.getLuggagePriceList()));
		if(airline.getLocation() != null) {
			airlineDTO.setLongitude(airline.getLocation().getLongitude());
			airlineDTO.setLatitude(airline.getLocation().getLatitude());
		}
		return airlineDTO;
	}

	public Airline convertToEntity(AirlineDTO airlineDTO) {
		Airline airline = modelMapper.map(airlineDTO, Airline.class);
		return airline;
	}

	public Page<Airplane> findAirplanes(Pageable pageRequest, Long airline_id) {
		return airplaneRepository.findAllByAirlineId(airline_id, pageRequest);
	}

	public Page<LuggagePrice> findLuggagePrices(Pageable pageRequest, Long airline_id) {
		return luggagePriceRepository.findAllByLuggagePriceList_Airline_Id(airline_id, pageRequest);
	}

	public Page<Flight> findFlights(Pageable pageRequest, Long airline_id) {
		return flightRepository.findAllByAirlineId(airline_id, pageRequest);
	}

	public Page<AirportDestination> findAirportDestinations(Pageable pageRequest, Long airline_id) {
		return airportDestinationRepostory.findAllByAirlines_Id(airline_id, pageRequest);
	}
	
	/**
	 * Logicko brisanje
	 */
	public void deleteAir(Long id) {
		Airline a = airlineRepository.getOne(id);
		a.setActive(false);
		airlineRepository.save(a);
	}
	
	/**
	 * Metoda za preuzimanje kompanija pri prikazu adminu
	 * @author Sveta
	 */
	public List<Airline> getAllAdmin() {
		return airlineRepository.findAll();
	}

}
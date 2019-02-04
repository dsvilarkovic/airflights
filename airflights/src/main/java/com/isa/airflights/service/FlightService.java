package com.isa.airflights.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.airflights.dto.FlightDTO;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.FlightClassPrice;
import com.isa.airflights.model.enumtypes.AirlineClassType;
import com.isa.airflights.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AirlineService airlineService;
	
	@Autowired
	private AirportDestinationService airportDestinationService;
	
	public Flight getFlight(Long id) {
		return flightRepository.getOne(id);
	}
	
	public void addFlight(Flight flight) {
		//pre ovoga, podesi i cene karata
		flightRepository.save(flight);
	}
	
	public Boolean updateFlight(Flight flight) {
		Flight foundFlight;
		try {
			foundFlight = flightRepository.getOne(flight.getId());
			foundFlight.setDepartureDatetime(flight.getDepartureDatetime());
			foundFlight.setArrivalDatetime(flight.getArrivalDatetime());
			foundFlight.setFlightDiscount(flight.getFlightDiscount());
			//aviokompanija je fiksirana
			foundFlight.setFlightClassPrices(flight.getFlightClassPrices());
			foundFlight.setFlightsLegs(flight.getFlightsLegs());
			foundFlight.setLegCount(foundFlight.getFlightsLegs().size());
			foundFlight.setTravelDistance(flight.getTravelDistance());
			foundFlight.setTravelTime(flight.getTravelTime());
			
			//sacuvaj izmene
			flightRepository.save(foundFlight);
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		return true;
	}
	
	public Boolean deleteFlight(Long id) {
		try {
			flightRepository.getOne(id);
			flightRepository.deleteById(id);
		}
		catch(EntityNotFoundException e) {
			return false; //nije nasao za brisanje
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	
	public List<Flight> findAll(){
		return flightRepository.findAll();
	}
	
	public Page<Flight> findAll(Pageable pageRequest){
		return flightRepository.findAll(pageRequest);
	}
	
	
	public List<Flight> findAll(Example<Flight> searchCriteria){
		return flightRepository.findAll(searchCriteria);
	}
	
	public List<Flight> findAllByArrivalIdAndDepartureIdAndDepartureDatetimeAndArrivalDatetime
			(Long arrival_id, Long departure_id, Date departureDatetime, Date ArrivalDatetime){
		return flightRepository.findAllByArrivalDestinationAndDepartureDestinationAndDepartureDatetimeAndArrivalDatetime
				(arrival_id, departure_id, departureDatetime, ArrivalDatetime);
	}
	
	public Page<Flight> findAllByArrivalIdAndDepartureIdAndDepartureDatetimeAndArrivalDatetime
	(Long arrival_id, Long departure_id, Date departureDatetime, Date ArrivalDatetime, Pageable pageRequest){
		return flightRepository.findAllByArrivalDestinationAndDepartureDestinationAndDepartureDatetimeAndArrivalDatetime
				(arrival_id, departure_id, departureDatetime, ArrivalDatetime, pageRequest);
	}
	
//	public List<Flight> find(){
//		flightRepository.findBy
//	}
	
	public FlightDTO convertToDTO(Flight flight) {
		FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
		flightDTO.setAirlineId(flight.getAirline().getId());
		
		//namesti legove koji su po idjevima
		List<AirportDestination> flightLegs = flight.getFlightsLegs();
		List<Long> flightLegs_id = new ArrayList<>();
		for (AirportDestination airportDestination : flightLegs) {
			Long airport_id = airportDestination.getId();
			flightLegs_id.add(airport_id);
		}
		flightDTO.setFlightLegsId(flightLegs_id);
		
		//podesi cene letova
		Map<AirlineClassType, Double> flightClassPricesMap = new TreeMap<AirlineClassType, Double>();
		Set<FlightClassPrice> flightClassPrices = new HashSet<>();
		for (FlightClassPrice flightClassPrice : flightClassPrices) {
			flightClassPricesMap.put(flightClassPrice.getAirlineClassType(), flightClassPrice.getPrice());
		}
		flightDTO.setFlightClassPricesMap(flightClassPricesMap);
		
		//podesi arrival i departure id-jeve
		
		//uzima se prvi leg kao pocetni za uzletanje
		flightDTO.setDepartureDestination(flightLegs_id.get(0));
		flightDTO.setArrivalDestination(flightLegs_id.get(flightLegs_id.size() - 1));
			
		return flightDTO;
	}
	
	public Flight convertToEntity(FlightDTO flightDTO) {
		Flight flight = modelMapper.map(flightDTO, Flight.class);
		Airline airline = airlineService.getAirline(flightDTO.getAirlineId());
		
		flight.setAirline(airline);
		List<AirportDestination> flightLegs = new ArrayList<>();
		List<Long> flightLegs_id = flightDTO.getFlightLegsId();
		
		for (Long id : flightLegs_id) {
			AirportDestination airportDestination = airportDestinationService.getAirportDestination(id);
			//TODO: ovde za flight dodati sta treba
			flightLegs.add(airportDestination);
		}
		
		flight.setFlightsLegs(flightLegs);
		
		flight.setDepartureDestination(flightLegs_id.get(0));
		flight.setArrivalDestination(flightLegs_id.get(flightLegs_id.size() - 1));
		
		return flight;
	}
}

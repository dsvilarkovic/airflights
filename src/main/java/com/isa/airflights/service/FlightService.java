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
import org.springframework.transaction.annotation.Transactional;

import com.isa.airflights.dto.AirportDestinationDTO;
import com.isa.airflights.dto.FlightDTO;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.FlightClassPrice;
import com.isa.airflights.model.enumtypes.AirlineClassType;
import com.isa.airflights.repository.FlightClassPriceRepository;
import com.isa.airflights.repository.FlightRepository;


@Service
@Transactional(readOnly = true)
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AirlineService airlineService;
	
	@Autowired
	private AirportDestinationService airportDestinationService;
	
	@Autowired
	private FlightClassPriceRepository flightClassPriceRepository;
	
	public Flight getFlight(Long id) {
		return flightRepository.getOne(id);
	}
	
	@Transactional(readOnly = false)
	public void saveFlight(Flight flight) {
		flightRepository.save(flight);		
	}
	
	@Transactional(readOnly = false)
	public void addFlight(Flight flight) {
		flightRepository.save(flight);
		
		//podesi i cene karata
		Set<FlightClassPrice> flightClassPrices = flight.getFlightClassPrices();
		
		for (FlightClassPrice flightClassPrice : flightClassPrices) {
			flightClassPriceRepository.save(flightClassPrice);
		}
	}
	
	@Transactional(readOnly = false)
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
			foundFlight.setId(flight.getId());
			//sacuvaj izmene
			flightRepository.save(foundFlight);
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		return true;
	}
	
	@Transactional(readOnly = false)
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
	
	public List<Flight> findAllByArrivalIdAndDepartureIdAndDepartureDatetimeAndArrivalDatetime
	(Long arrival_id, Long departure_id, Date departureDatetime, Date ArrivalDatetime, Pageable pageRequest){
		return flightRepository.findAllByArrivalDestinationAndDepartureDestinationAndDepartureDatetimeAndArrivalDatetime
				(arrival_id, departure_id, departureDatetime, ArrivalDatetime);
	}
	
	public List<Flight> findAllByArrivalIdAndDepartureId
	(Long arrival_id, Long departure_id, Pageable pageRequest){
		return flightRepository.findAllByArrivalDestinationAndDepartureDestination
				(arrival_id, departure_id);
	}
	
//	public List<Flight> find(){
//		flightRepository.findBy
//	}
	
	public FlightDTO convertToDTO(Flight flight) {
		FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
		flightDTO.setAirlineId(flight.getAirline().getId());
		
		//namesti legove koji su po idjevima
		List<AirportDestination> flightLegs = flight.getFlightsLegs();
		List<AirportDestinationDTO> flightLegs_id = new ArrayList<>();
		for (AirportDestination airportDestination : flightLegs) {
			AirportDestinationDTO airport = new AirportDestinationDTO(airportDestination);
			flightLegs_id.add(airport);
		}
		flightDTO.setFlightLegsDTO(flightLegs_id);
		
		//podesi cene letova
		Map<AirlineClassType, Double> flightClassPricesMap = new TreeMap<AirlineClassType, Double>();
		Set<FlightClassPrice> flightClassPrices = new HashSet<>();
		for (FlightClassPrice flightClassPrice : flight.getFlightClassPrices()) {
			flightClassPricesMap.put(flightClassPrice.getAirlineClassType(), flightClassPrice.getPrice());
		}
		flightDTO.setFlightClassPricesMap(flightClassPricesMap);
		
		//podesi arrival i departure id-jeve
		
		//uzima se prvi leg kao pocetni za uzletanje
		flightDTO.setDepartureDestination(flightLegs.get(0).getId());
		flightDTO.setArrivalDestination(flightLegs.get(flightLegs_id.size() - 1).getId());
			
		return flightDTO;
	}
	
	public Flight convertToEntity(FlightDTO flightDTO) {
		
		Flight flight2 = new Flight();
		Airline airline = airlineService.getAirline(flightDTO.getAirlineId());
		flight2.setAirline(airline);
		flightRepository.save(flight2);
		Flight flight = modelMapper.map(flightDTO, Flight.class);
		flight.setId(flight2.getId());
		//Airline airline = airlineService.getAirline(flightDTO.getAirlineId());
		//flight.setAirline(airline);
		List<AirportDestination> flightLegs = new ArrayList<>();
		List<AirportDestinationDTO> flightLegsDTO = flightDTO.getFlightLegsDTO();
		
		for (AirportDestinationDTO ad : flightLegsDTO) {
			AirportDestination airportDestination = airportDestinationService.getAirportDestination(ad.getId());
			//TODO: ovde za flight dodati sta treba
			flightLegs.add(airportDestination);
		}
		
		flight.setFlightsLegs(flightLegs);
		flight.setLegCount(flight.getLegCount() + 2);
		flight.setDepartureDestination(flightLegsDTO.get(0).getId());
		flight.setArrivalDestination(flightLegsDTO.get(flightLegsDTO.size() - 1).getId());
		
		return flight;
	}

	
}
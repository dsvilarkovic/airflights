package com.isa.airflights.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.isa.airflights.dto.AirportDestinationDTO;
import com.isa.airflights.dto.FlightTicketDTO;
import com.isa.airflights.dto.SeatDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.AirportDestination;
import com.isa.airflights.model.Flight;
import com.isa.airflights.model.FlightClassPrice;
import com.isa.airflights.model.FlightTicket;
import com.isa.airflights.model.Seat;
import com.isa.airflights.model.enumtypes.AirlineClassType;
import com.isa.airflights.repository.AirportDestinationRepository;
import com.isa.airflights.repository.FlightClassPriceRepository;
import com.isa.airflights.repository.FlightRepository;
import com.isa.airflights.repository.FlightTicketRepository;
import com.isa.airflights.repository.SeatRepository;

@Service
public class FlightTicketService {

	@Autowired
	private FlightTicketRepository flightTicketRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired 
	private AirportDestinationRepository airportDestinationRepository;
	
	@Autowired 
	private AirportDestinationService airportDestinationService;
	
	@Autowired 
	private FlightClassPriceRepository flightClassPriceRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	//kreiraj kartu
	public void saveFlightTicket(FlightTicket flightTicket) {
		flightTicketRepository.save(flightTicket);
	}
	
	//uzmi kartu
	public FlightTicket getFlightTicket(Long id) {
		return flightTicketRepository.getOne(id);
	}
	//obrisi kartu
	public Boolean deleteFlightTicket(Long id) {
		try {
			flightTicketRepository.getOne(id);
			flightTicketRepository.deleteById(id);
		}
		catch(EntityNotFoundException e) {
			return false;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	
	//prikazi sve karte na kojima je odabrani korisnik naveden
	public List<FlightTicket> findAllByAbstractUser_Id(Long abstractUserId, Pageable pageRequest){
		return flightTicketRepository.findAllByAbstractUser_Id(abstractUserId, pageRequest).getContent();
	}
	
	
	public List<FlightTicket> findAllByFlight_Airline_Id(Long airline_id, Pageable pageRequest) {
		return flightTicketRepository.findAllByFlight_Airline_Id(airline_id, pageRequest).getContent();
	}
	
	public FlightTicket convertToEntity(FlightTicketDTO flightTicketDTO) {
		FlightTicket flightTicket = modelMapper.map(flightTicketDTO, FlightTicket.class);
		
		//Ovo iskljucivo u kontroleru
		//flightTicket.setAbstractUser(abstractUser);
		Flight flight = flightRepository.getOne(flightTicketDTO.getFlightId());
		
		flightTicket.setFlight(flight);
		
		Long flightId = flightTicketDTO.getFlightId();
		AirlineClassType airlineClassType  = flightTicketDTO.getAirlineClassType();
		FlightClassPrice flightClassPrice = flightClassPriceRepository.findByFlightIdAndAirlineClassType(flightId, airlineClassType);
		flightTicket.setFlightClassPrice(flightClassPrice);
		
		
		Seat seat = seatRepository.getOne(flightTicketDTO.getSeatDTO().getId());
		flightTicket.setSeat(seat);
		
		
		return flightTicket;
	}
	
	public FlightTicketDTO convertToDTO(FlightTicket flightTicket) {
		FlightTicketDTO flightTicketDTO = modelMapper.map(flightTicket, FlightTicketDTO.class);
		
		//uzmi let da izvuces bitne informacije za slanje
		Flight flight = flightRepository.getOne(flightTicket.getFlight().getId());
		
		flightTicketDTO.setId(flightTicket.getId());
		
		flightTicketDTO.setAirlineClassType(flightTicket.getFlightClassPrice().getAirlineClassType());
		flightTicketDTO.setArrivalDatetime(flight.getArrivalDatetime());
		flightTicketDTO.setDepartureDatetime(flight.getDepartureDatetime());
		
		flightTicketDTO.setFlightId(flight.getId());
		
		AirportDestination arrivalDestination = airportDestinationRepository.getOne(flight.getArrivalDestination());
		AirportDestination departureDestination = airportDestinationRepository.getOne(flight.getDepartureDestination());
		AirportDestinationDTO arrivalDestinationDTO = airportDestinationService.convertToDTO(arrivalDestination);
		AirportDestinationDTO departureDestinationDTO = airportDestinationService.convertToDTO(departureDestination);
		
		flightTicketDTO.setArrivalDestination(arrivalDestinationDTO);
		flightTicketDTO.setDepartureDestination(departureDestinationDTO);
		
		
		Double flightClassPrice = flightTicket.getFlightClassPrice().getPrice();
		flightTicketDTO.setFlightClassPrice(flightClassPrice);
		
		SeatDTO seatDTO = modelMapper.map(flightTicket.getSeat(), SeatDTO.class);
		flightTicketDTO.setSeatDTO(seatDTO);
		
		flightTicketDTO.setTravelDistance(flight.getTravelDistance());
		flightTicketDTO.setTravelTime(flight.getTravelTime());
		

		//ovo iskljucivo u kontroleru
		//flightTicketDTO.setUserId(flightTicket.getAbstractUser().getId());
		
		
		return flightTicketDTO;
	}
	
	public void sendEmailReservationInvitation(AbstractUser senderUser, AbstractUser receiverUser, String linkInvitation, Long flightTicketId) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(receiverUser.getEmail());
		mail.setFrom(senderUser.getEmail());
		mail.setSubject("Flight invitation");
		
		
		String bodyContent = String.format("Hello %s, how are you? %s has just sent you an email invitation, "
				+ "please respond as soon as you can: %s", receiverUser.getFirstName(), senderUser.getFirstName(), linkInvitation);
		mail.setText(bodyContent);
		
		javaMailSender.send(mail);
		//TODO: @Viktor: Ovde mozes da dodas tajmer za ono otkazivanje posle tri sata ili tri sata pre leta
		//deleteFlightTicket(flightTicketId) ce obrisati kartu 
	}

	public List<FlightTicket> findAllByFlight_Id(Long flight_id, Pageable pageRequest) {
		return flightTicketRepository.findAllByFlight_Id(flight_id, pageRequest).getContent();
	}

	
}

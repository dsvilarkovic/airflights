package com.isa.airflights.service;

import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.airflights.dto.SeatDTO;
import com.isa.airflights.dto.SegmentConfigDTO;
import com.isa.airflights.model.Seat;
import com.isa.airflights.model.SegmentConfig;
import com.isa.airflights.repository.SeatRepository;
import com.isa.airflights.repository.SegmentConfigRepository;

@Service
@Transactional(readOnly = true)
public class SegmentConfigService {

	@Autowired
	private SegmentConfigRepository segmentConfigRepository;
	
	@Autowired 
	private SeatRepository seatRepository;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SegmentConfig getConfig(Long id) throws EntityNotFoundException{
		return segmentConfigRepository.getOne(id);
	}
	
	@Transactional(readOnly = false)
	public void addConfig(SegmentConfig segmentConfig) {
		
		segmentConfigRepository.save(segmentConfig);
		//updateSeats(segmentConfig);
	}
	@Transactional(readOnly = false)
	public Boolean deleteConfig(Long id) {
		try {
			segmentConfigRepository.deleteById(id);
//			SegmentConfig segmentConfig =  segmentConfigRepository.getOne(id);
//			System.out.println("SegmentConfig je : " + segmentConfig.getId());
		}
		catch(IllegalArgumentException exception) {
			return false;
		}		
		return true;
	}
	@Transactional(readOnly = false)
	public Boolean updateConfig(SegmentConfig segmentConfig) {
		SegmentConfig foundConfig;
		try {
			foundConfig = segmentConfigRepository.getOne(segmentConfig.getId());
			//obrati paznju na cuvanje seats i airplane pri azuriranju
			//segmentConfig.setSeats(seats);
			//segmentConfig.setSeats(foundConfig.getSeats());
			//segmentConfig.setAirplane(foundConfig.getAirplane());		
			
			foundConfig.setSegmentNum(segmentConfig.getSegmentNum());
			segmentConfigRepository.save(foundConfig);
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		//snimi konfiguraciju
		
		//updateSeats(segmentConfig);
		return true;
	}
	
	
	/**
	 * Azuriranje sedista koja su falila u prethodnoj konfiguraciji
	 * @param segmentConfig
	 */
	@Transactional(readOnly = false)
	public void updateSeats(SegmentConfig segmentConfig) {
		Set<Seat> seats = segmentConfig.getSeats();
		for (Seat seat : seats) {
			seatRepository.save(seat);
		}
	}
	
	
	public SegmentConfigDTO convertToDTO(SegmentConfig segmentConfig) {
		SegmentConfigDTO segmentConfigDTO = modelMapper.map(segmentConfig, SegmentConfigDTO.class);
		segmentConfigDTO.setAirplaneId(segmentConfig.getAirplane().getId());
		
		Set<Seat> seats = segmentConfig.getSeats();
		for (Seat seat : seats) {
			SeatDTO seatDTO = modelMapper.map(seat, SeatDTO.class);
			seat.setSegmentConfig(segmentConfig);
			
			seatDTO.setConfiguration(segmentConfig.getId());
			segmentConfigDTO.getSeats().add(seatDTO);
		}
		return segmentConfigDTO;
	}
	
	public SegmentConfig convertToEntity(SegmentConfigDTO segmentConfigDTO) {
		SegmentConfig segmentConfig = modelMapper.map(segmentConfigDTO, SegmentConfig.class);
		
		
		Set<SeatDTO> seatDTOs = segmentConfigDTO.getSeats();
		for (SeatDTO seatDTO : seatDTOs) {
			Seat seat = new Seat();
			seat = modelMapper.map(seatDTO, Seat.class);
			System.out.println("Segment num je: " + seatDTO.getSegmentNum() + " | " + seat.getSegmentNum());
			
			seatService.saveSeat(seat);			
			//System.out.println("Id od seat je "  + seat.getId());
			//OVDE ubaciti seatService da radi ono sto je u segmentConfigService radjeno
			
			
			seat.setSegmentConfig(segmentConfig);			
			segmentConfig.getSeats().add(seat);
		}
		
		return segmentConfig;
	}
}
package com.isa.airflights.service;

import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Seat;
import com.isa.airflights.model.SegmentConfig;
import com.isa.airflights.repository.SeatRepository;
import com.isa.airflights.repository.SegmentConfigRepository;

@Service
public class SegmentConfigService {

	@Autowired
	private SegmentConfigRepository segmentConfigRepository;
	
	@Autowired 
	private SeatRepository seatRepository;
	
	public SegmentConfig getConfig(Long id) throws EntityNotFoundException{
		return segmentConfigRepository.getOne(id);
	}
	
	public void addConfig(SegmentConfig segmentConfig) {
		
		segmentConfigRepository.save(segmentConfig);
		//updateSeats(segmentConfig);
	}
	
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
	public void updateSeats(SegmentConfig segmentConfig) {
		Set<Seat> seats = segmentConfig.getSeats();
		for (Seat seat : seats) {
			seatRepository.save(seat);
		}
	}
}

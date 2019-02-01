package com.isa.airflights.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.SegmentConfig;
import com.isa.airflights.repository.SegmentConfigRepository;

@Service
public class SegmentConfigService {

	@Autowired
	SegmentConfigRepository segmentConfigRepository;
	
	
	public SegmentConfig getConfig(Long id) throws EntityNotFoundException{
		return segmentConfigRepository.getOne(id);
	}
	
	public void addConfig(SegmentConfig segmentConfig) {
		segmentConfigRepository.save(segmentConfig);
	}
	
	public Boolean deleteConfig(Long id) {
		try {
			segmentConfigRepository.deleteById(id);
		}
		catch(IllegalArgumentException exception) {
			return false;
		}		
		return true;
	}
	
	public Boolean updateConfig(SegmentConfig segmentConfig) {
		try {
			SegmentConfig foundConfig = segmentConfigRepository.getOne(segmentConfig.getId());
			//obrati paznju na cuvanje seats i airplane pri azuriranju
			//segmentConfig.setSeats(seats);
			segmentConfig.setSeats(foundConfig.getSeats());
			segmentConfig.setAirplane(foundConfig.getAirplane());			
		}
		catch(EntityNotFoundException exception) {
			return false;
		}
		//snimi konfiguraciju
		segmentConfigRepository.save(segmentConfig);
		return true;
	}
	
}

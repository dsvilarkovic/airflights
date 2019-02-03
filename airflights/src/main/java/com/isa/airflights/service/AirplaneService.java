package com.isa.airflights.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.Airplane;
import com.isa.airflights.repository.AirplaneRepository;

@Service
public class AirplaneService {

	@Autowired
	private AirplaneRepository airplaneRepository;
	
	
	public Airplane findOne(Long id) {
		return airplaneRepository.getOne(id);
	}


	public void createAirplane(Airplane airplane) {
		airplaneRepository.save(airplane);
		
	}


	
	public Boolean updateAirplane(Airplane airplane) {
		Airplane foundAirplane;
		try {
			//nadji avion
			foundAirplane = airplaneRepository.getOne(airplane.getId());
			//podesi mu potrebne parametre 
			foundAirplane.setFullName(airplane.getFullName());
			//sacuvaj ga
			airplaneRepository.save(foundAirplane);			
		}
		catch(EntityNotFoundException exception) {
			return false;
		}		
		return true;
	}

	/**
	 * Metoda za brisanje aviona
	 * @param id - id aviona koji treba obrisati
	 * @return - false ako je prosledjen null parametar
	 */
	public Boolean deleteAirplane(Long id) {
		try {
			airplaneRepository.deleteById(id);
		}
		//u slucaju da se brise po null parametru
		catch(IllegalArgumentException exception) {
			return false;
		}
		return true;
	}


	public List<Airplane> findAll() {
		return airplaneRepository.findAll();		
	}


	public Page<Airplane> findByPageNumber(Pageable pageRequest) {
		
		return airplaneRepository.findAll(pageRequest);
	}

}

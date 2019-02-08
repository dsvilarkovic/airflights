package com.isa.airflights.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.airflights.dto.AirplaneDTO;
import com.isa.airflights.model.Airplane;
import com.isa.airflights.repository.AirplaneRepository;

@Service
@Transactional(readOnly = true)
public class AirplaneService {

	@Autowired
	private AirplaneRepository airplaneRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Airplane findOne(Long id) {
		return airplaneRepository.getOne(id);
	}

	@Transactional(readOnly = false)
	public void createAirplane(Airplane airplane) {
		airplaneRepository.save(airplane);
		
	}


	@Transactional(readOnly = false)
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
	@Transactional(readOnly = false)
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
	
	
	public AirplaneDTO convertToDTO(Airplane airplane) {
		AirplaneDTO airplaneDTO = modelMapper.map(airplane, AirplaneDTO.class);
		
		if(airplane.getAirline() != null)
			airplaneDTO.setAirline_id(airplane.getAirline().getId());

		
		return airplaneDTO;
	}
	
	public Airplane convertToEntity(AirplaneDTO airplaneDTO) {
		Airplane airplane = modelMapper.map(airplaneDTO, Airplane.class);
		
		return airplane;
	}


	public Airplane findByFlight_Id(Long flight_id) {
		return airplaneRepository.findByFlight_Id(flight_id);
	}

}

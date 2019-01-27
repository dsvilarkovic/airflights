package com.isa.airflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.service.AbstractUserService;



/**
 * Klasa za potvrdu izmene korisnika, ukljucuje i dodavanje prijatelja 
 * @author Dusan
 *
 */
@RestController
@RequestMapping(value="/api/userProfile")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {
	
	@Autowired
	private AbstractUserService abstractUserService;


	
	/**
	 * Sluzi za updateovanje korisnika.
	 * Proverava se da li uopste postoji korisnik koji se udpateuje, ako ne salje error http status kod. </br>
	 * Ako da onda se vrsi update-ovanje korisnika.
	 * @author Dusan
	 * @param updatedUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(
			value = "/update",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	//TODO @Dusan: izmeniti da umesto AbstractUser koristi AbstractUserDTO, zbog confirm password-a, malo je kompleksniji
	//TODO 2 @Dusan: uraditi proveru pri loginu da li je to taj nalog kojim se logujem
	public ResponseEntity<AbstractUserDTO> updateUser(
			@RequestBody AbstractUserDTO updatedUser) throws Exception {
		AbstractUser savedAbstractUser = abstractUserService.updateAbstractUser(updatedUser);
		
		if(savedAbstractUser == null) {
			return new ResponseEntity<AbstractUserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AbstractUserDTO>(new AbstractUserDTO(savedAbstractUser), HttpStatus.CREATED);
	}
}

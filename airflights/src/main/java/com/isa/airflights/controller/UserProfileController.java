package com.isa.airflights.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.isa.airflights.config.JwtSecurityConfig;
import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.utils.StringJSON;



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
    private ModelMapper modelMapper;
	@Autowired
	private AbstractUserService abstractUserService;
	
//	@Autowired
//	private JwtSecurityConfig jwtSecurityConfig;
	
	 @Autowired
	 PasswordEncoder encoder;
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
//	@Autowired
//    private ModelMapper modelMapper;

	/**
	 * @return - {@code AbstractUser} koji treba da se koristi za izmenu podataka
	 */
	@RequestMapping(
			value = "/get",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<AbstractUserDTO> getUser(){		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		
		//pretvori ga u DTO
		AbstractUserDTO loggedUserDTO = convertToDTO(loggedUser);
		
		if(loggedUserDTO == null) {
			return new ResponseEntity<AbstractUserDTO>(HttpStatus.NOT_FOUND);
		} 
		
		return new ResponseEntity<AbstractUserDTO>(loggedUserDTO, HttpStatus.OK);
	}

	
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
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	//TODO @Dusan: izmeniti da umesto AbstractUser koristi AbstractUserDTO, zbog confirm password-a, malo je kompleksniji
	public ResponseEntity<?> updateUser(
			@RequestBody AbstractUserDTO updatedUserDTO) throws Exception {
		//uzmi trenutno ulogovanog korisnika
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		
		
		//proveri da li nova sifra uopste postoji, i ako postoji, proveravaj dalje
		if(updatedUserDTO.getNewPassword() != null && updatedUserDTO.getNewPassword().length() != 0) {
			//prvo proveri da li su sifra logovanog i klijentskog korisnika iste
			String passwordHash = encoder.encode(updatedUserDTO.getPassword());
			
			if(authenticateUser(updatedUserDTO.getPassword(), loggedUser.getPassword()) == false) {
				//ako nisu, vrati error
				System.out.println("Nisu isti password-i");
				System.out.println(updatedUserDTO.getPassword());
				System.out.println("Password-i su: " + passwordHash + "\n" + loggedUser.getPassword());
				return new ResponseEntity<StringJSON>(new StringJSON("Wrong old password"), HttpStatus.NOT_FOUND);
			}
			//ako nije, podesi updatedUserDTO-u da mu je password onaj newPassword
			System.out.println("Nova sifra je " + updatedUserDTO.getNewPassword());
			String newPasswordHash = encoder.encode(updatedUserDTO.getNewPassword());
			System.out.println("Enkodovana sifra je " + newPasswordHash);
			updatedUserDTO.setPassword(newPasswordHash);
		}
		//ako nema nove sifre, podesi mu trenutnu koju ima
		else {
			//dodaj mu sifru
			updatedUserDTO.setPassword(loggedUser.getPassword());
		}
		
		//ako nema logovanog korisnika, vrati nazad		
		if(loggedUser == null) {
			System.out.println("Ne postoji logovani korisnik");
			return new ResponseEntity<StringJSON>(new StringJSON("No logged user under this data"),HttpStatus.NOT_FOUND);
		}
		
		//prebaci DTO u entitet
		AbstractUser updatedUser = convertToEntity(updatedUserDTO);
		
		
		//proveri da li je isti kao ovaj sto ce se sad menjati
		if(loggedUser.equals(updatedUser)) {
			System.out.println("Nije nasao da su jednaki");
			return new ResponseEntity<StringJSON>(new StringJSON("Somehow this user is not same as the logged one. Please refresh this page"), HttpStatus.NOT_FOUND);
		}
		
		AbstractUser savedAbstractUser = abstractUserService.updateAbstractUser(updatedUser);
		
		//vrati ga u oblik za klijenta
		AbstractUserDTO savedAbstractUserDTO = convertToDTO(savedAbstractUser);
		
		if(savedAbstractUser == null) {
			System.out.println("Greska pri updateovanju se desila");
			return new ResponseEntity<StringJSON>(new StringJSON("There has been an error in updating user"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AbstractUserDTO>(savedAbstractUserDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Za koriscenje u komunikaciji za klijentom
	 * @param abstractUser - entitet
	 * @return - DTO objekat
	 */
	private AbstractUserDTO convertToDTO(AbstractUser abstractUser) {
		System.out.println("da li je null?"  + abstractUser);
		//TODO: @Dusan : konverzije DTO u obicne objekte i obrnuto
		System.out.println(modelMapper);
		AbstractUserDTO abstractUserDTO = modelMapper.map(abstractUser, AbstractUserDTO.class);
		abstractUserDTO.setPassword("");

		
		return abstractUserDTO;
	}
	
	/**
	 * Za koriscenje u komunikaciji sa skladistem podataka
	 * @param abstractUserDTO - DTO 
	 * @return - entitet
	 */
	private AbstractUser convertToEntity(AbstractUserDTO abstractUserDTO) {
		//TODO: @Dusan : konverzije DTO u obicne objekte i obrnuto
		AbstractUser abstractUser = modelMapper.map(abstractUserDTO, AbstractUser.class);
		return abstractUser;		
	}
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private boolean authenticateUser(String passwordCompare, String hashPassword) {
		return encoder.matches(passwordCompare, hashPassword);		
	}
	
	
	
}

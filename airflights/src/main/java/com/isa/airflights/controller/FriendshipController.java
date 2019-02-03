package com.isa.airflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.FriendshipDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Friendship;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.FriendshipService;
import com.isa.airflights.utils.StringJSON;

@RestController
@RequestMapping(value="/api/userProfile/friends")
@CrossOrigin(origins = "http://localhost:4200")
public class FriendshipController {

	@Autowired
	private AbstractUserService abstractUserService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	//send request
	@RequestMapping(value = "/send",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> sendRequest(@RequestBody FriendshipDTO friendshipDTO){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		
		if(loggedUser == null) {
			return new ResponseEntity<>(new StringJSON("No currently logged-in user found"), HttpStatus.NOT_FOUND);
		} 
		
		if(friendshipDTO.getReceiverId() == null || friendshipDTO.getSenderId() == null) {
			return new ResponseEntity<>(new StringJSON("Invalid friendship"), HttpStatus.NOT_FOUND);
		}
		
		//proveri da li je sender isti kao i ulogovani korisnik
		AbstractUser sender = abstractUserService.getOne(friendshipDTO.getSenderId());
		if(!loggedUser.equals(sender)) {
			return new ResponseEntity<>(new StringJSON("Sender and logged-in user are not the same person. You are intruder! .!."), HttpStatus.NOT_FOUND);
		}
					
		Friendship friendship = friendshipService.convertToEntity(friendshipDTO);
		
		friendshipService.saveFriendship(friendship);
		
		return new ResponseEntity<>(new StringJSON("Successfully added friendship"), HttpStatus.OK);
	}
	
	/**
	 * Metoda za brisanje prijateljstva
	 * @param id - id prijateljstva
	 * @return
	 */
	//revert request  + //delete friend
	@RequestMapping(value = "/revert/{id}",
					method = RequestMethod.DELETE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> revertRequest(@PathVariable Long id){
		
		Boolean success = friendshipService.deleteFriendship(id);
		
		if(!success) {
			return new ResponseEntity<>(new StringJSON("Unsuccesful deletion of friendship"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new StringJSON("Successful deletion, friendship ended with someone, find someone new who is your best friend!"), HttpStatus.OK);		
	}
	
	
	//accept
	@RequestMapping(value = "/accept/{id}",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> acceptFriendRequest(@PathVariable Long id){
		
		Boolean success = friendshipService.acceptFriendship(id);
		if(!success) {
			return new ResponseEntity<>(new StringJSON("Friendship not accepted, because it does not exist"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new StringJSON("Friendship started, congratulations"), HttpStatus.NOT_FOUND);
	}
	
	//get all accepted friends of user {user_id}
	@RequestMapping(value = "/find/all",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllFriendships(){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ulogovani je : " + loggedInUser.getName());
		AbstractUser loggedUser = abstractUserService.getAbstractUser(loggedInUser);
		
		return null;
	}
	
	//get all friends pageable of user {user_id}
	
	//get all friends pending 
	
	//get all friends asking for friendship
	
	//get all users by search criteria
	
}

package com.isa.airflights.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/userProfile/friends")
@CrossOrigin(origins = "http://localhost:4200")
public class FriendshipController {

	
	//send request
	@RequestMapping(value = "/send",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<?> sendRequest(){
		
		
		return null;
	}
	
	//revert request 
	
	
	//delete friend
	
	
	
	
	//accept
	
	//get all friends of user {user_id}
	
	//get all friends pageable of user {user_id}
	
	//get all friends pending 
	
	//get all friends asking for friendship
	
}

package com.isa.airflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.model.*;
import antlr.collections.List;


import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value="/api/abstractUsers")
@CrossOrigin(origins = "http://localhost:4200")
public class AbstractUserController {
	
	private Logger logger = LoggerFactory.getLogger(AbstractUserController.class);
	
	@Autowired
	private AbstractUserService abstractUserService;
	
	@RequestMapping("/test")
	public String test() {
		return "Ovo je test";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AbstractUser> login(@RequestBody AbstractUser user,HttpSession session,HttpServletRequest request){
		System.out.println("Usao u metodu");
		AbstractUser loggedUser = abstractUserService.login(user);
		if(loggedUser != null) {
			System.out.println("Usao u if");
		    HttpSession newSession = request.getSession();
		    newSession.setAttribute("loggedUser", loggedUser);
		    AbstractUser logged = new AbstractUser(loggedUser);
		    return new ResponseEntity<AbstractUser>(logged,HttpStatus.OK);
		}
		AbstractUser logged = null;
		return new ResponseEntity<AbstractUser>(logged,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AbstractUser> register(@RequestBody AbstractUser absuser){
		System.out.println("Usao u register metodu");
		AbstractUser user = new AbstractUser();
		user.setAddress(absuser.getAddress());
		System.out.println("Adresa: " + user.getAddress());
		user.setEmail(absuser.getEmail());
		System.out.println("Mail: " + user.getEmail()); 
		user.setFirstName(absuser.getFirstName());
		user.setPassword(absuser.getPassword());
		System.out.println("Pss: " + user.getPassword());
		user.setPhoneNumber(absuser.getPhoneNumber());
		user.setLastName(absuser.getLastName());
		user.setVerify(false);
		user = abstractUserService.save(user);
		
		return new ResponseEntity<AbstractUser>(new AbstractUser(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/mail/{id}")
	public String sendMail(@PathVariable Long id) throws InterruptedException, MessagingException {
		AbstractUser user =  abstractUserService.getOne(id);		
		System.out.println("Usao ovdi ? " + user.getId());
		
			String test;
			try {
				test = abstractUserService.sendVerMail(user);
				System.out.println("Mail je poslat " + test);
			} catch (MailException e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage());
			}
			
		
		
		
		return "Kao poslato";
	}
	
	
	
}

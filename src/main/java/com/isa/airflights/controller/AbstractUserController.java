package com.isa.airflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.dto.UserDTODjuka;
import com.isa.airflights.model.*;
import com.isa.airflights.repository.AbstractUserRepository;

import java.security.Principal;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Vazno, ovaj Controller je DEPRECATED, koristiti samo /test 
 * @author Dusan
 *
 */
@RestController
@RequestMapping(value="/api/abstractUsers")
@CrossOrigin(origins = "http://localhost:4200")
public class AbstractUserController {
	
	private Logger logger = LoggerFactory.getLogger(AbstractUserController.class);
	
	@Autowired
	private AbstractUserService abstractUserService;
	
	@Autowired
	private AbstractUserRepository userrep;
	
	@Autowired
    PasswordEncoder encoder;
	
	@RequestMapping("/test")
	public String test() {
		return "Ovo je test";
	}
	
	@RequestMapping("/whoami")
	@PreAuthorize("permitAll()")
	public Optional<AbstractUser> user(Principal user) {
		System.out.println(user);
		Optional<AbstractUser> a = this.abstractUserService.findByEmail(user.getName());
		
		System.out.println("DFLJDSLFKJSD " + a.get().getEmail());
		
		return this.abstractUserService.findByEmail(user.getName());
	}
	
	@RequestMapping("/logged")
	public ResponseEntity<AbstractUser> abstractUser(Principal user) {
		Optional<AbstractUser> u = abstractUserService.findByEmail(user.getName());
		System.out.println(u.get().getLastName() + "przime");
		AbstractUser uuser = new AbstractUser(u.get());
		System.out.println("a uloge -> "+uuser.getRoles().size());
		
		return new ResponseEntity<AbstractUser>(uuser, HttpStatus.OK);
	}
	
	@RequestMapping("/loggedById/{id}")
	public ResponseEntity<UserDTODjuka> getabstractUser(@PathVariable Long id) {
		
		AbstractUser a = abstractUserService.getOne(id);
		UserDTODjuka dto = new UserDTODjuka(a);
		
		System.out.println("STO: " + dto.getRentacar());
		
		
		return new ResponseEntity<UserDTODjuka>(dto , HttpStatus.OK);
	}
	
	@RequestMapping("/loggedByIdCompany/{id}")
	public ResponseEntity<Integer> getUser(@PathVariable Long id) {
		
		AbstractUser a = abstractUserService.getOne(id);
		//AbstractUserDTO dto = new AbstractUserDTO(a);
		
		System.out.println("STO: " + a.getIdRentACar());
		int pom = a.getIdRentACar();
		System.out.println("POM: " + pom);
		
		return new ResponseEntity<Integer>(pom , HttpStatus.OK);
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

		
		return "Poslato";
	}
	

	@RequestMapping(value= "/verify/{id}")
	public ResponseEntity<AbstractUser> verify(@PathVariable Long id) {
		AbstractUser user = abstractUserService.getOne(id);
		user.setVerify(true);
		AbstractUser retVal = new AbstractUser();
		System.out.println("Getovali smo pravog user/a: "+user.getEmail() + " " + user.getVerify());
		
		retVal = abstractUserService.save(user);
		
		return new ResponseEntity<AbstractUser>(retVal,HttpStatus.OK);
	}
	@RequestMapping(value="/checkFirstAttemp/{id}")
	public Boolean checkVerify(@PathVariable Long id) {
		AbstractUser user = userrep.getOne(id);
		if(user.isChangePass()) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value="/setNewPassword/{pass}/{id}")
	public boolean setNew(@PathVariable String pass, @PathVariable Long id) {
		 AbstractUser user = userrep.getOne(id);
		 user.setPassword(encoder.encode(pass));
		 user.setChangePass(true);
		 userrep.save(user);
		//AbstractUserDTO dto = new AbstractUserDTO(user);
		 return true;
		 
	}
	
	
}

package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.dto.AdminDTO;
import com.isa.airflights.dto.AirlineDTO;
import com.isa.airflights.dto.AirlineDTO2;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Airline;
import com.isa.airflights.model.Hotel;
import com.isa.airflights.model.Misc;
import com.isa.airflights.model.Role;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.AdminService;
import com.isa.airflights.service.AirlineService;
import com.isa.airflights.service.HotelService;
import com.isa.airflights.service.MiscService;
import com.isa.airflights.service.RentACarService;

@RestController
@RequestMapping(value="/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private RentACarService racService;
	
	@Autowired
	private AirlineService airService;
	
	@Autowired
	private HotelService hs;
	
	@Autowired
	private AirlineService ra;
	
	@Autowired
	private RentACarService rs;
	
	@Autowired
	private AbstractUserService auSrv;
	
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
	private MiscService ms;
    
    @Autowired
    private AirlineService fs;
	
	
    @GetMapping("/all")
    public Collection<AbstractUserDTO> getAllAdmins() {
    	Collection<AbstractUser> users = service.getAll();
        
    	List<AbstractUser> admins = users.stream().filter(u -> u.getRole()!=null && u.getRole().getId() != 1).collect(Collectors.toList());
    	List<AbstractUserDTO> usersDTO = new ArrayList<AbstractUserDTO>();
    	
    	for (AbstractUser u : admins) {
    		usersDTO.add(new AbstractUserDTO(u));
    	}
    	
    	return usersDTO;
    }
    
    
    @GetMapping("/allA")
    public Collection<AdminDTO> getAllA() {
    	Collection<AbstractUser> users = service.getAll();
        
    	List<AbstractUser> admins = users.stream().filter(u -> u.getRole()!=null && u.getRole().getId() != 1).collect(Collectors.toList());
    	List<AdminDTO> usersDTO = new ArrayList<AdminDTO>();
    	
    	for (AbstractUser u : admins) {
    		AdminDTO aa  = new AdminDTO(u);
    		
    		long i = u.getRole().getId();
    		
    		if (i == 2) {
    			aa.setKind("System admin");
    			aa.setCom("Sys");
    		} else if (i == 3) {
    			aa.setKind("Airline admin");
    			aa.setCom(ra.getAirline(u.getAirline().getId()).getFullName());
    		} else if (i == 4) {
    			aa.setKind("Hotel admin");
    			aa.setCom(hs.getOne(u.getHotel().getId()).getName());
    		} else if (i == 5) {
    			aa.setKind("Rent a car admin");
    			aa.setCom(rs.getOne(new Long(u.getIdCompany())).getName());
    		}
    		
    		usersDTO.add(aa);
    	}
    	
    	return usersDTO;
    }
    
    @Transactional
    @RequestMapping(value="/add", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> addAdmin(@RequestBody AbstractUser abstractUser) {
		
    	abstractUser.setPassword(encoder.encode(abstractUser.getPassword()));
    	
    	Role role = abstractUser.getRole();
    	abstractUser.getRoles().add(role);
    	abstractUser.setChangePass(false);
    	
    	Hotel hh = abstractUser.getHotel();
    	if (hh != null) {
    		hs.save(hh);
    	}
    	
    	/*if (abstractUser.getAirline()!=null) {
    		
    	} else if (abstractUser.getIdCompany()!=null) {
    		
    	} else if (abstractUser.getHotel()!=null) {
    		
    	} else {
    		// System admin
    		
    	}*/
    	
    	AbstractUserDTO au = new AbstractUserDTO(service.save(abstractUser));
    	
    	
    	
    	return new ResponseEntity<AbstractUserDTO>(au,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> getAdmin(@PathVariable Long id) {
		
    	AbstractUserDTO ret = new AbstractUserDTO(service.findOne(id));
    	
    	return new ResponseEntity<AbstractUserDTO>(ret, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/passUpdate", method = RequestMethod.PUT, 
    		consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> updatePass(@RequestBody AbstractUserDTO user) {
		
    	AbstractUser u = service.findOne(user.getId());
    	u.setPassword(encoder.encode(user.getNewPassword()));
    	
    	service.save(u);
    	
    	return new ResponseEntity<AbstractUserDTO>(new AbstractUserDTO(u), HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> delAdmin(@PathVariable Long id) {
		
    	service.remove(id);
    	
    	return new ResponseEntity<AbstractUserDTO>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/rac/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> delRac(@PathVariable Long id) {
		
    	racService.deleteRac(id);
    	
    	return new ResponseEntity<AbstractUserDTO>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/air/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> delAir(@PathVariable Long id) {
		
    	airService.deleteAir(id);
    	
    	return new ResponseEntity<AbstractUserDTO>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/updateAdmin", method = RequestMethod.PUT,
    		consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> updateA(@RequestBody AbstractUser u) {
		
    	AbstractUser au = auSrv.getOne(u.getId());
    	au.setFirstName(u.getFirstName());
    	au.setLastName(u.getLastName());
    	auSrv.save(au);
    	
    	return new ResponseEntity<AbstractUserDTO>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/misc", method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Misc>> getMisc() {
		

    	Optional<Misc> m = ms.getById(1L);
    	
    	
    	return new ResponseEntity<Optional<Misc>>(m,HttpStatus.OK);
    }

    @RequestMapping(value="/misc", method = RequestMethod.POST,  
    		consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Misc> upMisc(@RequestBody Misc m) {

    	Misc mm = ms.up(m);
    	
    	return new ResponseEntity<Misc>(mm,HttpStatus.OK);
    }
    
    @RequestMapping(value="/miscAll", method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Misc>> getAllMisc() {
    	
    	
    	return new ResponseEntity<List<Misc>>(ms.get(),HttpStatus.OK);
    }
    
    @RequestMapping(value="/getAL", method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirlineDTO2>> getAL() {
    	
    	List<Airline> aa = fs.getAllAdmin();
    	List<AirlineDTO2> ad = new ArrayList<>();
    	for (Airline a : aa) {
    		AirlineDTO2 l = new AirlineDTO2();
    		l.setActive(a.getActive());
    		l.setFullName(a.getFullName());
    		l.setAddress(a.getAddress());
    		l.setCity(a.getCity());
    		l.setGradeCount(a.getGradeCount());
    		l.setGradeSum(a.getGradeSum());
    		l.setId(a.getId());
    		ad.add(l);
    	}
    	
    	return new ResponseEntity<List<AirlineDTO2>>(ad,HttpStatus.OK);
    }
   
    
    
    
}

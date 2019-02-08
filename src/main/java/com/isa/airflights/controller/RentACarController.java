package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.RentACarDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.model.Room;
import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.model.Vehicle;
import com.isa.airflights.model.VehicleReservation;
import com.isa.airflights.repository.VehicleRepository;
import com.isa.airflights.repository.VehicleReservationRepository;
import com.isa.airflights.service.AbstractUserService;
import com.isa.airflights.service.RentACarService;

@RestController
@RequestMapping(value="/api/rentacar")
@CrossOrigin(origins = "http://localhost:4200")
public class RentACarController {

	@Autowired
	private RentACarService racService;
	
	@Autowired
	private AbstractUserService abs;
	
	@Autowired
	private VehicleRepository vr;
	
	@Autowired
	private VehicleReservationRepository vrr;
	
	@RequestMapping("/test")
	public ResponseEntity<List<RentACarDTO>> getAll() {
		List<RentACar> rac = racService.findAll();
		List<RentACarDTO> r = new ArrayList<RentACarDTO>();
		
		for (RentACar rentACar : rac) {
			r.add(new RentACarDTO(rentACar));
						
		}
		
		for (RentACarDTO rentACarDTO : r) {
			System.out.println("sum: " + rentACarDTO.getRatingsSum());
			System.out.println("count: " + rentACarDTO.getRatingsCount());
		}
				
		return new ResponseEntity<List<RentACarDTO>>(r,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<RentACarDTO> getOne(@PathVariable Long id) {
		System.out.println("Provera? " + id);
		RentACar r = racService.getOne(id);
		System.out.println("Ovde? " + r.getName());
		RentACarDTO dto = new RentACarDTO(r);
		System.out.println("Provera? " + dto.getName());
		return new ResponseEntity<RentACarDTO>(dto,HttpStatus.OK);
	}
	
	
	@RequestMapping("/search/{name}")
	public ResponseEntity<List<RentACarDTO>> getSearch(@PathVariable String name) {
		List<RentACar> rac = racService.findAll();
		List<RentACarDTO> r = new ArrayList<RentACarDTO>();
		
		for (RentACar rentACar : rac) {
			if(rentACar.getName().contains(name) || rentACar.getCity().contains(name))
				r.add(new RentACarDTO(rentACar));
			
		}
				
		return new ResponseEntity<List<RentACarDTO>>(r,HttpStatus.OK);
	}
		
    @RequestMapping(value="/", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentACar> addAgency(@RequestBody RentACar rac) {
		
    	RentACar added = racService.save(rac);
    	
    	return new ResponseEntity<RentACar>(added,HttpStatus.OK);
    }
    
    @GetMapping("/allRacs")
    public ResponseEntity<List<RentACarDTO>> getAllRacs() {
    	List<RentACar> rac = racService.findAll();
		List<RentACarDTO> r = new ArrayList<RentACarDTO>();
		
		for (RentACar rentACar : rac) {
			r.add(new RentACarDTO(rentACar));
		}
		
        return new ResponseEntity<List<RentACarDTO>>(r,HttpStatus.OK);
    }
    
    @RequestMapping(value="/chartWeek/{id}", method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Double>> weekChart(@PathVariable Long id) {
    	
    	List<Double> list = new ArrayList<Double>(7);
    	List<VehicleReservation> all = new ArrayList<>();
    	
    	//RentACar rac = racService.getOne(id);	
    	List<Vehicle> veh = vr.findAll();
    	List<Vehicle> vehFromRac = new ArrayList<>();
    	
    	for (Vehicle vehicle : veh) {
			if(vehicle.getRentacar().getId().equals(id)) {
				vehFromRac.add(vehicle);
			}
		}
    	
		for (Vehicle vehicle : vehFromRac) {
    		List<VehicleReservation> rrs = vrr.findByVehicle_id(vehicle.getId());	
    		for (VehicleReservation rr : rrs) {
    			all.add(rr);
    		}
    	}
    	int i = 0;
    	
    	
    	
    	while (i < 7) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, i-7);
			Date past = cal.getTime();
			
			Double d = 0.0;
			for (VehicleReservation rr : all) {
    			if (rr.getPickupdate().before(past) && rr.getDropoffdate().after(past)) {
    				d += 1;
    			}
    		}
			
			list.add(i, d);
			
			i++;
    	}
    	
    	
    	
    	return new ResponseEntity<List<Double>>(list,HttpStatus.OK);
    }
    
    @RequestMapping(value="/lastM/{id}", method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Double>> monthChart(@PathVariable Long id) {
    	List<Double> list = new ArrayList<Double>(5);
    	List<VehicleReservation> all = new ArrayList<>();
    	
    	List<Vehicle> veh = vr.findAll();
    	List<Vehicle> vehFromRac = new ArrayList<>();
    	
    	for (Vehicle vehicle : veh) {
			if(vehicle.getRentacar().getId().equals(id)) {
				vehFromRac.add(vehicle);
			}
		}
    	
		for (Vehicle vehicle : vehFromRac) {
    		List<VehicleReservation> rrs = vrr.findByVehicle_id(vehicle.getId());	
    		for (VehicleReservation rr : rrs) {
    			all.add(rr);
    		}
    	}
    	int i = 0;
   
    	while (i < 5) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -(5-i)*7);
			Date l = cal.getTime();
			cal.add(Calendar.DATE, 7);
			Date h = cal.getTime();
			
			Double d = 0.0;
			for (VehicleReservation rr : all) {
    			if ((rr.getPickupdate().after(l) && rr.getPickupdate().before(h))
    					|| (rr.getDropoffdate().after(l) && rr.getDropoffdate().before(h))) {
    				d += 1;
    			}
    		}
			
			list.add(i, d);
			
			i++;
    	}
    	
    	
    	
    	return new ResponseEntity<List<Double>>(list,HttpStatus.OK);
    	
    }
    
    @RequestMapping(value="/lastYear/{id}", method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Double>> yearChart(@PathVariable Long id) {
    	List<Double> list = new ArrayList<Double>(12);
    	List<VehicleReservation> all = new ArrayList<>();
    	
    	List<Vehicle> veh = vr.findAll();
    	List<Vehicle> vehFromRac = new ArrayList<>();
    	
    	for (Vehicle vehicle : veh) {
			if(vehicle.getRentacar().getId().equals(id)) {
				vehFromRac.add(vehicle);
			}
		}
    	
		for (Vehicle vehicle : vehFromRac) {
    		List<VehicleReservation> rrs = vrr.findByVehicle_id(vehicle.getId());	
    		for (VehicleReservation rr : rrs) {
    			all.add(rr);
    		}
    	}
    	int i = 0;
   
    	while (i < 12) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -(12-i)*30);
			Date l = cal.getTime();
			cal.add(Calendar.DATE, 30);
			Date h = cal.getTime();
			
			Double d = 0.0;
			for (VehicleReservation rr : all) {
    			if ((rr.getPickupdate().after(l) && rr.getPickupdate().before(h))
    					|| (rr.getDropoffdate().after(l) && rr.getDropoffdate().before(h))) {
    				d += 1;
    			}
    		}
			
			list.add(i, d);
			
			i++;
    	}
    	
    	
    	return new ResponseEntity<List<Double>>(list,HttpStatus.OK);
    	
    }

	
	
}

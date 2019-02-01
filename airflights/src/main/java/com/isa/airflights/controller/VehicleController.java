package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.RentACarDTO;
import com.isa.airflights.dto.VehicleDTO;
import com.isa.airflights.model.RentACar;
import com.isa.airflights.model.Vehicle;
import com.isa.airflights.service.BranchLocationsService;
import com.isa.airflights.service.RentACarService;
import com.isa.airflights.service.VehicleService;

@RestController
@RequestMapping(value="/api/vehicle")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

	@Autowired
	private VehicleService vs;
	
	@Autowired
	private BranchLocationsService bs;
	
	@Autowired
	private RentACarService rs;
	
	@RequestMapping("/test")
	public ResponseEntity<List<VehicleDTO>> getAll() {
		System.out.println("sadfsdaf");
		List<Vehicle> v = vs.findAll();
		
		List<VehicleDTO> vehicledto = new ArrayList<>();
		
		for (Vehicle rentACar : v) {
			vehicledto.add(new VehicleDTO(rentACar));
			
		}
		
		System.out.println("Pred return");
		return new ResponseEntity<>(vehicledto,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addVehicle/{id1}/{id2}", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehicleDTO> addVehicle(@RequestBody Vehicle vehicle,@PathVariable Long id1,@PathVariable Long id2) {
		/*System.out.println("Usao u metodu" + vehicle.getName() + " dsafasdf " + vehicle.getId());
		List<Vehicle> lista = vs.findAll();
		Vehicle poslednji = new Vehicle();
		System.out.println("lista size: " + lista.size());
		poslednji = lista.get(lista.size() - 1);
		System.out.println("Poslednji id: " + poslednji.getId());
		Long pom = poslednji.getId() + 1;
		vehicle.setId(pom);*/
		vehicle.setRentacar(rs.getOne(id1));
		vehicle.setBranch_locations(bs.getOne(id2));

		
		VehicleDTO vdto = new VehicleDTO();

		vdto.setName(vehicle.getName());
		vdto.setBrand(vehicle.getBrand());
		vdto.setModel(vehicle.getModel());
		vdto.setYear(vehicle.getYear());
		vdto.setSeats(vehicle.getSeats());
		vdto.setType(vehicle.getType());
		vdto.setPrice(vehicle.getPrice());
		vdto.setRentACarId(vehicle.getRentacar().getId());
		vdto.setBranchOffice_id(vehicle.getBranch_locations().getId());
		vdto.setRating(0);
		vdto.setReserved(true);
		vdto.setDiscount(0);
		/*
		Vehicle v = new Vehicle();
		v.setId((long) 4);
		v.setName(vehicle.getName());
		v.setBrand(vehicle.getBrand());
		v.setModel(vehicle.getModel());
		v.setYear(vehicle.getYear());
		v.setSeats(vehicle.getSeats());
		v.setType(vehicle.getType());
		v.setPrice(vehicle.getPrice());
		v.setRentacar(vehicle.getRentacar());
		v.setBranch_locations(vehicle.getBranch_locations());
		v.setRating(0);
		v.setReserved(false);
		
		System.out.println("GetBranch: " + v.getBranch_locations());*/
		
		vs.save(vehicle);
		
		return new ResponseEntity<VehicleDTO>(vdto,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle){
		Vehicle v = vs.getOne(vehicle.getId());
		if(v != null){
			Vehicle u = vs.update(v, vehicle);
			if(u == null) {
				return null;
			} else {
				return new ResponseEntity<Vehicle>(new Vehicle(u), HttpStatus.OK );
			}
			
		}else{
			return null;
		}
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?>  deleteVehicle(@PathVariable Long id) {
		Vehicle v = vs.delete(id);
		if(v == null) {
			//nismo obrisali
			System.out.println("Objekat je nepostojeci li je reservisan");
			return new ResponseEntity<Boolean>(false,HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Metoda koja vraca sva vozila iz rent a car-a sa datim id
	 * */
	@RequestMapping(value="/getAll/{id}")
	public ResponseEntity<List<VehicleDTO>>getAll(@PathVariable Long id) {
		
		List<Vehicle> lista = vs.findAll();
		List<VehicleDTO> listaDTO = new ArrayList<>();
		for (Vehicle vehicle : lista) {
			if(vehicle.getRentacar().getId().equals(id)) {
				VehicleDTO vdto = new VehicleDTO(vehicle);
				listaDTO.add(vdto);
			}
		}

		
		
		
		return new ResponseEntity<List<VehicleDTO>>(listaDTO,HttpStatus.OK);
	}
	
	/**
	 * Metoda koja vraca vozila koja su na popustu u svim rent a car servisima u gradu
	 * koja je destination za korisnika.
	 * */
	@RequestMapping(value="/getAllDiscount/{id}/{name}")
	public ResponseEntity<List<VehicleDTO>>getAllDiscount(@PathVariable Long id,@PathVariable String name) {
		
		List<Vehicle> lista = vs.findAll();
		List<VehicleDTO> listaDTO = new ArrayList<>();
		
		/*List<RentACar> listaRac = rs.findByCity(name);
		List<RentACarDTO> listaRacDto = new ArrayList<>();*/
		
		
		for (Vehicle vehicle : lista) {
			System.out.println("Popus? " + vehicle.getDiscount() + " dslfkjaslkdf " + vehicle.getRentacar().getCity() + " sdafsdaf " + name );
				if(vehicle.getDiscount() != 0 && vehicle.getRentacar().getCity().equals(name)) {
					VehicleDTO vdto = new VehicleDTO(vehicle);
					listaDTO.add(vdto);
					System.out.println("IMa ih?");
					
				}

		}

		System.out.println("Bio sam ovde sta god bilo");
		return new ResponseEntity<List<VehicleDTO>>(listaDTO,HttpStatus.OK);
	}
	
}

package com.isa.airflights.controller;

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

import com.isa.airflights.model.PromoRoom;
import com.isa.airflights.model.Room;
import com.isa.airflights.service.PromoRoomService;
import com.isa.airflights.service.RoomService;

@RestController
@RequestMapping(value="/api/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

	@Autowired
	private RoomService service;
	
	@Autowired
	private PromoRoomService promoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)     
	public ResponseEntity<Room> getRoom(@PathVariable("id") Long id) {
		
    	Room r = service.getOne(id);
    	
    	return new ResponseEntity<Room>(r, HttpStatus.OK);
    }
	
	@RequestMapping(value="/", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)     
	public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
		
    	Room r = service.update(room);
    	
    	return new ResponseEntity<Room>(r, HttpStatus.OK);
    }
	
	@RequestMapping(value="/add", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
		
    	Room r = service.save(room);
    	
    	return new ResponseEntity<Room>(r, HttpStatus.CREATED);
    }
	
	/**
	 * Uzima sobe koje se nalaze u hotelu identifikovanom sa id
	 */
    @RequestMapping(value="/hotel/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getRoomsInHotel(@PathVariable("id") Long id) {
    	
    	List<Room> rooms = service.getRoomByHotel(id);
    	
    	return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
    }
    
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)     
	public ResponseEntity<Room> deleteRoom(@PathVariable("id") Long id) {
		
    	service.delete(id);
    	
    	return new ResponseEntity<Room>(HttpStatus.OK);
    }
	
	@RequestMapping(value="/promoExtra", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PromoRoom> addPromoExtra(@RequestBody PromoRoom promo) {
		
    	PromoRoom p = promoService.save(promo);
    	
    	return new ResponseEntity<PromoRoom>(p, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/endPromo", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<Room> endPromo(@RequestBody Room room) {
		
    	Room r = service.endPromo(room);
    	   	
    	return new ResponseEntity<Room>(r, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/cleanPR/{id}", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<PromoRoom>> cleanPromoRoom(@PathVariable("id") Long id) {
		
    	List<PromoRoom> del = promoService.deletyByRoom(id);
    	
    	return new ResponseEntity<List<PromoRoom>>(del, HttpStatus.CREATED);
    }
}

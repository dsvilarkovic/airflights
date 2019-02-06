package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.isa.airflights.model.Condition;
import com.isa.airflights.model.PromoRoom;
import com.isa.airflights.model.Room;
import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.model.SearchObject;
import com.isa.airflights.service.PromoRoomService;
import com.isa.airflights.service.RoomReservationService;
import com.isa.airflights.service.RoomService;

@RestController
@RequestMapping(value="/api/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

	@Autowired
	private RoomService service;
	
	@Autowired
	private PromoRoomService promoService;
	
	@Autowired
	private RoomReservationService rrService;
	
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
	
	@RequestMapping(value="/{id}/searchPromos", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<Room>> searchPromoRoom(@PathVariable("id") Long id, @RequestBody SearchObject obj) {
		
		List<Room> rooms = service.getRoomByHotel(id);
		
		Date from = new GregorianCalendar(obj.getStartY(), obj.getStartM()-1, obj.getStartD()).getTime();
    	Date to = new GregorianCalendar(obj.getEndY(), obj.getEndM()-1, obj.getEndD()).getTime();
		
    	List<Room> ret = new ArrayList<Room>();
		for (Room r : rooms) {
			if (r.getPromo() && isRoomA(r, from, to)) {
				ret.add(r);
			}
		}

    	return new ResponseEntity<List<Room>>(ret, HttpStatus.OK);
    }
	
	@RequestMapping(value="/{id}/searchRooms", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<Room>> searchRooms(@PathVariable("id") Long id, @RequestBody SearchObject obj) {
		
		List<Room> rooms = service.getRoomByHotel(id);
		
		// Provera da li je soba slobodna u zadatom periodu
		Date from = new GregorianCalendar(obj.getStartY(), obj.getStartM()-1, obj.getStartD()).getTime();
    	Date to = new GregorianCalendar(obj.getEndY(), obj.getEndM()-1, obj.getEndD()).getTime();
		
    	List<Room> free = new ArrayList<Room>();
		for (Room r : rooms) {
			if (r.getPromo() && isRoomA(r, from, to)) {
				free.add(r);
			}
		}
		
		// Filter po ukupnom broju osoba
		if (obj.getPersons() != null && obj.getPersons() > 0) {
			if (sumBeds(free) < obj.getPersons()) {
				return new ResponseEntity<List<Room>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
			}
		}
		
		//Filter po ceni
		List<Room> fp = new ArrayList<Room>(free);
		if (obj.getPf() != null && obj.getPt() != null) {
			for (Room r : free) {
				Double price = r.getPrice();
				if (!(price > obj.getPf() && price < obj.getPt())) {
					fp.remove(r);
				}
			}
		}
		
		// Filter po uslovima za sobe
		for (Condition c : obj.getConditions()) {
			Integer n = countRooms(fp, c.getNb());
			if (n < c.getNr()) {
				return new ResponseEntity<List<Room>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
			}
		}

    	return new ResponseEntity<List<Room>>(fp, HttpStatus.OK);
    }

	private boolean isRoomA(Room r, Date from, Date to) {
		List<RoomReservation> reservations = rrService.getByRoom(r.getId());
		
		for (RoomReservation reservation : reservations) {
			Date exf = reservation.getStartDate();
			Date ext = reservation.getEndDate();
			
			if (!(exf.after(to) || ext.before(from)))  {
				return false;
			}
		}
		
		return true;
	}
	
	private Integer sumBeds(List<Room> rooms) {
		Integer ret = 0;
		for (Room r : rooms) {
			ret += r.getBeds();
		}
		
		return ret;
	}
	
	private Integer countRooms(List<Room> rooms, Integer beds) {
		Integer ret = 0;
		for (Room r : rooms) {
			if (beds.equals(r.getBeds())) {
				ret += 1;
			}
		}
		
		return ret;
	}
}

package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.isa.airflights.model.HotelExtras;
import com.isa.airflights.model.ReservationPackage;
import com.isa.airflights.model.Room;
import com.isa.airflights.model.RoomResExtras;
import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.repository.ReservationPackageRepository;
import com.isa.airflights.repository.RoomReservationRepository;

@Service
public class RoomReservationService {
	
	@Autowired
	private RoomReservationRepository repository;
	
	@Autowired
	private ReservationPackageRepository packageRepo;
	
	@Autowired
	private ExtrasService xS;
	
	public List<RoomReservation> getAll() {
		
		return repository.findAll();
	}

	public List<RoomReservation> getByRoom(Long room_id) {
		return repository.findByRoom_id(room_id);
	}
	
	public ReservationPackage getRP(Long id) {
		return packageRepo.getOne(id);
	}
	
	@Transactional(readOnly=false)
	public RoomReservation save(RoomReservation r) {
		
		return repository.save(r);
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)
	public RoomReservation saveFull(RoomReservation rr, List<HotelExtras> extrasRealList) {
		

		Room room = rr.getRoom();
		List<RoomReservation> rres = repository.findByRoom_id(room.getId());
		// Provera slobodnosti
		
		for (RoomReservation z : rres) {
			if (rr.getEndDate().before(z.getStartDate()) || rr.getStartDate().after(z.getEndDate())) {
				continue;
			} else {
				return null;
			}
		}
		
		RoomReservation r9 = repository.save(rr);
		
		for (HotelExtras e : extrasRealList) {
			RoomResExtras r = new RoomResExtras();
			r.setRoom_res(r9);
			r.setExtras(e);
			xS.saveResExtra(r);
		}
		
		
		return r9;
	}

	public List<RoomReservation> findAll() {
		return repository.findAll();
	}
	
	
	
}

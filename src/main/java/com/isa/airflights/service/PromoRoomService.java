package com.isa.airflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.model.PromoRoom;
import com.isa.airflights.repository.PromoRoomRepository;

@Service
public class PromoRoomService {
	
	@Autowired
	private PromoRoomRepository repository;
	
	public PromoRoom save(PromoRoom promo) {
		return repository.save(promo);
	}

	public List<PromoRoom> deletyByRoom(Long room_id) {
		return repository.deleteByRoom_id(room_id);
	}
	
	public List<PromoRoom> getByRoom(Long room_id) {
		return repository.findByRoom_id(room_id);
	}

}

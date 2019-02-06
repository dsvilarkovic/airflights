package com.isa.airflights.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isa.airflights.model.PromoRoom;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface PromoRoomRepository extends JpaRepository<PromoRoom, Long> {

	@Transactional
	List<PromoRoom> deleteByRoom_id(Long room_id);
	
}

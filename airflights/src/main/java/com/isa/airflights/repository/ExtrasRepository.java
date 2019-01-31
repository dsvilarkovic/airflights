package com.isa.airflights.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isa.airflights.model.HotelExtras;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ExtrasRepository extends JpaRepository<HotelExtras, Long> {
	
	List<HotelExtras> findByHotel_id(Long hotel_id);

}

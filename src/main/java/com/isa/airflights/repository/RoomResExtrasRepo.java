package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isa.airflights.model.RoomResExtras;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface RoomResExtrasRepo extends JpaRepository<RoomResExtras, Long> {

}

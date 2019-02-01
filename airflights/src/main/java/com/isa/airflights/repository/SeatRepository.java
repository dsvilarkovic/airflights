package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.airflights.model.Seat;

public interface SeatRepository  extends JpaRepository<Seat, Long>{

}

package com.isa.airflights.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.VehicleReservation;

@Repository
public interface VehicleReservationRepository  extends JpaRepository<VehicleReservation, Long> {

}

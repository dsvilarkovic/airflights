package com.isa.airflights.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.RentACar;
import com.isa.airflights.model.RoomReservation;
import com.isa.airflights.model.VehicleReservation;

@Repository
public interface VehicleReservationRepository  extends JpaRepository<VehicleReservation, Long> {

	@Query("select b from VehicleReservation b where b.rentacar = ?1 and ((b.pickupdate between ?2 and ?3) or (b.dropoffdate between ?2 and ?3) or (b.pickupdate < ?2 and b.dropoffdate > ?3))")
    List<VehicleReservation> findAllReserved(RentACar r, Date pickupdate, Date dropoffdate);	
	
	@Query("select b from VehicleReservation b where b.rentacar = ?1 and (b.reservationdate between ?2 and ?3)")
	List<VehicleReservation> getAllByDate(RentACar r,Date pickupdate, Date dropoffdate);
	
	public List<VehicleReservation> findByVehicle_id(Long vehicle_id);
}

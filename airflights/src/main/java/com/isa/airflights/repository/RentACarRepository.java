package com.isa.airflights.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.isa.airflights.model.*;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, Long> {
	
	
	
}

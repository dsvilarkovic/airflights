package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.rating.RentACarRating;

@Repository
public interface RentACarRatingRepository extends JpaRepository<RentACarRating, Long> {

}

package com.isa.airflights.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.LuggagePrice;

@Repository
public interface LuggagePriceRepository extends JpaRepository<LuggagePrice, Long> {


	Page<LuggagePrice> findAllByLuggagePriceList_Airline_Id(Long airline_id, Pageable pageRequest);

}

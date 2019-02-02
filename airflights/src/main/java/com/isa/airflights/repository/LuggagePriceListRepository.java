package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.isa.airflights.model.LuggagePriceList;


@Repository
public interface LuggagePriceListRepository extends JpaRepository<LuggagePriceList, Long> {
	
}

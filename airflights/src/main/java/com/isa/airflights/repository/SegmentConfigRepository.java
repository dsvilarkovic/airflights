package com.isa.airflights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.airflights.model.SegmentConfig;

@Repository
public interface SegmentConfigRepository extends JpaRepository<SegmentConfig, Long>  {

}

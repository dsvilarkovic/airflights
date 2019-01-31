package com.isa.airflights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.airflights.repository.SegmentConfigRepository;

@Service
public class SegmentConfigService {

	@Autowired
	SegmentConfigRepository segmentConfigRepository;
}

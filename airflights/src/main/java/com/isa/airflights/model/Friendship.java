package com.isa.airflights.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Friendship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Friend_to
	 */
	//TODO: dodati kad @Djuka sredi svoje
	
	/**
	 * Friend_of
	 */
	//TODO: dodati kad @Djuka sredi svoje
}

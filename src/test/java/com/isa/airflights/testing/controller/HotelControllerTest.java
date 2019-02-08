package com.isa.airflights.testing.controller;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

public class HotelControllerTest {
	
	private static final String URL_PREFIX = "/api/rentacar";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	
}

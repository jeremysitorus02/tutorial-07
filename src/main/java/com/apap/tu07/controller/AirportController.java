package com.apap.tu07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tu07.rest.Setting;

@RestController
@RequestMapping("/airport")
public class AirportController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@GetMapping(value= "/kota/{namaKota}")
	public ResponseEntity<String> getStatus(@PathVariable("namaKota") String kota) {
		String path = Setting.airportUrl+"?apikey="+Setting.airportApikey+"&term="+kota+"&country=ID";
		System.out.println(path);
		ResponseEntity<String> response = restTemplate.getForEntity(path, String.class);
		return response;
	}
}
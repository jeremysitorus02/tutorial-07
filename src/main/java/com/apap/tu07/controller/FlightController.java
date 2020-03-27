package com.apap.tu07.controller;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tu07.model.FlightModel;
import com.apap.tu07.model.PilotModel;
import com.apap.tu07.service.FlightService;
import com.apap.tu07.service.PilotService;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;

    
    @PostMapping(value = "/add/{licenseNumber}")
    private FlightModel addFlight(@PathVariable(value = "licenseNumber") String licenseNumber,
    		@RequestBody FlightModel flight, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
        return flightService.addFlight(flight);
    }

    @GetMapping(value = "/view/{flightNumb}")
    private FlightModel flightView(@PathVariable("flightNumb") String flightNumb) {
        FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumb).get();
        return flight;
    }
    
    @GetMapping(value = "/all")
    private List<FlightModel> flightViewAll(@ModelAttribute FlightModel flight) {
        return flightService.getAllFlight();
    }
       
    @DeleteMapping(value = "/{flightNumb}")
    private String deleteFlight(@PathVariable("flightNumb") String flightNumb) {
        FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumb).get();
        flightService.deleteFlight(flight);
        return "flight has been deleted";
    }
    
    @PutMapping(value = "/update/{flightNumb}")
    private String updateFlight(@PathVariable("flightNumb") String flightNumb,
    		@RequestParam("destination") String dest,
    		@RequestParam("origin") String ori,
    		@RequestParam("time") Date time) {
        FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumb).get();
        if(flight.equals(null)) {
        	return "Couldn't find your flight";
        }
        flight.setDestination(dest);
        flight.setOrigin(ori);
        flight.setTime(time);
        flightService.updateFlight(flightNumb, flight);
        return "flight update success";
    }
}
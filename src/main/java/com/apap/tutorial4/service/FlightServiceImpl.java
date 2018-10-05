package com.apap.tutorial4.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDB;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDB;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDB.save(flight);
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDB.findByFlightNumber(flightNumber);
	}

	@Override
	public List<FlightModel> getFlightList() {
		List<FlightModel> archiveFlight = flightDB.findAll();
		return archiveFlight;
	}

	@Override
	public void deleteFlight(long id) {
		flightDB.deleteById(id);
		
	}

	@Override
	public void saveFlight(FlightModel flight) {
		flightDB.save(flight);
		
	}
}

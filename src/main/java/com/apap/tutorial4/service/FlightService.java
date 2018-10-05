package com.apap.tutorial4.service;

import java.util.List;

import com.apap.tutorial4.model.FlightModel;

public interface FlightService {
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	List<FlightModel> getFlightList();
	void deleteFlight(long id);
	void addFlight(FlightModel flight);
}

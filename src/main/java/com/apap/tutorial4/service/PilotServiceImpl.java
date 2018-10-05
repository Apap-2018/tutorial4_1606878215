package com.apap.tutorial4.service;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDB;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDB.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDB.save(pilot);
	}

	@Override
	public List<PilotModel> getPilotList() {
		List<PilotModel> archivePilot = pilotDB.findAll();
		return archivePilot;
	}
	
	@Override
	public void deletePilot(long id) {
		pilotDB.deleteById(id);
		
	}
	
}

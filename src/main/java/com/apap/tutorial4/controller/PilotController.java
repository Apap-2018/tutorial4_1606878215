package com.apap.tutorial4.controller;
import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		try {
			PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);	
			model.addAttribute("pilot", archive);
			model.addAttribute("flightList", archive.getPilotFlight());
			return "view-pilot";
		} catch (NullPointerException e) {
			return "error";
		}
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> pilotList = pilotService.getPilotList();
		model.addAttribute("pilotList", pilotList);
		return "list-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "id") long id, Model model) {
		pilotService.deletePilot(id);
		return "delete";
	}
	
	@RequestMapping("/pilot/update")
	public String updatePilotSubmit(@RequestParam("licenseNumber") String licenseNumber, @RequestParam("newName") String newName, @RequestParam("newFlyHour") int newFlyHour, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);	
		archive.setName(newName);
		archive.setFlyHour(newFlyHour);
		pilotService.savePilot(archive);
		return "update";
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		model.addAttribute("licenseNumber", licenseNumber);
		return "updatePilot";
	}
}

package com.central.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.app.model.AppointmentResponse;
import com.central.app.model.CentralInput;
import com.central.app.service.CentralService;

@RestController
@RequestMapping("/central")
public class CentralApplicationController {

	private static final Logger LOGGER = LogManager.getLogger(CentralApplicationController.class);

	@Autowired
	private CentralService centralService;

	@GetMapping("/trial")
	public String helloWorld() {
		return "Hello world";
	}

	@PostMapping("/appointment/create")
	public List<AppointmentResponse> createAppointment(@RequestBody final List<CentralInput> itemList) {
		LOGGER.info("createAppointment");
		return centralService.createAppointment(itemList);
	}

}

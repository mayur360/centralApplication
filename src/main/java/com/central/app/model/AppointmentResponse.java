package com.central.app.model;

import lombok.Data;

@Data
public class AppointmentResponse {
	private String message;
	private Long appointmentId;
	private Long personalId;
	private String centralStatus;
	
}

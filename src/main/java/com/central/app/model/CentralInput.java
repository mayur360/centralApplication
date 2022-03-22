package com.central.app.model;

import java.util.Date;

import lombok.Data;

@Data
public class CentralInput {

	private Long appointmentId;
	private Long personalId;
	private Date appointmentTime;
	private String appointmentState;
	private Long reasonId;
	private Long providerId;
	private String location;
	private String resource;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;

}

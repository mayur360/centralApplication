package com.central.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AppointmentEntity {
	@Id
	private Long appointmentId;
	private Long personalId;
	private LocalDateTime appointmentTime;
	private String appointmentState;
	private String status;
	private Long reasonId;
	private String reasonDescription;
	private Long providerId;
	private String location;
	private String resource;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	
}

package com.central.app.service;

import java.util.List;

import com.central.app.entity.AppointmentEntity;
import com.central.app.model.AppointmentResponse;
import com.central.app.model.CentralInput;

public interface CentralService {

	List<AppointmentResponse> createAppointment (List<CentralInput> itemList);
	
}

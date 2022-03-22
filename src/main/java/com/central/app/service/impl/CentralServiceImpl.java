package com.central.app.service.impl;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.app.constants.CentralConstants;
import com.central.app.entity.AppointmentEntity;
import com.central.app.model.AppointmentResponse;
import com.central.app.model.CentralInput;
import com.central.app.repo.AppointmentRepository;
import com.central.app.service.CentralService;
import com.central.app.util.CentralUtils;

@Service
public class CentralServiceImpl implements CentralService {
	
	private static final Logger LOGGER = LogManager.getLogger(CentralServiceImpl.class);

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private CentralUtils centralUtils;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AppointmentResponse> createAppointment(final List<CentralInput> itemList) {
		LOGGER.info("Inside createAppointment");
		if (itemList != null && !itemList.isEmpty()) {
			List<AppointmentResponse> result = new ArrayList<>();
			
			for (CentralInput inputItem : itemList) {
				AppointmentResponse response = new AppointmentResponse();
				AppointmentEntity appointment = appointmentRepository.findByAppointmentId(inputItem.getAppointmentId());
				AppointmentEntity savedAppointmentEntity;

				if (appointment == null) {
					LOGGER.info("Creating New Appointment");
					savedAppointmentEntity = createNewAppointment(inputItem);
					response.setCentralStatus(CentralConstants.APPOINTMENT_STATUS_CREATED);
					response.setMessage("Appointment Created for :: " + inputItem.getAppointmentId());
				} else {
					LOGGER.info("Updating Existing Appointment");
					savedAppointmentEntity = updateExistingAppointment(inputItem, appointment);
					response.setCentralStatus(CentralConstants.APPOINTMENT_STATUS_CREATED);
					response.setMessage("Appointment Updated for :: " + inputItem.getAppointmentId());
				}
				LOGGER.info("Before saving record");
				appointmentRepository.save(savedAppointmentEntity);
				response.setAppointmentId(savedAppointmentEntity.getAppointmentId());
				response.setPersonalId(savedAppointmentEntity.getPersonalId());
				result.add(response);
				LOGGER.info("Appointment Details saved");
			}
			return result;
		}
		return null;
	}

	private AppointmentEntity updateExistingAppointment(CentralInput inputItem, AppointmentEntity appointment) {
		modelMapper.map(inputItem, appointment);
		appointment.setStatus(CentralConstants.APPOINTMENT_STATUS_UPDATED);
		appointment.setReasonDescription(centralUtils.getReasonDesc(inputItem.getReasonId()));
		appointment.setAppointmentTime(
				inputItem.getAppointmentTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		return appointment;
	}

	private AppointmentEntity createNewAppointment(CentralInput inputItem) {
		AppointmentEntity newAppointment = modelMapper.map(inputItem, AppointmentEntity.class);
		newAppointment.setStatus(CentralConstants.APPOINTMENT_STATUS_CREATED);
		newAppointment.setReasonDescription(centralUtils.getReasonDesc(inputItem.getReasonId()));
		newAppointment.setAppointmentTime(
				inputItem.getAppointmentTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		return newAppointment;
	}

}

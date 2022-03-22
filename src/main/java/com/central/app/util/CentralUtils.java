package com.central.app.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.central.app.constants.CentralConstants;

@Component
public class CentralUtils {

	private final Map<Long, String> reasonDescMap = new HashMap<>();
	
	@PostConstruct
	private void configureReasonDescriptionMap() {
		
		reasonDescMap.put(10L, CentralConstants.REASON_DESC_10);
		reasonDescMap.put(20L, CentralConstants.REASON_DESC_20);
		reasonDescMap.put(30L, CentralConstants.REASON_DESC_30);
		reasonDescMap.put(40L, CentralConstants.REASON_DESC_40);
	}
	
	public String getReasonDesc(Long reasonId) {
		return reasonDescMap.get(reasonId);
	}
	
}

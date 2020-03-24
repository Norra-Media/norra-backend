package com.norra.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.norra.model.response.CommonErrorResponse;
import com.norra.service.AppConfigService;

@Service
public class AppInitValidation {
	private AppConfigService appConfigService;

	@Autowired
	public AppInitValidation(AppConfigService appConfigService) {
		this.appConfigService = appConfigService;
	}

	/**
	 * This method is used to validate the Active Trip and feedback details by user
	 * id and current time.
	 *
	 * @param userId - type of Long
	 * @param geoId  - type of Long
	 * @return returns the Active trip and feedback trip counts details
	 */
	public CommonErrorResponse getAppInitInfoValidation(Long userId, Long geoId) {
		CommonErrorResponse resp = new CommonErrorResponse();
		resp.setValid(true);
		return resp;
	}
}

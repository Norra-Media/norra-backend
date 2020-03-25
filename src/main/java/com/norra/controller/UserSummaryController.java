package com.norra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.norra.constants.Constants;
import com.norra.exceptions.CustomAppException;
import com.norra.model.request.User;
import com.norra.model.response.ResponseModel;
import com.norra.service.UserSummaryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user-summary")
public class UserSummaryController {

	private UserSummaryService userSummaryService;

	@Autowired
	public UserSummaryController(UserSummaryService userSummaryService) {
		this.userSummaryService = userSummaryService;
	}


	/**
	 * This api is used to get user summary details
	 *
	 * @param userId object of type Long
	 * @return returns weekly interest and number of ride taken/offered
	 */
	@GetMapping
	public ResponseEntity<ResponseModel> getUserSummary(@RequestParam Long userId) {
		try {
			return new ResponseEntity<ResponseModel>(userSummaryService.getUserSummary(userId),
					HttpStatus.OK);
		} catch (CustomAppException e) {
			return new ResponseEntity<>(new ResponseModel(Constants.STATUS_FAILED, e.getAddtionalMessage(), null,
					e.getAppError()), e.getHttpStatus());
		}
	}


	/**
	 * This api is used to store the user summary to redis based on userId.
	 * 
	 * @return returns user response entity
	 */
	@PostMapping
	public void userSummary(@RequestBody User user) {
		userSummaryService.userSummary(user);
	}

}

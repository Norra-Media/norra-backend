package com.norra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.norra.config.DataSource;
import com.norra.constants.Constants;
import com.norra.entities.UserSummary;
import com.norra.enums.AppErrorCodes;
import com.norra.enums.UserType;
import com.norra.exceptions.ExceptionLogger;
import com.norra.model.request.User;
import com.norra.model.response.AppError;
import com.norra.model.response.ResponseModel;
import com.norra.model.response.UserSummaryResponse;
import com.norra.repositories.UserSummaryRepository;
import com.norra.util.RedisUtil;
import com.norra.util.Util;
import com.norra.validation.UserSummaryValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserSummaryServiceImpl implements UserSummaryService {

	UserSummaryRepository userSummaryRepository;
	private RedisUtil redisUtil;
	private UserSummaryValidation userSummaryValidation;
	private AppConfigService appConfigService;
	private PushNotificationService pushNotificationService;


	@Autowired
	public UserSummaryServiceImpl(UserSummaryRepository userSummaryRepository, RedisUtil redisUtil,
			UserSummaryValidation userSummaryValidation, 
								  AppConfigService appConfigService, 
								   PushNotificationService pushNotificationService
								  ) {
		this.userSummaryRepository = userSummaryRepository;
		this.redisUtil = redisUtil;
		this.userSummaryValidation = userSummaryValidation;
		this.appConfigService = appConfigService;
		this.pushNotificationService = pushNotificationService;
	}

	/**
	 * Gets the user summary by user id.
	 * 
	 * @param userId the user id
	 * @return the user summary by user id
	 */
	@DataSource(Constants.DB_READ_REPLICA_NAME)
	@Override
	public UserSummary getUserSummaryByUserId(Long userId) {
		return userSummaryRepository.getUserSummaryByUserId(userId);
	}

	/**
	 * This method is used to add or update rating and reviews for particular user
	 * based on guest or host
	 * 
	 * @param userId   used id of type Long
	 * @param userType user type of type String
	 * @param rating   rating of type Float
	 * @return returns response entity
	 */
	@Override
	public ResponseModel updateUserSummary(Long userId, UserType userType, Float rating, int rideTaken, int rideOffered,
			Long tripId) {
		ResponseModel resp = new ResponseModel();
		UserSummary userSummary = userSummaryRepository.findByUserId(userId);
		if (userSummary == null) {
			userSummary = new UserSummary();
			userSummary.setUserId(userId);
		}
		userSummaryRepository.save(userSummary);
		resp.setStatus(Constants.STATUS_SUCCESS);
		resp.setMessage(Constants.USER_RATING_UPDATED);
		resp.setData(userSummary);

		return resp;
	}

	
	/**
	 * This method is used to store the user summary to redis based on userId.
	 * 
	 * @return returns user object
	 */
	@Override
	public User userSummary(User user) {
		try {
			if (user.getId() != null) {
				redisUtil.saveUserDataInCache(user);
			}
		} catch (Exception e) {
			log.info("server error in bounce pool profile api" + e.getMessage());
			ExceptionLogger.logError(e);
		}
		return user;
	}

	/**
	 * This method is used to return the user summary data based on user id
	 *
	 * @param userId user id of type Long
	 * @return returns response model
	 */
	@Override
	@DataSource(Constants.DB_READ_REPLICA_NAME)
	public ResponseModel getUserSummary(Long userId) {
		ResponseModel resp = new ResponseModel();
		UserSummary userSummary = getUserSummaryByUserId(userId);
		UserSummaryResponse userSummaryResponse = new UserSummaryResponse();
		if (userId != null) {
			if (userSummary != null) {
				resp.setStatus(Constants.STATUS_SUCCESS);
				resp.setMessage(Constants.USER_SUMMARY);
				resp.setData(userSummaryResponse);
			} else {
				resp.setStatus(Constants.STATUS_FAILED);
				resp.setMessage(Constants.USER_SUMMARY_NOT_FOUND);
				resp.setErrors(new AppError(AppErrorCodes.E400.getValue(), resp.getMessage()));
			}
		} else {
			resp.setStatus(Constants.STATUS_FAILED);
			resp.setMessage(Constants.USER_ID_REQUIRED);
			resp.setErrors(new AppError(AppErrorCodes.E400.getValue(), resp.getMessage()));
		}
		return resp;
	}


	/**
	 * This method is used to fetch user details from cache
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public ResponseModel getUserDetailsFromCache(Long userId) {
		ResponseModel model = new ResponseModel();
		try {
			User user = this.redisUtil.getUserDataById(userId);
			model.setData(user);
			model.setMessage(Constants.USER_DETAILS);
			model.setStatus(Constants.STATUS_SUCCESS);
		} catch (Exception e) {
			ExceptionLogger.logError(e);
			AppError appError = new AppError(AppErrorCodes.E400.toString(), Constants.DATA_NOT_FOUND_IN_CACHE);
			model.setErrors(appError);
			model.setStatus(Constants.STATUS_FAILED);
			model.setMessage(e.getMessage());
		}
		return model;
	}

}

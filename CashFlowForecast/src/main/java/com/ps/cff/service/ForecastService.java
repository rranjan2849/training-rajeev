package com.ps.cff.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ps.cff.entity.Forecast;
import com.ps.cff.entity.ForecastTransaction;
import com.ps.cff.entity.User;
import com.ps.cff.view.ForecastPayload;
import com.ps.cff.view.UpdatePayload;

/**
 * 
 * @author rranjan
 *
 */
public interface ForecastService {


	/**
	 * Get get all forecast by role
	 * @param user
	 * @return
	 */
	List<ForecastTransaction> getAllForecastByRole(Optional<User> user);


	/**
	 * Update forecast by id
	 * @param request
	 * @return
	 */
	String updateForcastById(UpdatePayload request);

	/**
	 * Delete forecast by id
	 * @param id
	 * @param forecastID 
	 * @return
	 */
	boolean deleteForcastById(long id, Long forecastID);


	/**
	 * Get forecast by horizon period
	 * @param requestPayload
	 * @return
	 */
	List<ForecastTransaction> getForcastByHorizonPeriods(@Valid ForecastPayload requestPayload);


	/**
	 * Create new forecast
	 * @param forecast
	 * @return
	 */
	Forecast createForecast(Forecast forecast);

	/**
	 * Get forecast by horizon period
	 * @param f
	 * @return
	 */
	List<ForecastTransaction> getForcastByHorizonPeriod(Forecast f);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<Forecast> findById(Long id);

	


}

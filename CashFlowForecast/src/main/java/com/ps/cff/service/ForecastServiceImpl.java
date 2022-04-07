package com.ps.cff.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ps.cff.controller.exception.MissingHeaderInfoException;
import com.ps.cff.controller.exception.RecordNotFoundException;
import com.ps.cff.controller.exception.RestServiceException;
import com.ps.cff.entity.Forecast;
import com.ps.cff.entity.ForecastTransaction;
import com.ps.cff.entity.Trasaction;
import com.ps.cff.entity.User;
import com.ps.cff.repositories.ForecastRepository;
import com.ps.cff.repositories.ForecastTransactionRepository;
import com.ps.cff.repositories.TransactionRepository;
import com.ps.cff.util.Constants;
import com.ps.cff.view.ForecastPayload;
import com.ps.cff.view.UpdatePayload;



/**
 * @author rranjan
 *
 */
@Service
public class ForecastServiceImpl implements ForecastService {
	private static final Logger logger = LogManager.getLogger(ForecastServiceImpl.class);

	

	@Autowired
	private ForecastTransactionRepository forecastTransactionRepository;


	@Autowired
	private ForecastRepository forecastRepository;


	@Autowired
	private TransactionRepository transactionRepository;



	/**
	 * Get forecast By Role
	 * Get get all forecast by role
	 * @param user
	 * @return
	 */
	@Override
	public List<ForecastTransaction> getAllForecastByRole(final Optional<User> user) {
		logger.info("getAllForcsatByRole service start");
		List<ForecastTransaction> responce=new ArrayList<ForecastTransaction>();
		if((user.get().getRoleId().getId()).equals(1L)) {

			return forecastTransactionRepository.findAll();

		}else if((user.get().getRoleId().getId()).equals(2L)) {

			List<Forecast> forcastList=forecastRepository.findByStatus(Constants.PUBLIC);

			for(Forecast forecast:forcastList) {
				List<ForecastTransaction> list1= forecastTransactionRepository.findByForecastId(forecast);
				list1.forEach(responce::add);
			}

		}
		if(responce.size()==0) {
			throw new RecordNotFoundException(Constants.NOTFOUNT);
		}
		/*
		 * for(ForecastTransaction forecast1:responce) { Integer id = (int) (long)
		 * forecast1.getId(); Map<Integer, ForecastTransaction> map=new HashMap<Integer,
		 * ForecastTransaction>(); map.put(id, forecast1);
		 * hashOperations.putAll(hashReference,map); }
		 */

		logger.info("getAllForcsatByRole service end");

		return responce;
	}

	/**
	 * Create forecast by horizon period
	 * @param requestPayload
	 * @return
	 */
	@Override
	@Transactional(rollbackOn = {RuntimeException.class})
	public List<ForecastTransaction> getForcastByHorizonPeriods(@Valid final ForecastPayload requestPayload) {
		logger.info("createForcastByHorizonPeriod "+Constants.START);
		List<ForecastTransaction> forecastTransactions=new ArrayList<>();
		
		try {
		
			Optional<Forecast> forecast=forecastRepository.getByHorizonPeriod(requestPayload.getHorizonPeriod());
			if(forecast.isPresent()) {
			
				List<Trasaction> transcation=transactionRepository.getHorizonPeriods(forecast.get().getStart_date(),forecast.get().getEnd_date());
				if(!transcation.isEmpty()) {
					//Create forecast transaction
					for(Trasaction transcations:transcation) {
						ForecastTransaction forecastTransaction=new ForecastTransaction();
						forecastTransaction.setForecastId(forecast.get());;
						forecastTransaction.setTransactionId(transcations);
						ForecastTransaction forecasts = forecastTransactionRepository.save(forecastTransaction);
						forecastTransactions.add(forecasts);
					}
				}else
					throw new RecordNotFoundException(Constants.NOTAVAILABLE +requestPayload.getHorizonPeriod());

			}else {
				throw new RecordNotFoundException(Constants.NOTFOUNDHORIZON + requestPayload.getHorizonPeriod());
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("createForcastByHorizonPeriod"+Constants.END);
		return forecastTransactions;
	}


	/**
	 * Update forecast by id
	 * @param request
	 * @return
	 */
	@Override
	@Transactional
	public String updateForcastById(UpdatePayload request) {
		logger.info("updateForcastById "+Constants.START);
		String response=Constants.NOTFOUNT;
		try {
			Optional<Forecast> forcasts = forecastRepository.findById(request.getId());
			if(forcasts.isPresent() && !(forcasts.get().getName()).equalsIgnoreCase(request.getName()) ) {
				forcasts.get().setId(request.getId());
				if(request.getName() !=null)
					forcasts.get().setName(request.getName());
				if(request.getDescription()!=null)
					forcasts.get().setDescription(request.getDescription());
				forecastRepository.save(forcasts.get());
				response=Constants.UPDATED;
			}else
				response=Constants.FOUND;

		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("updateForcastById "+Constants.END);
		return response;
	}

	/*
	 * public void deleteAllForecast() { forecastTransactionRepository.deleteAll();
	 * }
	 */
	/**
	 * Delete forecast by id
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteForcastById(final long id,Long forecastID) {
		logger.info("deleteForcastById "+Constants.START);
		boolean responce=false;
		try {
			//List<Forecast> forcastList=forecastRepository.findByStatus(Constants.PUBLIC);
			//if(!forcastList.isEmpty()) {
			//for(Forecast forecast:forcastList) 
			//	if(forecast.getStatus().equalsIgnoreCase(Constants.PUBLIC)) {
			int i=forecastTransactionRepository.deleteByForecastId(id,forecastID);
			if(i==1)
				responce=true;
			//}
			//}else
			//throw new RecordNotFoundException(Constants.NOTFOUNT);

			logger.info("deleteForcastById "+Constants.END);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new MissingHeaderInfoException("Exception: "+e.getMessage());
		}
		return responce;
	}

	/**
	 * Get forecast by horizon period
	 * @param horizonPeriod
	 * @return
	 */
	@Override
	public List<ForecastTransaction> getForcastByHorizonPeriod(final Forecast horizonPeriod) {
		logger.info("getForcastByHorizonPeriod "+Constants.START);
		List<ForecastTransaction> forecastTransactions=forecastTransactionRepository.findByForecastId(horizonPeriod);
		if(forecastTransactions.isEmpty())
			throw new RecordNotFoundException(Constants.NOTFOUNT);
		logger.info("getForcastByHorizonPeriod "+Constants.END);
		return forecastTransactions;
	}





	/**
	 * Create new forecast
	 * @param forecast
	 * @return
	 */
	@Override
	public Forecast createForecast(final Forecast forecast) {
		logger.info("createTransaction "+Constants.START);
		Forecast responce;
		Optional<Forecast> data=forecastRepository.findByName(forecast.getName());
		if(data.isEmpty()) 
			responce = forecastRepository.save(forecast);
		else 
			throw new RestServiceException(Constants.FOUND+forecast.getName());
		logger.info("createTransaction "+Constants.END);
		return responce;
	}



	/**
	 * @param id
	 * @return
	 */
	@Override
	public List<Forecast> findById(Long id) {
		return forecastRepository.findByStatus(Constants.PUBLIC);
	}

	public Forecast saveForecast(Forecast forecast) {
		return forecastRepository.save(forecast);
	}

}

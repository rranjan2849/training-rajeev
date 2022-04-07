package com.ps.cff.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ps.cff.controller.exception.RecordNotFoundException;
import com.ps.cff.controller.exception.RestServiceException;
import com.ps.cff.entity.Forecast;
import com.ps.cff.entity.ForecastTransaction;
import com.ps.cff.entity.User;
import com.ps.cff.service.ForecastService;
import com.ps.cff.service.KafKaProducerService;
import com.ps.cff.service.RabbitMqSender;
import com.ps.cff.service.UserService;
import com.ps.cff.util.Constants;
import com.ps.cff.view.ForecastPayload;
import com.ps.cff.view.UpdatePayload;
import com.ps.cff.view.UserPayload;



/**
 * @author rranjan
 *
 */
@RestController
@RequestMapping("/api")
public class ForecastController {
	private static final Logger logger = LogManager.getLogger(ForecastController.class);

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ForecastService forecastService;

	//@Autowired
	//private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private KafKaProducerService producerService;

	@Autowired 
	private UserService userService;

	@Resource(name = "redisTemplate")
	private HashOperations<String,String,Object> hashOperations;

	@Autowired
	private RabbitMqSender rabbitMqSender;


	/**
	 * fetch all forecast by role
	 * @param request 
	 * @return
	 */
	@GetMapping("/getAllForcastByRole")
	public ResponseEntity<List<ForecastTransaction>> getAllForcastByRole(@Valid @RequestBody final UserPayload request){

		logger.info("getAllForcastByRole endpoint");
		List<ForecastTransaction> list=null;
		Optional<User> user = userService.findUserByEmail(request.getEmail(),request.getPassword());
		Map<String, Object> redisValue=new HashMap<String, Object>();
		if(user.isPresent()) {
			list= forecastService.getAllForecastByRole(user);
			redisValue.put(Constants.SUCCESSFUL, list);
			rabbitMqSender.send(list.toString());
			redisTemplate.boundListOps("getAllForcastByRole").rightPush(redisValue);
			this.producerService.sendMessage(list.toString());
			return new ResponseEntity<>(list, HttpStatus.OK);
		}else {
			redisValue.put(Constants.UNSUCCESSFUL, list);
			redisTemplate.boundListOps("AllForcastByRole").rightPush(redisValue);
			throw new RestServiceException(Constants.UNAUTHORIZED + HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Update forecast by id 
	 * @param id
	 * @param request
	 * @return
	 */
	@PutMapping("/updateForcastById")
	public ResponseEntity<String> updateForcastById(@RequestBody final UpdatePayload request){
		logger.info("updateForcastById start");
		Optional<User> user=userService.findUserByEmail(request.getEmail(),request.getPassword());
		String responce=null;
		Map<String, Object> redisValue=new HashMap<String, Object>();
		if(user.isPresent() &&  request.getName() != null) {
			responce=forecastService.updateForcastById(request);
			rabbitMqSender.send(responce);

			redisValue.put(Constants.SUCCESSFUL, responce);
			redisTemplate.boundListOps("updateForcastById").rightPush(redisValue);
			this.producerService.sendMessage(responce.toString());
			return new ResponseEntity<>(responce,HttpStatus.OK);
		}else {
			redisValue.put(Constants.UNSUCCESSFUL, responce);
			redisTemplate.boundListOps("updateForcastById").rightPush(redisValue);

			throw new RestServiceException(Constants.UNAUTHORIZED );
		}
	}


	/**
	 * Delete Forecast by id and status is public 
	 * @param request
	 * @return
	 * @throws RestServiceException
	 */
	@DeleteMapping("/deleteForcastById")
	public ResponseEntity<String> deleteForcastById(@RequestBody final UserPayload request)throws RestServiceException {
		logger.info("deleteForcastById endpoint");
		Optional<User> user=userService.findUserByEmail(request.getEmail(),request.getPassword());
		boolean responce=false;
		Map<String, Object> redisValue=new HashMap<String, Object>();
		if(user.isPresent()) {

			List<Forecast> forcast=forecastService.findById(request.getId());

			for(Forecast forcasts:forcast)
				responce=forecastService.deleteForcastById(request.getId(),forcasts.getId());

			if(responce) {
				rabbitMqSender.send(Constants.DELETED);
				redisValue.put(Constants.SUCCESSFUL, responce);
				redisTemplate.boundListOps("deleteForcastById").rightPush(redisValue);
				this.producerService.sendMessage(Constants.DELETED);
				return new ResponseEntity<>(Constants.DELETED,HttpStatus.OK);
			}else {
				redisValue.put(Constants.UNSUCCESSFUL, responce);
				redisTemplate.boundListOps("deleteForcastById").rightPush(redisValue);
				return new ResponseEntity<>(Constants.NOTDELETED,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else
			throw new RestServiceException(Constants.UNAUTHORIZED );
	}

	/**
	 * 
	 * @param horizonPeriod
	 * @return
	 */
	@GetMapping("/getForcastByHorizonPeriod/{horizonPeriod}")
	public ResponseEntity<List<ForecastTransaction>> getForcastByHorizonPeriod(@PathVariable final String horizonPeriod){
		Forecast forecast=getForcastId(horizonPeriod);
		Map<String, Object> redisValue=new HashMap<String, Object>();
		List<ForecastTransaction> responce=forecastService.getForcastByHorizonPeriod(forecast);
		if(responce.size()>0) {
			redisValue.put(Constants.SUCCESSFUL, responce);
			redisTemplate.boundListOps("getForcastByHorizonPeriod").rightPush(redisValue);
		}else {
			redisValue.put(Constants.UNSUCCESSFUL, responce);
			redisTemplate.boundListOps("getForcastByHorizonPeriod").rightPush(redisValue);
		}
		//kafkaTemplate.send("test-topic", responce.toString());
		this.producerService.sendMessage(responce.toString());

		return  ResponseEntity.ok(responce);
	}


	/**
	 * Create Forecast By horizon period 3m 1w 3m
	 * @param requestPayload
	 * @return
	 */
	@PostMapping("/createForcastByHorizonPeriod")
	public ResponseEntity<List<ForecastTransaction>> createForcastByHorizonPeriod(@Valid @RequestBody final ForecastPayload requestPayload){


		Optional<User> user=userService.findUserByEmail(requestPayload.getEmail(),requestPayload.getPassword());
		List<ForecastTransaction> responce=null;
		Map<String, Object> redisValue=new HashMap<String, Object>();
		if(user.isPresent()) {
			responce=forecastService.getForcastByHorizonPeriods(requestPayload);
			redisValue.put(Constants.SUCCESSFUL, responce);
			redisTemplate.boundListOps("createForcastByHorizonPeriod").rightPush(redisValue);


		}else {
			redisValue.put(Constants.UNSUCCESSFUL, responce);
			redisTemplate.boundListOps("createForcastByHorizonPeriod").rightPush(redisValue);
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}

		logger.info("getForcastByHorizonPeriod end");

		rabbitMqSender.send(responce.toString());
		this.producerService.sendMessage(responce.toString());
		return new ResponseEntity<>(responce, HttpStatus.OK);
	}


	/**
	 * Create forecast data in forecast Table
	 * @param forecast
	 * @return
	 */
	@PostMapping("/createForecast")
	public ResponseEntity<Forecast> createForecast(@RequestBody final Forecast forecast){
		logger.info("postAccountData start");
		Forecast responce=forecastService.createForecast(forecast);
		Map<String, Object> redisValue=new HashMap<String, Object>();
		try {
			if(responce !=null) {
				logger.info("postAccountData status "+HttpStatus.OK);
				rabbitMqSender.send(responce.toString());
				redisValue.put(Constants.SUCCESSFUL, responce);
				redisTemplate.boundListOps("createForecast").rightPush(redisValue);
				this.producerService.sendMessage(responce.toString());
				return new ResponseEntity<>(responce,HttpStatus.OK);
			}else {
				redisValue.put(Constants.UNSUCCESSFUL, responce);
				redisTemplate.boundListOps("createForecast").rightPush(redisValue);
				throw new RestServiceException(Constants.CREATED);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestServiceException("Exception"+e.getMessage());
		}
	}

	/**
	 * 
	 * @param horizonPeriod
	 * @return
	 */
	private Forecast getForcastId(String horizonPeriod) {
		Forecast forecast=new Forecast();
		switch (horizonPeriod) {
		case "1W":{forecast.setId(1L); return forecast;}
		case "2W":{forecast.setId(2L); return forecast;}
		case "1M":{forecast.setId(3L); return forecast;}
		case "3M":{forecast.setId(4L); return forecast;}
		default:
			throw new RecordNotFoundException(Constants.HORIZONPERIOD);
		}

	}
}

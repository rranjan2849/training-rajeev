package com.ps.cff;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author rranjan
 *
 */
@SpringBootApplication
public class CashFlowForcastApplication{
	private static final Logger logger = LogManager.getLogger(CashFlowForcastApplication.class);
	public static void main(String[] args) {
		logger.info("Main method start");
		SpringApplication.run(CashFlowForcastApplication.class, args);
		logger.info("Main method end");
		
	}
	

}

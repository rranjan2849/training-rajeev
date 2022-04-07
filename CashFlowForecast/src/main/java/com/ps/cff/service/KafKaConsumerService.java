package com.ps.cff.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ps.cff.util.Constants;

@Service
public class KafKaConsumerService 
{
	private static final Logger logger = LogManager.getLogger(KafKaConsumerService.class);
	
	
    @KafkaListener(topics = Constants.TOPIC_NAME, groupId = Constants.GROUP_ID)
    public void consume(String message) 
    {
        logger.info(String.format("Message recieved -> %s", message));
    }
}

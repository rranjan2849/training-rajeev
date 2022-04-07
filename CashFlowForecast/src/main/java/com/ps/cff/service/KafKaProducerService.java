package com.ps.cff.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ps.cff.util.Constants;

@Service
public class KafKaProducerService 
{
	private static final Logger logger = LogManager.getLogger(KafKaProducerService.class);
	
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
 
    public void sendMessage(String message) 
    {
    	logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(Constants.TOPIC_NAME, message);
    }
}

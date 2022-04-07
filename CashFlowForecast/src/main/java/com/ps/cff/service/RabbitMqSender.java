package com.ps.cff.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqSender{

	private RabbitTemplate rabbitTemplate;

	@Value("${ps.rabbitmq.exchange}")
	private String exchange;

	@Value("${ps.rabbitmq.routingkey}")
	private String routingkey;	

	@Autowired
	public RabbitMqSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void send(String username){
		rabbitTemplate.convertAndSend(exchange,routingkey, username);
	}
}
package com.ps.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqService implements RabbitListenerConfigurer {


	@RabbitListener(queues = "${ps.rabbitmq.queue}")
	public void receivedMessage(String str) {
		System.out.println(str);
	}

	@RabbitListener(queues = "${ps.rabbitmq.queue1}")
	public void receivedMessage1(String str) {
		System.out.println(str);
	}
	
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

	}

}

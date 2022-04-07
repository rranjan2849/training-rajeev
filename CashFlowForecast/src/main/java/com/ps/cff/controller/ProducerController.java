package com.ps.cff.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ps.cff.service.RabbitMqSender;



@RestController
@RequestMapping(value = "/api")
public class ProducerController {
   
	private RabbitMqSender rabbitMqSender;
	

	@Value("${ps.message}")
    private String message;
	
    @Autowired
    public ProducerController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }
    
    @PostMapping(value = "/username")
    public String publishUserDetails(@PathVariable String username ) {
        rabbitMqSender.send(username);
        return message;
    }
}
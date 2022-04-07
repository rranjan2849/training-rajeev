package com.ps.cff.controller;


import java.util.List;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.cff.entity.User;
import com.ps.cff.service.UserService;

/**
 * @author rranjan
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	

	/**
	 * Create new user details in user tables
	 * @param user
	 * @return
	 */
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		logger.info("createUser start");
		User responce=null;
		Boolean users=userService.findUserByEmail(user.getEmail());
		if(users) 
			responce=userService.createUser(user);
		
		logger.info("createUser end");
		return new ResponseEntity<>(responce,HttpStatus.OK);
	}
	
	/**
	 * Get all user List
	 * @return
	 */
	@GetMapping("/getAll")
	public List<User> getAll(){
		return userService.getAllUsers();
	}
	
}

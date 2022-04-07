package com.ps.cff.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ps.cff.entity.User;

/**
 * @author rranjan
 *
 */
public interface UserService {
	
	
	/**
	 * Get user details By email and password
	 * @param email
	 * @param password
	 * @return
	 */
	Optional<User> findUserByEmail(String email, String password);
	
	
	/**
	 * Find by Email
	 * @param email
	 * @return
	 */
	boolean findUserByEmail(String email);

	/**
	 * Create new record in User table
	 * @param user
	 * @return
	 */
	User createUser(@Valid User user);
	
	/**
	 * Get All user details
	 * @return
	 */
	List<User> getAllUsers();
	
	/**
	 *Delete User By ID 
	 * @param email
	 * @return
	 */
	User deleteUser(String email);
}

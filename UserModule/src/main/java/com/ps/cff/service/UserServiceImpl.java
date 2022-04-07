package com.ps.cff.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.cff.controller.exception.RecordNotFoundException;
import com.ps.cff.controller.exception.RestServiceException;
import com.ps.cff.entity.Constants;
import com.ps.cff.entity.User;
import com.ps.cff.repositories.UserRepository;


/**
 * @author rranjan
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;



	/**
	 * Get user details By email and password
	 * @param email
	 * @param password
	 * @return
	 */
	@Override
	public Optional<User> findUserByEmail(String email, String password) {
		logger.info("findUserByEmail service start");
		//System.out.println("Hits is DB");
		Optional<User> user=userRepository.findByEmail(email);
		if(user.isPresent() && user.get().getEncryptedPassword().equals(encryptedPassword(password)))
			return user;
		else 
			throw new RecordNotFoundException(Constants.UNAUTHORIZED);
	}

	/**
	 * Find by Email
	 * @param email
	 * @return
	 */
	@Override
	public boolean findUserByEmail(String email) {
		Optional<User> user=userRepository.findByEmail(email);
		if(user.isPresent())
			throw new RestServiceException("User already registerd -"+email);
		else
			return true;
	}

	
	/**
	 * Password Encrypted method
	 * @param password
	 * @return
	 */
	private String encryptedPassword(String password) {
		logger.info("create encryptedPassword service start");
		String encryptedPassword=null;
		try {
			MessageDigest m=MessageDigest.getInstance("MD5");
			m.update(password.getBytes());
			byte[] bytes = m.digest();
			StringBuilder s=new StringBuilder();
			for(int i=0;i<bytes.length;i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			encryptedPassword=s.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptedPassword;
	}

	/**
	 * Create new record in User table
	 * @param user
	 * @return
	 */
	@Override
	public User createUser(@Valid User user) {
		user.setEncryptedPassword(encryptedPassword(user.getEncryptedPassword()));
		User users=userRepository.save(user);
		return users;
	}

	/**
	 * Get All user details
	 * @return
	 */
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 *Delete User By ID 
	 * @param id
	 * @return
	 */
	@Override
	public User deleteUser(String email) {
		return userRepository.deleteByEmail(email);
	}
	

     

}

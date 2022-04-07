package com.ps.cff.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ps.cff.entity.User;

/**
 * @author rranjan
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	/**
	 * 
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);

	/**
	 * 
	 * @param email
	 * @return
	 */
	User deleteByEmail(String email);

}

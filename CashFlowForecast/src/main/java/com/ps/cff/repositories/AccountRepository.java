package com.ps.cff.repositories;

import org.springframework.stereotype.Repository;

import com.ps.cff.entity.Account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @author rranjan
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	/**
	 * @param accountNumber
	 * @return
	 */
	Optional<Account> getByAccountNumber(long accountNumber);

}

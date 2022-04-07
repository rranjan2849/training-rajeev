package com.ps.cff.repositories;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ps.cff.entity.Trasaction;

/**
 * @author rranjan
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Trasaction, Long>{
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	@Query(value = "SELECT * FROM trasaction WHERE  `transaction_date` BETWEEN :start AND :end",nativeQuery = true)
	List<Trasaction> getHorizonPeriods(Timestamp start, Timestamp end);

}

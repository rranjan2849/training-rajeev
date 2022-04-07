package com.ps.cff.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ps.cff.entity.Forecast;
import com.ps.cff.entity.ForecastTransaction;

/**
 * @author rranjan
 *
 */
@Repository
public interface ForecastTransactionRepository extends JpaRepository<ForecastTransaction, Long>{
	/**
	 * 
	 * @param f
	 * @return
	 */
	List<ForecastTransaction> findByForecastId(Forecast forecast);


	/**
	 * @param id
	 * @param forcastId
	 * @return
	 */
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM forecast_transaction WHERE id=:id and forecast_id=:forcast",nativeQuery = true)
	int deleteByForecastId(Long id, Long forcast);

}

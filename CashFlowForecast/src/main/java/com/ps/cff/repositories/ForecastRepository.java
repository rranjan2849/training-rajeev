package com.ps.cff.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ps.cff.entity.Forecast;

/**
 * @author rranjan
 *
 */
@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long>{
	

	/**
	 * Get forecast by status
	 * @param string
	 * @param id 
	 * @return
	 */
	List<Forecast> findByStatus(String string);
	
	
	/**
	 * Get forecast by horizon period
	 * @param horizonPeriod
	 * @return
	 */
	Optional<Forecast> getByHorizonPeriod(String horizonPeriod);

	/**
	 * Create new record in database
	 * @param name
	 * @return
	 */
	Optional<Forecast> findByName(String name);

}

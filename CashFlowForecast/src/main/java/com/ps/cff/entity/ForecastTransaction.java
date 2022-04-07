package com.ps.cff.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author rranjan
 *
 */
@Entity
public class ForecastTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	

	@ManyToOne
	@JoinColumn(name = "forecastId", referencedColumnName = "id")
	private Forecast forecastId;


	@ManyToOne
	@JoinColumn(name = "transactionId", referencedColumnName = "id")
	private Trasaction transactionId;

	
	
	public ForecastTransaction() {
		super();
	}
	
	

	public ForecastTransaction(Long id, Forecast forecastId, Trasaction transactionId) {
		super();
		this.id = id;
		this.forecastId = forecastId;
		this.transactionId = transactionId;
	}



	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	


	public Forecast getForecastId() {
		return forecastId;
	}

	public void setForecastId(Forecast forecastId) {
		this.forecastId=forecastId;
	}

	public Trasaction getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Trasaction transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "ForecastTransaction [id=" + id + ", forecastId=" + forecastId + ", transactionId=" + transactionId
				+ "]";
	}

	
}

package com.ps.cff.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
/**
 * 
 * @author rranjan
 *
 */
@Entity
@DynamicUpdate
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "name", name="name"),
})
public class Forecast implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotNull
	private String name;

	private String description;

	@NotBlank
	@NotNull
	private String horizonPeriod;	//1W/2M/3M


	@NotNull
	private Timestamp start_date;	


	@NotNull
	private Timestamp end_date;	

	@NotBlank
	@NotNull
	private String status;	//private/public



	public Forecast() {
		super();
	}


	public Forecast(Long id, @NotBlank @NotNull String name, String description,
			@NotBlank @NotNull String horizonPeriod, @NotNull Timestamp start_date, @NotNull Timestamp end_date,
			@NotBlank @NotNull String status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.horizonPeriod = horizonPeriod;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
	}


	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}


	/*
	 * public ForecastTransaction getForcastId() { return forcastId; }
	 * 
	 * 
	 * public void setForcastId(ForecastTransaction forcastId) { this.forcastId =
	 * forcastId; }
	 */


	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 */
	public String getHorizonPeriod() {
		return horizonPeriod;
	}
	/**
	 * @param horizonPeriod
	 */
	public void setHorizonPeriod(String horizonPeriod) {
		this.horizonPeriod = horizonPeriod;
	}
	/**
	 * @return
	 */
	public Timestamp getStart_date() {
		return start_date;
	}
	/**
	 * @param start_date
	 */
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	/**
	 * @param end_date
	 */
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Forecast [id=" + id + ", name=" + name + ", description=" + description + ", horizonPeriod="
				+ horizonPeriod + ", start_date=" + start_date + ", end_date=" + end_date + ", status=" + status + "]";
	}



}

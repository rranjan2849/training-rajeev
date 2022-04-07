package com.ps.cff.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ForecastPayload {
	
	/*
	 *Json Attribute Email 
	 */
	//@NotNull
	//@NotEmpty
	private String email;
	
	/*
	 *Json Attribute Password
	 */
	//@NotNull
	//@NotEmpty
	private String password;

	/*
	 *Json Attribute horizonPeriod
	 */
	@NotNull
	@NotEmpty
	private String horizonPeriod;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHorizonPeriod() {
		return horizonPeriod;
	}

	public void setHorizonPeriod(String horizonPeriod) {
		this.horizonPeriod = horizonPeriod;
	}
	
	
}

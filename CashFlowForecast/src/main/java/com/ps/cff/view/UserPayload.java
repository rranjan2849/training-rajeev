package com.ps.cff.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @author rranjan
 *
 */
public class UserPayload {

	/*
	 * Attribute id 
	 */
	private Long id;
	/*
	 * Attribute Username 
	 */
	@NotNull
	@NotEmpty
	private String email;

	/*
	 * Attribute Password
	 */
	@NotNull
	@NotEmpty
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	
}

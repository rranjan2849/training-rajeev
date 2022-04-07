package com.ps.cff.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UpdatePayload {
	

	/*
	 *Json Attribute id 
	 */
	
	@NotNull
	@NotEmpty
	private long id;

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
	 *Json Attribute description
	 */

	private String description;

	/*
	 *Json Attribute name
	 */

	private String name;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}

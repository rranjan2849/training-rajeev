package com.ps.cff.view;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = UserTest.class)
public class UserTest {
	private long id;
	private String name;
	
	public UserTest() {
		super();
	}

	public UserTest(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}

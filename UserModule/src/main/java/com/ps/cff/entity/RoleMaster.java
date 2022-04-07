package com.ps.cff.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/*
 * Role Master table Contain Unique Role
 */
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "name",name="name")
})
public class RoleMaster implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * Contain Unique Name
	 */
	
	
	@Column(columnDefinition = "VARCHAR(100)")
	@NotNull
	@NotEmpty
	private String name;

	
	
	public RoleMaster() {
		super();
	}

	public RoleMaster(Long id, @NotNull @NotEmpty String name) {
		super();
		this.id = id;
		this.name = name;
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
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RoleMaster [id=" + id + ", name=" + name + "]";
	}
	
	
}

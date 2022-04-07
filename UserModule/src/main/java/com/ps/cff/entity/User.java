package com.ps.cff.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
 * User class containing user data
 */
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "email", name="email"),
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = User.class)
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	 * The User parameter  name
	 */
	@NotNull
	private String name;
	/*
	 * The User parameter  email
	 */
	@NotNull
	@Email
	@Column(columnDefinition = "VARCHAR(200)")
	private String email;
	/*
	 * The User parameter  encryptedPassword
	 */
	@NotNull
	private String encryptedPassword;
	/*
	 * The User parameter  role
	 */
	
	@NotNull
	@OneToOne
	@JoinColumn(name="id" ,referencedColumnName = "roleId")
	private RoleMaster roleId;
	
	
	/*
	 * The User parameter  signUpDate
	 */
	@CreationTimestamp
	private LocalDateTime signUpDate;


	
	
	public User() {
		super();
	}

	public User(Long id, @NotNull String name, @NotNull @Email String email, @NotNull String encryptedPassword,
			@NotNull RoleMaster roleId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
		this.roleId = roleId;
	
	}

	public RoleMaster getRoleId() {
		return roleId;
	}

	public void setRoleId(RoleMaster roleId) {
		this.roleId=roleId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	

	public LocalDateTime getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(LocalDateTime signUpDate) {
		this.signUpDate = signUpDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", encryptedPassword=" + encryptedPassword
				+ ", roleId=" + roleId + ", signUpDate=" + signUpDate + "]";
	}

	
	
}

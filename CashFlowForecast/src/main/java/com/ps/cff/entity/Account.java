package com.ps.cff.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
/**
 * 
 * @author rranjan
 *
 */
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "accountNumber", name="accountNumber"),
})
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	private String accountHolderName;
	
	
	@NotNull
	@Column(length = 16)
	private long accountNumber;
	
	@NotBlank
	@NotNull
	private String typeOfAccount;
	
	@CreationTimestamp
	private Timestamp dateOfCreation;
	
	
	private String statusOfAccount;
	
	
	public Account() {
		super();
	}
	
	
	public Account(Long id, @NotBlank @NotNull String accountHolderName, @NotNull long accountNumber,
			@NotBlank @NotNull String typeOfAccount) {
		super();
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.typeOfAccount = typeOfAccount;
	}


	/**
	 * @return
	 */
	public long getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * @param accountNumber
	 */
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
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
	
	/**
	 * @return
	 */
	public String getAccountHolderName() {
		return accountHolderName;
	}
	/**
	 * @param accountHolderName
	 */
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	/**
	 * @return
	 */
	public String getTypeOfAccount() {
		return typeOfAccount;
	}
	/**
	 * @param typeOfAccount
	 */
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}
	/**
	 * @return
	 */
	public Timestamp getDateOfCreation() {
		return dateOfCreation;
	}
	/**
	 * @param dateOfCreation
	 */
	public void setDateOfCreation(Timestamp dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	/**
	 * @return
	 */
	public String getStatusOfAccount() {
		
		return statusOfAccount;
	}
	/**
	 * @param statusOfAccount
	 */
	public void setStatusOfAccount(String statusOfAccount) {
		this.statusOfAccount = statusOfAccount;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountHolderName=" + accountHolderName + ", accountNumber=" + accountNumber
				+ ", typeOfAccount=" + typeOfAccount + ", dateOfCreation=" + dateOfCreation + ", statusOfAccount="
				+ statusOfAccount + "]";
	}
	
	

}

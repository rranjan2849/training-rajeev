package com.ps.cff.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ps.cff.util.CFFEntryTypeConveter;

/**
 * @author rranjan
 *
 */
@Entity
public class Trasaction implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "accountId",referencedColumnName = "id")
	private Account accountId;
	
	private Timestamp transactionDate;
	
	@Convert(converter = CFFEntryTypeConveter.class)
	private CFFEntryType transactionType; //Payable and RECEIVABLE
	
	
	private String description;

	@NotNull
	private Long amount;

	
	public Trasaction() {
		super();
	}
	
	
	public Trasaction(Long id, Account accountId, CFFEntryType transactionType, String description,
			@NotNull Long amount,Timestamp transactionDate) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.description = description;
		this.amount = amount;
		this.transactionDate = transactionDate;
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

	/*
	 * public ForecastTransaction getTransactionId() { return transactionId; }
	 * 
	 * public void setTransactionId(ForecastTransaction transactionId) {
	 * this.transactionId = transactionId; }
	 */

	/**
	 * @return
	 */
	public Account getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 */
	public void setAccountId(Account accountId) {
		this.accountId=accountId;
	}

	/**
	 * @return
	 */
	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate
	 */
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return
	 */
	public CFFEntryType getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 */
	public void setTransactionType(CFFEntryType transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Trasaction [id=" + id + ", accountId=" + accountId + ", transactionDate=" + transactionDate
				+ ", transactionType=" + transactionType + ", description=" + description + ", amount=" + amount + "]";
	}

	
}

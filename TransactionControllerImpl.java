package com.ii.app.controllers;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ii.app.dto.in.PaymentIn;
import com.ii.app.dto.in.TransactionIn;
import com.ii.app.dto.out.BankAccountOut;
import com.ii.app.dto.out.PaymentOut;
import com.ii.app.services.interfaces.TransactionService;

@Scope(value = "session")
@Controller(value = "TransactionController")
@ELBeanName(value = "TransactionController")
@Join(path = "/transa", to = "/transaction.jsf")
public class TransactionControllerImpl {
	@Autowired
	TransactionService transactionService;
	
	private static final Logger L=LogManager.getLogger(TransactionControllerImpl.class);

    private String sourceAccountNumber;

    
    private String sourceCurrency;

    private String destinedAccountNumber;

    
    private String destinedCurrency;

    private float balance;

    private String title;
    private String email;

 
    public void transact() {
    	TransactionIn emp=new TransactionIn(sourceAccountNumber, sourceCurrency, destinedAccountNumber,destinedCurrency, balance, title);
		L.info("tester"+emp);
		transactionService.create(emp, email);
    }
 
  
 
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public static Logger getL() {
		return L;
	}


	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getDestinedAccountNumber() {
		return destinedAccountNumber;
	}

	public void setDestinedAccountNumber(String destinedAccountNumber) {
		this.destinedAccountNumber = destinedAccountNumber;
	}

	public String getDestinedCurrency() {
		return destinedCurrency;
	}

	public void setDestinedCurrency(String destinedCurrency) {
		this.destinedCurrency = destinedCurrency;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public TransactionService getTransactionService() {
		return transactionService;
	}




	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
    
	
    
}

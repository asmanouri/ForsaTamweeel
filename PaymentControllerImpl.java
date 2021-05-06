package com.ii.app.controllers;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ii.app.dto.in.PaymentIn;
import com.ii.app.dto.out.BankAccountOut;
import com.ii.app.dto.out.PaymentOut;
import com.ii.app.models.CurrencyType;
import com.ii.app.models.enums.BankAccountType;
import com.ii.app.models.enums.Currency;
import com.ii.app.services.interfaces.BankAccountService;
import com.ii.app.services.interfaces.PaymentService;



@Scope(value = "session")
@Controller(value = "PaymentController")
@ELBeanName(value = "PaymentController")
@Join(path = "/pay", to = "/payment.jsf")
public class PaymentControllerImpl {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	BankAccountService bankAccountService;
	private static final Logger L=LogManager.getLogger(PaymentControllerImpl.class);

	  private String destinedBankAccountNumber;

	    
	    private String sourceCurrencyType;

	    
	    private BigDecimal balance;

	    private Instant date;
private Long id;

private List<PaymentOut> payments;
private Long bankAccountId;
private List<PaymentOut> paymentsid;

@Enumerated(EnumType.STRING) 
private Currency currency;





public void removePayment(Long id)
{
	bankAccountService.deleteById(id);;
}

public List<PaymentOut> findbybank(Long bankAccountId ){
	payments = paymentService.findAllByBankAccountId(bankAccountId);
	return payments;
}


public List<PaymentOut> getPaymentsid() {
	return paymentsid;
}











public void setPaymentsid(List<PaymentOut> paymentsid) {
	this.paymentsid = paymentsid;
}











public Long getBankAccountId() {
	return bankAccountId;
}











public void setBankAccountId(Long bankAccountId) {
	this.bankAccountId = bankAccountId;
}











public Currency[] getCurrencys() { return currency.values(); }

		public Currency getCurrency() {
	return currency;
}

		public void setCurrency(Currency currency) {
	this.currency = currency;
}

		public PaymentService getPaymentService() {
	return paymentService;
}

public void setPaymentService(PaymentService paymentService) {
	this.paymentService = paymentService;
}

public BankAccountService getBankAccountService() {
	return bankAccountService;
}


public void setBankAccountService(BankAccountService bankAccountService) {
	this.bankAccountService = bankAccountService;
}

public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}

		public void verser() {
	    	PaymentIn emp=new PaymentIn(destinedBankAccountNumber, sourceCurrencyType, balance, date.now());
			L.info("zzazaezaeazzaa"+emp);
			L.info("zzazaezaeazzaa"+paymentService.create(emp));
			}
	    
	    
		
		

		public void retrait() {
	    	PaymentIn emp=new PaymentIn(destinedBankAccountNumber, sourceCurrencyType, balance, date.now());
			L.info("zzazaezaeazzaa"+emp);
			L.info("zzazaezaeazzaa"+paymentService.withdraw(emp));
			}
	    
		
		  public List<PaymentOut> getPayments() {
				payments = paymentService.findAll();
				return payments;
				}
		    
		
		  
		  
		  
		  
	    
		public void setPayments(List<PaymentOut> payments) {
			this.payments = payments;
		}

		public String getDestinedBankAccountNumber() {
			return destinedBankAccountNumber;
		}

		public void setDestinedBankAccountNumber(String destinedBankAccountNumber) {
			this.destinedBankAccountNumber = destinedBankAccountNumber;
		}

		public String getSourceCurrencyType() {
			return sourceCurrencyType;
		}

		public void setSourceCurrencyType(String sourceCurrencyType) {
			this.sourceCurrencyType = sourceCurrencyType;
		}

		public BigDecimal getBalance() {
			return balance;
		}

		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}

		public Instant getDate() {
			return date;
		}

		public void setDate(Instant date) {
			this.date = date;
		}

		public static Logger getL() {
			return L;
		}
	    
	    
	    
}

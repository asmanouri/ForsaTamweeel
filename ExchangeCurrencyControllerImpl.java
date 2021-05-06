package com.ii.app.controllers;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ii.app.dto.in.ExchangeCurrencyIn;
import com.ii.app.services.interfaces.ExchangeCurrencyService;



@Scope(value = "session")
@Controller(value = "ExchangeController")
@ELBeanName(value = "ExchangeController")
@Join(path = "/exch", to = "/exchange.jsf")
public class ExchangeCurrencyControllerImpl {
	
	private static final Logger L=LogManager.getLogger(ExchangeCurrencyControllerImpl.class);

	@Autowired
	ExchangeCurrencyService exchangeCurrencyService;
    private float balance;

    
    private String sourceBankAccNumber;

    
    private String sourceCurrency;

    
    private String destCurrency;

    
    private BigDecimal msg;
    
    
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Here is The result" + calculate() );

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
    
    
    public ExchangeCurrencyService getExchangeCurrencyService() {
		return exchangeCurrencyService;
	}




	public void setExchangeCurrencyService(ExchangeCurrencyService exchangeCurrencyService) {
		this.exchangeCurrencyService = exchangeCurrencyService;
	}




	public BigDecimal getMsg() {
		return msg;
	}




	public void setMsg(BigDecimal msg) {
		this.msg = msg;
	}




	public BigDecimal calculate() {
    	ExchangeCurrencyIn emp=new ExchangeCurrencyIn(balance, sourceBankAccNumber, sourceCurrency, destCurrency);
		L.info("zzazaezaeazzaa"+emp);
	//	L.info("zzazaezaeazzaa"+exchangeCurrencyService.calculate(emp));
		msg=  exchangeCurrencyService.calculate(emp);
		  return msg;
		}
    
    
    

	public float getBalance() {
		return balance;
	}


	public void setBalance(float balance) {
		this.balance = balance;
	}


	public String getSourceBankAccNumber() {
		return sourceBankAccNumber;
	}


	public void setSourceBankAccNumber(String sourceBankAccNumber) {
		this.sourceBankAccNumber = sourceBankAccNumber;
	}


	public String getSourceCurrency() {
		return sourceCurrency;
	}


	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}


	public String getDestCurrency() {
		return destCurrency;
	}


	public void setDestCurrency(String destCurrency) {
		this.destCurrency = destCurrency;
	}


	public static Logger getL() {
		return L;
	}

    
    
    
}

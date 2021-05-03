package com.ii.app.controllers;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ii.app.dto.edit.SaldoEdit;
import com.ii.app.dto.in.BankAccountIn;
import com.ii.app.dto.out.*;
import com.ii.app.models.enums.BankAccountType;
import com.ii.app.models.user.User;
import com.ii.app.repositories.UserRepository;
import com.ii.app.services.interfaces.BankAccountService;
import com.ii.app.services.interfaces.UserService;

@Scope(value = "session")
@Controller(value = "BankController")
@ELBeanName(value = "BankController")
@Join(path = "/add", to = "/admin/addAccount.jsf")
public class BankAccountControllerImpl {
	private static final Logger L=LogManager.getLogger(BankAccountControllerImpl.class);
	
	@Autowired
    BankAccountService bankAccountService;
	
	@Autowired
	UserService userservice;
	private Optional<User> authenticatedUser;
	private UserOut authenticatedUserr;

	
	@Enumerated(EnumType.STRING) 
	private BankAccountType BankAccountType;
	private BankAccTypeOut BankAccTypeOut;
	private String userIdentifier;
	private List<BankAccountOut> accounts;
    private String number;
    @Autowired
	UserRepository userRepository;
    private String login;
	private String password; 
	private BankAccountOut ankAccountOut = new BankAccountOut();
	private Long idcomm;

	private Long id;
	private	BigDecimal balance;
    private Set<SaldoOut> saldos =  new HashSet<SaldoOut>();

	
	public BankAccountType[] getBankAccountTypes() { return BankAccountType.values(); }

	public String addBankAccount() {
	//	authenticatedUserr=userservice.findCurrentUser();

		BankAccountIn emp=new BankAccountIn(BankAccountType);
		
		
		L.info("Asma"+emp);
		L.info("Asmaa"+bankAccountService.create(emp, userIdentifier));
		return "addAccount.xhtml?faces-redirect=true";
		
		}


	
     public String updateAgency(long id)
	
	{ 
		BankAccountOut agency = bankAccountService.findById(id);
		display(agency);
		
		return "/admin/update_agency.xhtml?faces-redirect=true";
	}	
	

	
	  public void display(BankAccountOut empl) {
	  
	  //this.setId(empl.getId()); this.setSaldos(empl.getSaldos());
	  this.setId(empl.getId()); this.setNumber(empl.getNumber());
	//  this.setBalance(empl.getSaldos());
	  
	  }
	 
	
	public List<BankAccountOut> getAccounts() {
		accounts = bankAccountService.findAll();
		return accounts;
		
		
		}
	
	public void removeAccount(Long id)
	{
		bankAccountService.deleteById(id);;
	}
	
	public void updateSolde() {
		SaldoEdit se = new SaldoEdit(balance);
		L.info("update"+bankAccountService.updateSaldo(id, se));

		
	}

	
	/*
	 * public String update(long id)
	 * 
	 * { BankAccountOut bank = bankAccountService.findById(id);
	 * displayAccount(bank);
	 * 
	 * return "/admin/updateAccount.xhtml?faces-redirect=true"; }
	 */	
	
	
	
	/*
	 * public void displayAccount(BankAccountOut empl) {
	 * 
	 * //this.setId(empl.getId()); this.setSaldos(empl.getSaldos());
	 * this.setIdcomm(empl.getBankAccType().getId());
	 * this.setNumber(empl.getNumber());
	 * 
	 * }
	 */
	
	

	public UserService getUserservice() {
		return userservice;
	}


	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}





	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserOut getAuthenticatedUserr() {
		return authenticatedUserr;
	}


	public void setAuthenticatedUserr(UserOut authenticatedUserr) {
		this.authenticatedUserr = authenticatedUserr;
	}

	public void setAccounts(List<BankAccountOut> accounts) {
		this.accounts = accounts;
	}


	public BankAccountService getBankAccountService() {
		return bankAccountService;
	}


	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}


	public String getUserIdentifier() {
		return userIdentifier;
	}


	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}


	public static Logger getL() {
		return L;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BankAccountType getBankAccountType() {
		return BankAccountType;
	}


	public void setBankAccountType(BankAccountType bankAccountType) {
		BankAccountType = bankAccountType;
	}







	public BankAccTypeOut getBankAccTypeOut() {
		return BankAccTypeOut;
	}

	public void setBankAccTypeOut(BankAccTypeOut bankAccTypeOut) {
		BankAccTypeOut = bankAccTypeOut;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Set<SaldoOut> getSaldos() {
		return saldos;
	}

	public void setSaldos(Set<SaldoOut> saldos) {
		this.saldos = saldos;
	}










	public Long getIdcomm() {
		return idcomm;
	}










	public void setIdcomm(Long idcomm) {
		this.idcomm = idcomm;
	}

	public BankAccountOut getAnkAccountOut() {
		return ankAccountOut;
	}

	public void setAnkAccountOut(BankAccountOut ankAccountOut) {
		this.ankAccountOut = ankAccountOut;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setAuthenticatedUser(Optional<User> authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
	
	
}

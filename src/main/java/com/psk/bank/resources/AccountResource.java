package com.psk.bank.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.psk.bank.model.Account;
import com.psk.bank.model.AccountEntity;
import com.psk.bank.model.Transaction;
import com.psk.bank.model.TransactionEntity;
import com.psk.bank.repository.AccountRepository;
import com.psk.bank.repository.TransactionRepository;
import com.psk.bank.services.AccountService;

@ManagedBean
@RequestScoped
@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

	@Inject
	private AccountService accountService;
	@Inject
	private AccountRepository accountRepository;
	@Inject
	private TransactionRepository transactionRepository;

	
	public static class AccountValue {
		private String accountNum;
		private BigDecimal value;

		public AccountValue() {
		}

		public AccountValue(String accountNum, BigDecimal value) {
			this.accountNum = accountNum;
			this.value = value;
		}

		public String getAccountNum() {
			return accountNum;
		}

		public void setAccountNum(String accountNum) {
			this.accountNum = accountNum;
		}

		public BigDecimal getValue() {
			return value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}
	}

	// GET,POST ...

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAccount() {
		return Response.status(200).entity("GET :getAccount is called").build();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response addAccount() {
		return Response.status(200).entity("POST :addAccount is called").build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateAccount() {
		return Response.status(200).entity("PUT :updateAccount is called").build();
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteAccount() {
		return Response.status(200).entity("DELETE :deleteAccount is called").build();
	}

	/// PATH

	@GET
	@Path("/vip")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getVipAccount() {
		return Response.status(200).entity("getVipAccount is called").build();
	}

	@GET
	@Path("/vip/admin")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAdminAccount() {
		return Response.status(200).entity("getAdminUser is called").build();
	}

	///// @Produces

	@GET
	@Path("/accountsJson")
	public Response getAccountsJson() {
		return Response.status(200).entity(accountRepository.findAll()).build();
	}

	@GET
	@Path("/accountJson")
	public Response getAccountJson() {
		return Response.status(200).entity(accountRepository.findOne("ABC1")).build();
	}
	
	@GET
	@Path("/accountXml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAccountXml() {
		return Response.status(200).entity(accountRepository.findOne("ABC1")).build();
	}
	
	
	
	/// @PathParam

	
	@GET
	@Path("/getAccountByNumber/{number}")
	public Response getAccount(@PathParam("number") String number) {
		
		return Response.status(200).entity(accountRepository.findOne(number)).build();
	}
	

	@DELETE
	@Path("/deleteAccountByNumber/{number}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteAccount(@PathParam("number") String number) {
		return Response.status(200).entity("deleteAccount with number: " + number).build();
	}

	//// @FormParam

	@POST
	@Path("/addAccount")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addAccount(@FormParam("accountNumber") String accountNumber,
			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
		accountRepository.save(
				AccountEntity.newInstance(accountNumber, firstName, lastName, true, LocalDateTime.parse("2017-01-01T21:32:00")));
		return Response.status(200).entity("Account added successfully").build();
	}

	@PUT
	@Path("/updateAccountWithNumber")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateAccountWithNumber(@FormParam("number") long number) {
		return Response.status(200).entity("updateAccountWithNumber number: " + number).build();
	}

	/// @QueryParam

	@GET
	@Path("/filteredAccounts")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getFilteredAccounts(@QueryParam("firstName") String firstName,
			 @QueryParam("lastName") String lastName) {

		return Response.status(200).entity(
				"getFilteredAccounts is called, for firstName: "+firstName+" and lastName: "+lastName)
				.build();
	}
	

	
	//////////////


	@POST
	@Path("{accountNum}/fill")
	public Response fillTransactions(@PathParam("accountNum") String accountNum) {
		Account account = accountRepository.findOne(accountNum);
		if (account == null) {
			return Response.status(404).build();
		}
		TransactionEntity entity = TransactionEntity.newInstance(transactionRepository.nextId(), "XCC1",
				BigDecimal.valueOf(12L), false, LocalDateTime.now(), LocalDateTime.now(), account);
		transactionRepository.save(entity);
		return Response.status(201).entity(entity).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{accountNum}")
	public AccountValue getAccountValue(@PathParam("accountNum") String accountNum) {
		Account account = accountRepository.findOne(accountNum);
		return new AccountValue(accountNum, accountService.getBalance(account));
	}
	
	
	@GET
	@Path("{accountNum}/transactions")
	public List<Transaction> getTransactions(@PathParam("accountNum") String accountNum) {
		Account account = accountRepository.findOne(accountNum);
		if (account == null) {
			return Collections.emptyList();
		}
		return transactionRepository.findAllByAccount(account);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("info")
	public String getInfo() {
		return accountService.getInfo();
	}
}

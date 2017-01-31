package com.psk.bank.resources;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.psk.bank.model.Account;
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

    @GET
    @Path("{accountNum}")
    public AccountValue getAccountValue(@PathParam("accountNum") String accountNum) {
        Account account = accountRepository.findOne(accountNum);
        return new AccountValue(accountNum, accountService.getBalance(account));
    }
    
    @POST
    @Path("{accountNum}/fill")
    public Response fillTransactions(@PathParam("accountNum") String accountNum) {
        Account account = accountRepository.findOne(accountNum);
        if(account == null) {
            return Response.status(404).build();
        }
        TransactionEntity entity = TransactionEntity.newInstance(transactionRepository.nextId(), "XCC1", BigDecimal.valueOf(12L), false, LocalDateTime.now(), LocalDateTime.now(), account);
        transactionRepository.save(entity);
        return Response.status(201).entity(entity).build();
    }
    
    @GET
    @Path("{accountNum}/transactions")
    public List<Transaction> getTransactions(@PathParam("accountNum") String accountNum) {
        Account account = accountRepository.findOne(accountNum);
        if(account == null) {
            return Collections.emptyList();
        }
        return transactionRepository.findAllByAccount(account);
    }

    @GET
    @Path("info")
    public String getInfo() {
        return accountService.getInfo();
    }
}

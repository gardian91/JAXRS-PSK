package com.psk.bank.resources;


import java.math.BigDecimal;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.psk.bank.model.Account;
import com.psk.bank.repository.AccountRepository;
import com.psk.bank.services.AccountService;

@ManagedBean
@RequestScoped
@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    
    @Inject
    private AccountService accountService;
    @Inject
    private AccountRepository repository;
    
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
    public AccountValue getOrders(@PathParam("accountNum") String accountNum) {
        return new AccountValue(accountNum, BigDecimal.TEN);
    }

    @GET
    @Path("info")
    public String getOrdersSummary() {
        return accountService.getInfo();
    }
}

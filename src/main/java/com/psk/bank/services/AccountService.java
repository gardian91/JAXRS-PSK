package com.psk.bank.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.psk.bank.model.Account;
import com.psk.bank.model.Transaction;
import com.psk.bank.repository.TransactionRepository;

@ManagedBean
@ApplicationScoped
public class AccountService {
    
    @Inject
    private TransactionRepository transactionRepository;
    
    public BigDecimal getBalance(Account account) {
        List<Transaction> list = transactionRepository.findAllByAccount(account);
        return list.stream().collect(Collectors.reducing(BigDecimal.ZERO,t -> t.getValue(),BigDecimal::add));
    }
    
    public String getInfo() {
        return "POK";
    }
}

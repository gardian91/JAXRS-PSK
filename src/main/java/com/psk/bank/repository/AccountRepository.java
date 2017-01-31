package com.psk.bank.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psk.bank.model.Account;
import com.psk.bank.model.AccountEntity;

@ManagedBean
@ApplicationScoped
public class AccountRepository  implements Repository<Account, String>{

        private Map<String, Account> storage = new HashMap<>();
        
        public AccountRepository() {
            save(AccountEntity.newInstance("ABC1","Jan","Kowalski",true, LocalDateTime.parse("2017-01-01T21:32:00")));
            save(AccountEntity.newInstance("ABC2","Tomasz","Kot",true, LocalDateTime.parse("2017-01-05T22:45:00")));
            save(AccountEntity.newInstance("ABC3","Jan","Kowalski",true, LocalDateTime.parse("2017-01-07T12:08:00")));
        }
    
	@Override
	public void save(Account object) {
	    storage.put(object.getAccountNumber(), object);
	}

	@Override
	public Account findOne(String accountNumber) {
		return storage.get(accountNumber);
	}

	@Override
	public List<Account> findAll() {
		return storage.values().stream().collect(Collectors.toList());
	}


}

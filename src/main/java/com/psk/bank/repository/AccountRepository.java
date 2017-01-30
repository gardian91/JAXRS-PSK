package com.psk.bank.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.psk.bank.model.Account;

@ApplicationScoped
public class AccountRepository  implements Repository<Account, String>{

	@Override
	public void save(Account object) {
		
	}

	@Override
	public Account findOne(String id) {
		return null;
	}

	@Override
	public List<Account> findAll() {
		return null;
	}


}

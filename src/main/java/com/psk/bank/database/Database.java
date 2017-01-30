package com.psk.bank.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.psk.bank.model.Account;
import com.psk.bank.model.Transaction;

public class Database {
	public Database(){
		
	}
	public List<Account> getAccounts(){
		return new ArrayList<Account>() {{
//			add(new DefaultAccount("1","firstName1","lastName1",new Date(),true));
		}};
	}
	public List<Transaction> getTransactions() {
		return new ArrayList<Transaction>() {{
//			add(new DefaultTransaction("500","1",true,new Date(),new Date(),1));
		}};
	}
	
}

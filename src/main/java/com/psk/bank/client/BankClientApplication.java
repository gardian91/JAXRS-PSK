package com.psk.bank.client;

import com.psk.bank.resources.AccountResource.AccountValue;

public class BankClientApplication {

    public static void main(String[] args) {
        BankClient client = new BankClient("http://localhost:8080/Bank/api/");
        
        System.out.println(client.getInfo());
        System.out.println(client.getAccountAsStr("ABC1"));
        AccountValue accountVal = client.getAccount("ABC1");
        System.out.println(accountVal);
    }

}

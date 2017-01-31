package com.psk.bank.services;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class AccountService {
    public String getInfo() {
        return "POK";
    }
}

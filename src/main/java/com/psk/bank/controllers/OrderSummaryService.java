package com.psk.bank.controllers;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;


@ManagedBean
@ApplicationScoped
public class OrderSummaryService {
    public String getSummary() {
        return "All are ok";
    }
}

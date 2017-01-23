package com.tt.jaxrs;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderSummaryService {
    public String getSummary() {
        return "All are ok";
    }
}

package com.tt.jaxrs;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingCard {
    public String getString() {
        return "GREET";
    }
}

package com.psk.bank.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.psk.bank.resources.AccountResource.AccountValue;

public class BankClient {

    private Client client;
    private WebTarget target;

    public BankClient(String url) {
        ClientConfig config = new ClientConfig();
     //   config.register(JacksonJsonProvider.class);
        
        client = ClientBuilder.newClient(config);
        target = client.target(url);
    }

    public String getInfo() {
        return target.path("/accounts/info").request(MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public String getAccountAsStr(String accountNumber) {
        return target.path("/accounts/{id}").resolveTemplate("id", accountNumber).request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }

    public AccountValue getAccount(String accountNumber) {
        return target.path("/accounts/{id}").resolveTemplate("id", accountNumber).request(MediaType.APPLICATION_JSON)
                .get(AccountValue.class);
    }
}

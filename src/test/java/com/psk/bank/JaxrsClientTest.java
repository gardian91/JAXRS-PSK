package com.psk.bank;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.psk.bank.configuration.ObjectMapperContextResolver;
import com.psk.bank.model.TransactionEntity;
import com.psk.bank.resources.AccountResource.AccountValue;

public class JaxrsClientTest {

    @Test
    public void shouldReturnString() {
        Client client = ClientBuilder.newClient();

        String result = client.target("http://localhost:8080/Bank/").path("api/accounts/{id}")
                .resolveTemplate("id", "ABC1").request(MediaType.APPLICATION_JSON_TYPE).get(String.class);

        assertThat(result).isNotNull();

        System.out.println(result);
    }

    @Test
    public void shouldReturnObject() {
        ClientConfig config = new ClientConfig();
        config.register(JacksonJsonProvider.class);
        config.register(ObjectMapperContextResolver.class);

        Client client = ClientBuilder.newClient(config);

        AccountValue result = client.target("http://localhost:8080/Bank/").path("api/accounts/{id}")
                .resolveTemplate("id", "ABC1").request(MediaType.APPLICATION_JSON_TYPE).get(AccountValue.class);

        assertThat(result).isNotNull();

        System.out.println(result);
    }

    @Test
    public void shouldPostEmptyAndReturnObjects() {
        ClientConfig config = new ClientConfig();
        config.register(JacksonJsonProvider.class);
        config.register(ObjectMapperContextResolver.class);

        Client client = ClientBuilder.newClient(config);

        TransactionEntity result1 = client.target("http://localhost:8080/Bank/").path("api/accounts/{id}/fill")
                .resolveTemplate("id", "ABC1").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(null), TransactionEntity.class);

        assertThat(result1).isNotNull();
        System.out.println(result1);
    }

    @Test
    public void shouldPostFormAndReturnStatus200withMessage() {
        ClientConfig config = new ClientConfig();
        config.register(JacksonJsonProvider.class);
        config.register(ObjectMapperContextResolver.class);

        Client client = ClientBuilder.newClient(config);

        Form form = new Form();
        form.param("accountNumber", "XXX1");
        form.param("firstName", "Jan");
        form.param("lastName", "Kowalski");

        Response result = client.target("http://localhost:8080/Bank/").path("api/accounts/addAccount")
                .resolveTemplate("id", "ABC1").request(MediaType.TEXT_PLAIN).post(Entity.form(form));

        String msg = result.readEntity(String.class);
        System.out.println(msg);

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(200);
        assertThat(msg).isEqualTo("Account added successfully");

    }
}

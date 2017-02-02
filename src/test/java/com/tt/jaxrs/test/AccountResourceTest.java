package com.tt.jaxrs.test;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.psk.bank.repository.AccountRepository;
import com.psk.bank.repository.TransactionRepository;
import com.psk.bank.resources.AccountResource;
import com.psk.bank.resources.AccountResource.AccountValue;
import com.psk.bank.services.AccountService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class AccountResourceTest extends JerseyTest {

    private static final String RESOURCE_URL = "accounts";

    @Mock
    private AccountService service;
    
    @Mock
    private AccountRepository repository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountResource resource;

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        MockitoAnnotations.initMocks(this);
        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(repository).to(AccountRepository.class);
                bind(service).to(AccountService.class);
            }
        });

        config.registerInstances(resource);

        return config;
    }

    @Test
    public void accountInfoShouldPassInfoText() {
        when(service.getInfo()).thenReturn("TEST");
        String response = target(RESOURCE_URL+"/info").request().get(String.class);
        assertThat(response).isEqualTo("TEST");
    }

    @Test
    public void accountValueShouldHaveBalanceValueResponseAsString() {
        String order = target(RESOURCE_URL+"/453").request().get(String.class);
        assertThat(order).contains("accountNum", "value");
    }

    @Ignore
    @Test
    public void accountValueShouldHaveBalanceValue() {
        AccountValue order = target(RESOURCE_URL+"/453").request().get(AccountValue.class);
        assertThat(order.getAccountNum()).isEqualTo("453");
        assertThat(order.getValue()).isEqualTo(BigDecimal.TEN);
    }

    @Ignore
    @Test
    public void accountValueShouldHaveBalanceValueWithTemplateParam() {
        Response response = target(RESOURCE_URL+"/{accountNum}").resolveTemplate("accountNum", "453").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);

        AccountValue order = response.readEntity(AccountValue.class);
        assertThat(order.getAccountNum()).isEqualTo("453");
        assertThat(order.getValue()).isEqualTo(BigDecimal.TEN);
    }

}

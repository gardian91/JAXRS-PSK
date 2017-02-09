package com.psk.bank.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.psk.bank.resources.AccountResource.AccountValue;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BankClientTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);
    
    private ObjectMapper mapper = new ObjectMapper();
    private BankClient bank;
    
    @Before
    public void doBefore() {
        bank = new BankClient("http://localhost:8089/");
    }
    
    @Test
    public void shouldGetInfoAboutAccounts() {
        stubFor(get(urlEqualTo("/accounts/info"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("TEST")));

        String result = bank.getInfo();

        assertThat(result).isEqualTo("TEST");

        verify(getRequestedFor(urlMatching("/accounts/info")));
    }
    
    
    
    @Test
    public void shouldGetAccountValue() throws JsonGenerationException, JsonMappingException, IOException {
        AccountValue expectedVal = new AccountValue("ABC1",BigDecimal.TEN);
        
        stubFor(get(urlEqualTo("/accounts/ABC1"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(mapper.writeValueAsString(expectedVal))));

        AccountValue result = bank.getAccount("ABC1");

        assertThat(result).isEqualsToByComparingFields(expectedVal);

        verify(getRequestedFor(urlMatching("/accounts/ABC1")));
    }
}

package com.tt.jaxrs.test;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tt.jaxrs.GreetingCard;
import com.tt.jaxrs.OrderService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class OrderServiceTest extends JerseyTest {

    @Mock
    private GreetingCard greetingCard;

    @InjectMocks
    private OrderService orderService;

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        MockitoAnnotations.initMocks(this);
        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(greetingCard).to(GreetingCard.class);
            }
        });
        
        config.registerInstances(orderService);

        return config;
    }

    @Test
    public void ordersPathParamTest() {
        String response = target("orders/453").request().get(String.class);
        Assert.assertTrue("orderId: 453".equals(response));
    }
    
    @Test
    public void ordersPathParamTest2() {
        Response response = target("orders/453").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMediaType()).isEqualTo("application/json");
        assertThat(response.readEntity(String.class)).isEqualTo("orderId: 453");
    }

    @Test
    public void ordersFixedPathTest() {
        when(greetingCard.getString()).thenReturn("TEST");
        String response = target("orders/summary").request().get(String.class);
        assertThat(response).isEqualTo("TEST");
    }

}

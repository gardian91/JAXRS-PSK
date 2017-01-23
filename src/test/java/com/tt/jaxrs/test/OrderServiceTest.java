package com.tt.jaxrs.test;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tt.jaxrs.OrderSummaryService;
import com.tt.jaxrs.OrderService;
import com.tt.jaxrs.OrderService.Order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class OrderServiceTest extends JerseyTest {

    @Mock
    private OrderSummaryService orderSummary;

    @InjectMocks
    private OrderService orderService;

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        MockitoAnnotations.initMocks(this);
        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(orderSummary).to(OrderSummaryService.class);
            }
        });
        
        config.registerInstances(orderService);

        return config;
    }

    @Test
    public void ordersPathParamTest() {
        Order order = target("orders/453").request().get(Order.class);
        assertThat(order.getOrderId()).isEqualTo(453L);
        assertThat(order.getValue()).isEqualTo(BigDecimal.TEN);
    }
    
    @Test
    public void ordersPathParamTest2() {
        Response response = target("orders/453").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
        
        Order order = response.readEntity(Order.class);
        assertThat(order.getOrderId()).isEqualTo(453L);
        assertThat(order.getValue()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void ordersFixedPathTest() {
        when(orderSummary.getSummary()).thenReturn("TEST");
        String response = target("orders/summary").request().get(String.class);
        assertThat(response).isEqualTo("TEST");
    }

}

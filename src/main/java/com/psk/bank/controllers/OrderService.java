package com.psk.bank.controllers;


import java.math.BigDecimal;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ManagedBean
@RequestScoped
@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {
    
    @Inject
    private OrderSummaryService orderSummary;
    
    public static class Order {
        private Long orderId;
        private BigDecimal value;
        
        
        public Order() {
        }

        public Order(Long orderId, BigDecimal value) {
            this.orderId = orderId;
            this.value = value;
        }

        public Long getOrderId() {
            return orderId;
        }

        
        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        
        public BigDecimal getValue() {
            return value;
        }

        
        public void setValue(BigDecimal value) {
            this.value = value;
        }
    }

    @GET
    @Path("{orderId}")
    public Order getOrders(@PathParam("orderId") Long orderId) {
        return new Order(orderId, BigDecimal.TEN);
    }

    @GET
    @Path("summary")
    public String getOrdersSummary() {
        return orderSummary.getSummary();
    }
}

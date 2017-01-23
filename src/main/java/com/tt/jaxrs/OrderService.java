package com.tt.jaxrs;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("orders")
@Produces("application/json")
public class OrderService {
    
    @Inject
    private GreetingCard greetingCard;

    @GET
    @Path("{orderId}")
    public String getOrders(@PathParam("orderId") String orderId) {
        return "orderId: " + orderId;
    }

    @GET
    @Path("summary")
    public String getOrdersSummary() {
        return greetingCard.getString();
    }
}

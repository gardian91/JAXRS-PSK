package com.tt.jaxrs;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class BankAccountRestService {

	@GET
	@Path("/balance/{accountNumber : \\d+}")
	public Response getAccountBalance(@PathParam("accountNumber") String accountNumber) {

		return Response.status(200).entity("getAccountBalance is called " +accountNumber).build();

	}
	
	@GET
	@Path("/balance")
	public Response getAllAccounts(@PathParam("accountNumber") String accountNumber) {
		return Response.status(200).entity("getAllAccounts is called").build();
	}
	
	
	@GET
	@Path("/filteredAccounts")
	public Response getFilteredAccounts(
		@DefaultValue("1000") @QueryParam("from") int from,
		@DefaultValue("10000")@QueryParam("to") int to,
		@DefaultValue("balance") @QueryParam("orderBy") List<String> orderBy) {

		return Response
		   .status(200)
		   .entity("getFilteredAccounts is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();

	}
	
	
	
	@POST
	@Path("/addAccount")
	public Response addAccount(
		@FormParam("type") String type,
		@FormParam("balance") int balance) {

		
		System.out.println("addAccount is called");
		return Response.status(200)
			.entity("addAccount is called, type : " + type + ", balance : " + balance)
			.build();

	}
	
	@GET
	@Path("/getHeaderInfo")
	public Response getHeaderInfo(@Context HttpHeaders headers) {

		String userAgent = headers.getRequestHeader("user-agent").get(0);

		for(String header : headers.getRequestHeaders().keySet()){
			System.out.println(header);
		}
		
		
		return Response.status(200)
			.entity("addUser is called, userAgent : "+userAgent )
			.build();

	}
	
	
}

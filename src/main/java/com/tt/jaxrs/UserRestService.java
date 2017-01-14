package com.tt.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {

	@GET
	public Response getUser() {

		return Response.status(200).entity("getUser is called").build();

	}

	@GET
	@Path("/vip")
	public Response getUserVIP() {

		return Response.status(200).entity("getUserVIP is called").build();

	}

	@GET
	@Path("{name}")
	public Response getUserByName(@PathParam("name") String name) {

		return Response.status(200)
				.entity("getUserByName is called, name : " + name).build();

	}

	@GET
	@Path("{id : \\d+}")
	public Response getUserById(@PathParam("id") String id) {

		return Response.status(200).entity("getUserById is called, id : " + id)
				.build();

	}

	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
	public Response getUserByUserName(@PathParam("username") String username) {

		return Response.status(200)
				.entity("getUserByUserName is called, username : " + username)
				.build();

	}

	@GET
	@Path("/books/{isbn : \\d+}")
	public Response getUserBookByISBN(@PathParam("isbn") String isbn) {

		return Response.status(200)
				.entity("getUserBookByISBN is called, isbn : " + isbn).build();

	}
	
	
	@POST
	@Path("/sendemail")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sendEmail(@FormParam("email") String email) {
		System.out.println("receive plain text");
		System.out.println(email);
		return Response.ok("email=" + email).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/sendemail")
	public Response sendJsonData(String email) {
		System.out.println("receive json data");
		System.out.println(email);
		return Response.ok(email).build();
	}
	
	
	@PUT
	@Path("/update")
	public Response updatingResource(@FormParam("firstName") String firstName) {
		System.out.println("Update recource");
		System.out.println(firstName);
		return Response.ok("updatingResource").build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response deleteResource(@PathParam("id") String id) {
		System.out.println("Delete recource");
		System.out.println(id);
		return Response.ok("deleteResource").build();
	}
	

}
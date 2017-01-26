package com.tt.jaxrs;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {

	
	////GET,POST,PUT,DELETE
	
	@GET
	public Response getUser() {

		return Response.status(200).entity("GET :getUser is called").build();

	}

	@POST
	public Response addUser() {

		return Response.status(200).entity("POST :addUser is called").build();

	}	

	@PUT
	public Response updateUser() {

		return Response.status(200).entity("PUT :updateUser is called").build();

	}
	
	@DELETE
	public Response deleteUser() {

		return Response.status(200).entity("DELETE :deleteUser is called").build();

	}	
	
	///PATH
	
	@GET
	@Path("/vip")
	public Response getVipUser() {
		return Response.status(200).entity("getVipUser is called").build();
	}
	
	@GET
	@Path("/vip/admin")
	public Response getVipAdminUser() {
		return Response.status(200).entity("getVipAdminUser is called").build();
	}
	
	
	/////@Produces
	
	@GET
	@Path("/vipJson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserVIP() {
		
		User user = new User();
		user.setAge(22);
		user.setName("OLA");
		return Response.status(200).entity(user).build();
	}
	
	@GET
	@Path("/vipXml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getUserVIPPlainText() {

		User user = new User();
		user.setAge(22);
		user.setName("OLA");
		return Response.status(200).entity(user).build();
	}
	
	
	/////@PathParam
	
	@GET
	@Path("{name}")
	public Response getUserByName(@PathParam("name") String name) {
		return Response.status(200)
				.entity("getUserByName is called, name : " + name).build();
	}
	
	@GET
	@Path("{name}&{age}")
	public Response getUserByCriteria(@PathParam("name") String name,@PathParam("age") String age) {
		return Response.status(200)
				.entity("getUserByCriteria is called, name : " + name + " age: " + age).build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response deleteUser(@PathParam("id") String id) {
		System.out.println("Delete user");
		System.out.println(id);
		return Response.status(200).entity("deleteUser id: "+id).build();
	}
	
	
	
	////@FormParam
	
	@POST
	@Path("/addUser")
	public Response addUser(@FormParam("name") String name, @FormParam("age") int age) {
		return Response.status(200).
				entity("addUser is called, name : " + name + ", age : " + age).build();
	}
	
	
	@PUT
	@Path("/update")
	public Response updateUser(@FormParam("name") String name) {
		System.out.println("Update User");
		System.out.println(name);
		return Response.status(200).entity("updateUser name: "+name).build();
	}
	
    ///@QueryParam
	
	@GET
	@Path("/filteredAccounts")
	public Response getFilteredUsers(
		@DefaultValue("20") @QueryParam("from") int from,
		@DefaultValue("30")@QueryParam("to") int to,
		@DefaultValue("Jan") @QueryParam("orderBy") List<String> orderBy) {

		return Response
		   .status(200)
		   .entity("getFilteredUsers is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
	}
	
	

}
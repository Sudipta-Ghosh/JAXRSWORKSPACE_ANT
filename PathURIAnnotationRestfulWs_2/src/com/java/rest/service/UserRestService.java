package com.java.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/users")
public class UserRestService {
 
	@GET
	@Path("{id : \\d+}") //support digit only
	public Response getUserById(@PathParam("id") String id) {
 	   return Response.status(200).entity("getUserById is called, id : " + id).build();
 	}
 
	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
	public Response getUserByUserName(@PathParam("username") String username) {
 	   return Response.status(200).entity("getUserByUserName is called, username : " + username).build();
 
	}
	
 	@GET
	@Path("/books/{isbn : \\d+}")
	public Response getUserBookByISBN(@PathParam("isbn") String isbn) {
 
	   return Response.status(200).entity("getUserBookByISBN is called, isbn : " + isbn).build();
 
	}
 
}
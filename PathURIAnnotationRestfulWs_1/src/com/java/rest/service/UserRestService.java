package com.java.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
 
@Path("/users")
public class UserRestService {
 
	@GET
	public Response getUser() { 
		return Response.status(200).entity("getUser is called").build(); 
	}
 	@GET
	@Path("/SUPERUSER")
	public Response getUserVIP() {
 		return Response.status(200).entity("getUser SUPERUSER is called").build();
 	}
}

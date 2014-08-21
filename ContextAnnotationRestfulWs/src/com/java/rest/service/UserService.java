package com.java.rest.service;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
 
@Path("/users")
public class UserService {
 
	@GET
	@Path("/query")
	public Response getUsers(@Context UriInfo info) {
 
		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");
 
		return Response
		   .status(200)
		   .entity("getUsers is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
 
	}
	
	@GET
	@Path("/get")
	public Response addUser(@Context HttpHeaders headers) {
 		String userAgent = headers.getRequestHeader("user-agent").get(0);
 		return Response.status(200).entity("addUser is called, userAgent : " + userAgent).build();
 	}
}
package com.webcontext.libs.zelibrairie.services.ws;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webcontext.libs.zelibrairie.model.User;
import com.webcontext.libs.zelibrairie.services.UserService;

/**
 * Rest Service providing access to User entity.
 * will retrieve all occurence from UserService.
 */
@Path("/users")
public class UserRestService {
	
	/**
	 * Internal Service.
	 */
	private static UserService userService  = new UserService();


	/**
	 * Default constructor.
	 */
	public UserRestService(){

	}

	/**
	 * Retrieve all users from <code>User</code> entity.
	 */
	@Path("/")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response findAll(){
		try{
			List<User> users = userService.findAll();
			if(users==null){
				return Response.status(Response.Status.NOT_FOUND).build();
			}else{
				return Response.ok().entity(users).build();
			}
		}catch(Exception e){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(e).build();
		}
	}

	@Path("/{username}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response findByUsername(@PathParam("username") String username){
		try{
			User user = userService.findByUsername(username);
			if(user==null){
				return Response.status(Response.Status.NOT_FOUND).build();
			}else{
				return Response.ok().entity(user).build();
			}
		}catch(Exception e){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(e).build();
		}
	}

	@Path("/{username}")
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Response update(@FormParam("user") User userupdate){
		try{
			User user = userService.update(userupdate);
			if(user==null){
				return Response.status(Response.Status.NOT_FOUND).build();
			}else{
				return Response.ok().entity(user).build();
			}
		}catch(Exception e){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(e).build();
		}
	}

	@Path("/")
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(@FormParam("username") User username){
		try{
			User user = userService.add(username);
			if(user==null){
				return Response.status(Response.Status.NOT_FOUND).build();
			}else{
				return Response.ok().entity(user).build();
			}
		}catch(Exception e){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(e).build();
		}
	}


}
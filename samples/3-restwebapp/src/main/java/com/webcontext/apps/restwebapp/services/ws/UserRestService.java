package com.webcontext.apps.restwebapp.services.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webcontext.apps.restwebapp.exceptions.EntityAlreadyExistsException;
import com.webcontext.apps.restwebapp.model.User;
import com.webcontext.apps.restwebapp.services.business.UserService;

/**
 * Rest Service providing access to User entity. will retrieve all occurrence
 * from UserService.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 */
@Path("/users")
public class UserRestService {

	/**
	 * Internal Service.
	 */
	@Inject
	private static UserService userService;

	/**
	 * Default constructor.
	 */
	public UserRestService() {

	}

	/**
	 * Retrieve all users from <code>User</code> entity.
	 * <ul>
	 * <li><code>OK</code> the data retrieve with success.
	 * <li>
	 * <li><code>NOT_FOUND</code> no data exists.
	 * <li>
	 * <li><code>INTERNAL_SERVER_ERROR</code> error during processing.
	 * <li>
	 * </ul>
	 */
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		try {
			List<User> users = userService.findAll();
			if (users == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(users).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e).build();
		}
	}

	/**
	 * Retrieve a User from persistence based on the <code>username</code>.
	 * <ul>
	 * <li><code>OK</code> the data is saved with success
	 * <li>
	 * <li><code>NOT_FOUND</code> this data does not exist
	 * <li>
	 * <li><code>INTERNAL_SERVER_ERROR</code> error during processing.
	 * <li>
	 * </ul>
	 * 
	 * @param username
	 * @return
	 */
	@Path("/{username}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByUsername(@PathParam("username") String username) {
		try {
			User user = userService.findByUsername(username);
			if (user == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(user).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e).build();
		}
	}

	/**
	 * Retrieve a User from persistence based on its <code>userId</code>.
	 * <ul>
	 * <li><code>OK</code> the data is saved with success
	 * <li>
	 * <li><code>NOT_FOUND</code> this data does not exist
	 * <li>
	 * <li><code>INTERNAL_SERVER_ERROR</code> error during processing.
	 * <li>
	 * </ul>
	 * 
	 * @param userId
	 * @return
	 */
	@Path("/id/{userId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findById(@PathParam("userId") Long userId) {
		try {
			User user = userService.findById(userId);
			if (user == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(user).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e).build();
		}
	}

	
	
	/**
	 * Update the User to persistence.
	 * <ul>
	 * <li><code>OK</code> the data is saved with success
	 * <li>
	 * <li><code>NOT_FOUND</code> this data does not exist
	 * <li>
	 * <li><code>INTERNAL_SERVER_ERROR</code> error during processing.
	 * <li>
	 * </ul>
	 * 
	 * @param userupdate
	 * @return
	 */
	@Path("/{username}")
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	public Response update(@FormParam("user") User userupdate) {
		try {
			User user = userService.update(userupdate);
			if (user == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(user).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e).build();
		}
	}

	/**
	 * Save a new User entity to persistence. The HTTP status code return would
	 * be:
	 * <ul>
	 * <li><code>CREATED</code> the data is saved with success
	 * <li>
	 * <li><code>CONFLICT</code> this data already exists
	 * <li>
	 * <li><code>INTERNAL_SERVER_ERROR</code> error during processing.
	 * <li>
	 * </ul>
	 * 
	 * @param userSave
	 * @return
	 */
	@Path("/")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response save(@FormParam("user") User userSave) {
		try {
			User user = userService.add(userSave);
			if (user == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.status(Response.Status.CREATED).entity(user)
						.build();
			}
		} catch (EntityAlreadyExistsException eaee) {
			return Response.status(Response.Status.CONFLICT).entity(eaee)
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e).build();
		}
	}

}
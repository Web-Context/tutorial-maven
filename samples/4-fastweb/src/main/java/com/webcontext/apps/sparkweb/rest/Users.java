package com.webcontext.apps.sparkweb.rest;

import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.delete;


import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;

import com.webcontext.apps.sparkweb.dao.UserDao;
import com.webcontext.apps.sparkweb.model.User;

/**
 * A simple RESTful example showing how-to create, get, update and delete User
 * resources.
 */
public class Users {

	/**
	 * User DAO.
	 */
	private static UserDao users = new UserDao("fastweb");

	public static void main(String[] args) {

		// create a brand new user
		post(new Route("/users") {
			@Override
			public Object handle(Request request, Response response) {
				User user;
				List<User> usersExists = users.findByUsername(request
						.queryParams("username"));
				if (usersExists.size() == 0) {
					user = new User(null, request.queryParams("username"),
							request.queryParams("firstname"),
							request.queryParams("lastname"),
							request.queryParams("email"),
							request.queryParams("password"));
					users.save(user);
					response.status(201); // 201 Created
				} else {
					user = usersExists.get(0);
					response.status(409); // 201 Created
				}
				return user.getId();
			}
		});

		// Gets the User resource for the provided id
		get(new Route("/users/:id") {
			@Override
			public Object handle(Request request, Response response) {
				Long id = Long.parseLong(request.params(":id"));
				User user = users.findById(id);
				if (user != null) {
					return "Firstname: " + user.getFirstname() + ", Lastname: "
							+ user.getLastname();
				} else {
					response.status(404); // 404 Not found
					return "user not found for id=" + id;
				}
			}
		});

		// Updates the User resource for the provided id with new information
		// author and title are sent as query parameters e.g.
		// /users/<id>?author=Foo&title=Bar
		put(new Route("/users/:id") {
			@Override
			public Object handle(Request request, Response response) {
				Long id = Long.parseLong(request.params(":id"));
				User user = users.findById(id);
				if (user != null) {
					user.setUsername(request.queryParams("username"));
					user.setFirstname(request.queryParams("firstname"));
					user.setLastname(request.queryParams("lastname"));
					user.setEmail(request.queryParams("email"));
					user.setPassword(request.queryParams("password"));

					return "User with id '" + id + "' updated";
				} else {
					response.status(404); // 404 Not found
					return "User not found";
				}
			}
		});

		// Deletes the User resource for the provided id
		delete(new Route("/users/:id") {
			@Override
			public Object handle(Request request, Response response) {
				Long id = Long.parseLong(request.params(":id"));
				User User = users.deleteById(id);
				if (User != null) {
					return "User with id '" + id + "' deleted";
				} else {
					response.status(404); // 404 Not found
					return "User not found";
				}
			}
		});

		// Gets all available User resources (id's)
		get(new Route("/users") {
			@Override
			public Object handle(Request request, Response response) {
				return users.findAll();
			}
		});

	}
}
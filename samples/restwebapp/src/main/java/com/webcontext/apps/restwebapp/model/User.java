package com.webcontext.apps.restwebapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * User is a user modeling for a System connected user.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "findByUsername", query = "SELECT u FROM User u WHERE u.username=:username"),
		@NamedQuery(name = "findAll", query = "SELECT u FROM User u ORDER BY u.username ASC"),
		@NamedQuery(name="count",query="SELECT count(u) as nbUser FROM User u")})
@Table(name="USERS")
public class User {
	@Id
	private String username;

	private String password;
	private String email;
	private String firstname;
	private String lastname;

	/**
	 * Default Standard constructor.
	 */
	public User() {
	}

	/**
	 * Default Standard constructor.
	 */
	public User(String username, String firstname, String lastname,
			String email, String password) {

		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (username != null)
			builder.append("username=").append(username).append(", ");
		if (password != null)
			builder.append("password=").append(password).append(", ");
		if (email != null)
			builder.append("email=").append(email).append(", ");
		if (firstname != null)
			builder.append("firstname=").append(firstname).append(", ");
		if (lastname != null)
			builder.append("lastname=").append(lastname);
		builder.append("]");
		return builder.toString();
	}

}

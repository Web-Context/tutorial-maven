package com.webcontext.apps.restwebapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * <p>
 * <b>User</b> is an entity modelizing a connected user.
 * </p>
 * <p>
 * This entity contains basic user information like <code>username</code>, <code>password</code>, but also
 * some personal information like {@link User#firstname}, {@link User#lastname} and {@link User#email}.
 * </p>
 * <p>
 * It also contained application internal data like User {@link User#status} figuring
 * if user is :
 * </p>
 * <ul>
 * <li><code>{@link Status#ACTIVE}</code>,</li>
 * <li><code>{@link Status#DISABLED}</code>,</li>
 * <li><code>{@link Status#BANNED}</code>.</li>
 * </ul>
 * <p>
 * and a {@link User#lastConnection} Date giving the last connection date.
 * </p>
 * <p>
 * the NamedQueries used are:
 * </p>
 * <ul>
 * <li><code>findByUsername</code> return a user corresponding to the
 * {@link User#username} attribute.</li>
 * <li><code>findById</code> return the user based on its internal unique
 * {@link User#id} attribute.</li>
 * <li><code>findAll</code> retrieve all users from the persistence container.</li>
 * <li><code>count</code> count number of users in persistence container.</li>
 * </ul>
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "findByUsername", query = "SELECT u FROM User u WHERE u.username=:username"),
		@NamedQuery(name = "findById", query = "SELECT u FROM User u WHERE u.id=:userId"),
		@NamedQuery(name = "findAll", query = "SELECT u FROM User u ORDER BY u.username ASC"),
		@NamedQuery(name = "count", query = "SELECT COUNT(u) FROM User u") })
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 4, max = 40, message = "Username must contains at least 4 characters and max to 40 char.")
	private String username;
	@Size(min = 4, max = 30, message = "The password must at least contains 4 characters and max to 30 char.")
	private String password;
	@Email(message = "Please enter a valid email")
	private String email;
	private String firstname;
	private String lastname;

	private Status status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastConnection;

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
		this.lastConnection = new Date();
		this.status = Status.ACTIVE;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=")
				.append(username).append(", password=").append(password)
				.append(", email=").append(email).append(", firstname=")
				.append(firstname).append(", lastname=").append(lastname)
				.append("]");
		return builder.toString();
	}

}

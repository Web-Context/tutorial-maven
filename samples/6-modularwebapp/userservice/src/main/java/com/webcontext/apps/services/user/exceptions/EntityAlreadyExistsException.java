package com.webcontext.apps.services.user.exceptions;

/**
 * Exception raised when the Entity already exists in the persisting system.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
public class EntityAlreadyExistsException extends Exception {

	/**
	 * Generated Unique Serial UID
	 */
	private static final long serialVersionUID = -3488973943052643796L;

	/**
	 * 
	 */
	public EntityAlreadyExistsException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EntityAlreadyExistsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EntityAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}

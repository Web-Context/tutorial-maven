/**
 * 
 */
package com.webcontext.libs.zelibrairie.exception;

/**
 * Exception raised when the Entity already exists in the persisting system.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
public class EntityAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	public EntityAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public EntityAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public EntityAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

/**
 * 
 */
package com.webcontext.apps.services.user.exceptions;

/**
 * Exception emitted on Persistence error.
 * 
 * @author Frédéric Delorme <frederic.delorme@gmail.com>
 * 
 */
public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 959438006442595348L;

	/**
	 *
	 */
	public DaoException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

}

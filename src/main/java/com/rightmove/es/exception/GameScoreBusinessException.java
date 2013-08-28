package com.rightmove.es.exception;

/**
 * Used for any business Exception in the GameScore system. This avoids conflict
 * with exceptions such as NullPointerException when catching.
 */
public class GameScoreBusinessException extends RuntimeException {

	private static final long	serialVersionUID	= 4758058987003372244L;

	public GameScoreBusinessException() {
		super();
	}

	public GameScoreBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GameScoreBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameScoreBusinessException(String message) {
		super(message);
	}

	public GameScoreBusinessException(Throwable cause) {
		super(cause);
	}

}

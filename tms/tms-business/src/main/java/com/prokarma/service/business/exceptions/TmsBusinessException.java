package com.prokarma.service.business.exceptions;

public class TmsBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TmsBusinessException() {
		super();
	}
	
	public TmsBusinessException(final String message) {
		super(message);
	}
	
	
	public TmsBusinessException(final String message, final Throwable e) {
		super(message, e);
	}
	
	public TmsBusinessException(final Throwable e) {
		super(e);
	}

}

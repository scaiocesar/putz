package com.dc3.service.exception;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2460859190015124135L;

	private String msgResource;

	/**
	 * Seta a mensagem e o propertie  do i18N da msg
	 * @param message
	 * @param msgResource
	 */
	public ServiceException(String message, String msgResource) {
		super(message);
		this.msgResource = msgResource;
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * 
	 * @return
	 */
	public String getMsgResource() {
		return msgResource;
	}

	public void setMsgResource(String msgResource) {
		this.msgResource = msgResource;
	}

}

package com.dc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class GenericController {

	@Autowired
	private MessageSource messageSource;

	public static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}

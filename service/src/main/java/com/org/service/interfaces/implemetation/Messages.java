package com.org.service.interfaces.implemetation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.org.service.interfaces.IMessages;

@Service
public class Messages implements IMessages {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String key, String... params) {

		return messageSource.getMessage(key, params, Locale.ENGLISH);
	}

	@Override
	public String getLocalizedMessage(Locale locale, String key, String... params) {

		return messageSource.getMessage(key, params, locale);
	}

}

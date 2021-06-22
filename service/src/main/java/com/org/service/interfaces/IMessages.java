package com.org.service.interfaces;

import java.util.Locale;

public interface IMessages {

	/**
     * Returns the message in English locale
     * 
     * @param key
     * @param params
     * @return
     */
    public String getMessage(String key, String... params);

    /**
     * Returns the localized message for the given key.
     * 
     * @param locale
     * @param key
     * @param params
     * @return
     */
    public String getLocalizedMessage(Locale locale, String key, String... params);

}

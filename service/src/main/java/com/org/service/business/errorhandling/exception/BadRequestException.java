package com.org.service.business.errorhandling.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.org.service.interfaces.IMessages;

public class BadRequestException extends AbstractApplicationException{

	 private static final String HEADER_FIELD_NAME = "HEADER_FIELD_NAME";
	    
	    private static final String ERROR_CODE = "ERROR_CODE";

	    private static final long serialVersionUID = 1L;

	    public static final String BAD_REQUEST_MSG = "DE-PERSON-00000001";
	    
	    public static final String JWTTOKEN_EXPIRED = "DE-PERSON-00000502";
	    
	    public static final String HEADER_VALUE_EMPTY = "DE-PERSON-00000501";
	    
	    public static final String NO_PERSON_PRESENT = "DE-PERSON-00000404";
	    
	    public static final String NO_PERSON_PRESENT_IN_REQUEST = "DE-PERSON-00000405";
	    
	    public static final String INVALID_STATE_CHANGE_STATUS = "DE-PERSON-00000400";
	    
	    public static final String MORE_THAN_ONE_PERSON_PRESENT_IN_REQUEST = "DE-PERSON-00000406";
	    
	    private final String message;

	    private final transient Map<String, Object> parameters;

	    private final IMessages messages;
	    
	    public BadRequestException(final String businessErrorCode, final IMessages messages) {
			this(businessErrorCode, null, messages);
		}

		public BadRequestException(final String businessErrorCode, final Throwable cause, final IMessages messages,
				final String... headerFieldNames) {
			super(cause);
			this.parameters = initMap(businessErrorCode, headerFieldNames);
			this.message = messages.getMessage(businessErrorCode == null ? BAD_REQUEST_MSG : businessErrorCode,
					headerFieldNames);
			this.messages = messages;
		}

		private Map<String, Object> initMap(final String businessErrorCode, final String... headerFieldName) {
			final Map<String, Object> ret = new HashMap<>(1);
			ret.put(HEADER_FIELD_NAME, headerFieldName);
			ret.put(ERROR_CODE, businessErrorCode == null ? BAD_REQUEST_MSG : businessErrorCode);
			return ret;
		}

	    @Override
	    public String getMessage() {

	        return message;
	    }

	    @Override
	    public Map<String, Object> getParameters() {

	        return Collections.unmodifiableMap(parameters);
	    }

	    @Override
	    public String getErrorCode() {

	    	return parameters.get(ERROR_CODE) == null ? BAD_REQUEST_MSG : (String) parameters.get(ERROR_CODE);
	         
	    }

	    @Override
	    public String getLocalizedMessage() {
	    	return messages.getLocalizedMessage(LocaleContextHolder.getLocale(), (String) parameters.get(ERROR_CODE),
					(String[]) parameters.get(HEADER_FIELD_NAME));
	    }
}

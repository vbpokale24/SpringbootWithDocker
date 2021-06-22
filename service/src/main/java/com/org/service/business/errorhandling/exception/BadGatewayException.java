package com.org.service.business.errorhandling.exception;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import com.org.service.interfaces.IMessages;

public class BadGatewayException extends AbstractApplicationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String message;

    private final transient Map<String, Object> parameters;

    private final IMessages messages;

    public static final String SERVICE_NAME = "SERVICE_NAME";

    public static final String ERROR_CODE = "400";
    public static final String GATEWAY_STATUS = "GATEWAY_STATUS";
    public static final String GATEWAY_ERR_MSG = "GATEWAY_ERR_MSG";

    private static final String MSG_ID = "DE-CUSTOMER-00000001";

    public BadGatewayException(final String serviceName, final String gatewayCode, final String gatewayErrMsg,
                               final HttpStatus gatewayStatus, final Throwable cause, final IMessages messages) {
        super(cause);

        this.parameters = initMap(serviceName, gatewayCode, gatewayErrMsg, gatewayStatus);
        this.message = cause.getMessage();
        this.messages = messages;
    }

    private Map<String, Object> initMap(final String serviceName, final String gatewayCode, final String gatewayErrMsg,
                                        final HttpStatus gatewayStatus) {
        final Map<String, Object> ret = new HashMap<>();
        ret.put(SERVICE_NAME, serviceName);
        ret.put(ERROR_CODE, gatewayCode);
        ret.put(GATEWAY_ERR_MSG, gatewayErrMsg);
        ret.put(GATEWAY_STATUS, gatewayStatus);
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
        String errorCode = (String) parameters.get(ERROR_CODE);
        return errorCode != null ? errorCode : MSG_ID;
    }
    
    @Override
    public String getLocalizedMessage() {
        return messages.getLocalizedMessage(LocaleContextHolder.getLocale(), MSG_ID, 
            (String) parameters.get(SERVICE_NAME), 
            (String) parameters.get(GATEWAY_ERR_MSG));
    }

}
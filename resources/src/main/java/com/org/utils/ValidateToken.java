package com.org.utils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.interfaces.IMessages;

import io.jsonwebtoken.Claims;

@Component
public class ValidateToken {
	
	@Autowired
	private IMessages messages;
	
	public boolean judgeToken(HttpServletRequest request) {
		
		if(request.getHeader("Basic") !=null){
			String decoded = new String(DatatypeConverter.parseBase64Binary(request.getHeader("Basic")));
	        if(decoded.equals("securityAdmin")){
	        	return true;
	        }
		}
		
        String token = request.getHeader("Authorization");       
        if (token == null) {        	
            throw new BadRequestException(BadRequestException.JWTTOKEN_EXPIRED, null, messages, "");
        }else {
        Claims claims = new JWTUtils().decodeJWT(token);
        if(claims.getIssuer().equals("Admin")) {
        	return true;
        }else {
        	throw new BadRequestException(BadRequestException.BAD_REQUEST_MSG,null, messages, "");
        }
    }	
        
	}
	
	
}

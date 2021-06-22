package com.org.utils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.interfaces.IMessages;

import io.jsonwebtoken.ExpiredJwtException;

@RunWith(SpringRunner.class)
public class ValidateTokenTest {

	@InjectMocks
	private ValidateToken validateToken;
	
	@Mock
	private IMessages messages;
	
	@Mock
	HttpServletRequest request;
	
	@Test
	public void testForBasicAuth(){
		when(request.getHeader(Mockito.anyString())).thenReturn("c2VjdXJpdHlBZG1pbg==");
		assertTrue(validateToken.judgeToken(request));
	}
	
	@Test(expected=ExpiredJwtException.class)
	public void testForExpiredJWTAuth(){
		when(request.getHeader(Mockito.anyString())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjE0MTA1NTA3LCJzdWIiOiJBdXRob3JpemF0aW9uIiwiaXNzIjoiQWRtaW4iLCJleHAiOjE2MTQxMDg5NjN9.roYiJnzyLIJst7L_NIokHBPAZNmhbcGZcIOdkE4zJZ4");
		validateToken.judgeToken(request);
	}
	
	@Test(expected=BadRequestException.class)
	public void testForNullAuthorizationValue(){
		validateToken.judgeToken(request);
	}
}

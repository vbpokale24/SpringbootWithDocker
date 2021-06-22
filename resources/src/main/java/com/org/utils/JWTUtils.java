package com.org.utils;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.*;

public class JWTUtils {

	public static String SECRET_KEY = "secretKey";

	public  static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	public Claims decodeJWT(String jwt) {
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}	
	
	/*public static void main(String[] args) {
		System.out.println("JWT TOKEN --"+createJWT("1", "Admin", "Authorization", 3456789));
		//System.out.println("Decoded Token "+decodeJWT(createJWT("1", "Admin", "Authorization", 3456789)).getIssuer());
	}*/
}

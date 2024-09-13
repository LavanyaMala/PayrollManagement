package com.payrollapp;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component //This registers JWT utility with spring
public class JwtUtil {

	private String SECRET_KEY = "secret";
	
	//This method is used to extract username from the token that will come from the UI
	//This happens automatically with spring security http basic
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
	 
	//This method will set the expire date of the token - 3 days (3*24*60*60 seconds)
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	//This method resolves the claim placed by the UI developer while giving the token
	//This claim must also be registered with overall Claims class
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		//Extract the claims basis given token 
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	//This method first encodes the secret key using base 64 encoder and parses it to create a stronger token
	//such methods are sometimes called as helper method
	private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())).parseClaimsJws(token).getBody();
    }
	private String createToken(Map<String, Object> claims, String subject) {
		// Builder design pattern which we are using to built JWTs object
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*3*24*60*60*1000)) //3 represents the day
                 .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .compact();
    }
	
	//This will generate token basis on username
	public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
	
	//Checks whether the token is valid or expired 
	private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	//This is to validate the token when it comes from the UI
	public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

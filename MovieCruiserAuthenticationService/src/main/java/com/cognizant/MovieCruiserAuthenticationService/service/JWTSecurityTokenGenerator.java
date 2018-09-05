package com.cognizant.MovieCruiserAuthenticationService.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cognizant.MovieCruiserAuthenticationService.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTSecurityTokenGenerator implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserName()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", jwtToken);
		tokenMap.put("message", "Login successful");
		return tokenMap;
	}

}

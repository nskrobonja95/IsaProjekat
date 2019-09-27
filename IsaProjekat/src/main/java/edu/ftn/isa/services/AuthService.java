package edu.ftn.isa.services;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public String extractAuthUsernameFromRequest(HttpServletRequest request) {
		String auth = request.getHeader("Authorization");
		String encodedBasic = auth.substring("Basic ".length());
		String credentials = new String(Base64.getDecoder().decode(encodedBasic));
		String username = credentials.split(":")[0];
		return username;
	}
	
}

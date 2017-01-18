package com.javafirst.ws.services.security.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * dummy implementation of the user service
 * @author lfallon
 *
 */
@Service
public class UserStubService implements UserService{
	final Map<String, String> data = new HashMap<>();
	
	public UserStubService() { 
		data.put("lfallo1", "snoopy");
		data.put("johndoe", "johndoe123");
	}
	
	@Override
	public String findPasswordByUsername(String username){
		return data.get(username);
	}
}

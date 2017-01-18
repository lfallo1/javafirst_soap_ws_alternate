package com.javafirst.ws.services.security.repository;

import org.springframework.stereotype.Service;

/**
 * service for CRUD ops on user related data
 * @author lfallon
 *
 */
public interface UserService {
	String findPasswordByUsername(String username);
}

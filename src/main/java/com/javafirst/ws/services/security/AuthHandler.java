package com.javafirst.ws.services.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.cxf.interceptor.Fault;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.beans.factory.annotation.Autowired;

import com.javafirst.ws.services.security.repository.UserService;

/**
 * callback handler for the authentication check
 * @author lfallon
 *
 */
public class AuthHandler implements CallbackHandler {

	@Autowired
	private UserService userService;

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			pc.setPassword(userService.findPasswordByUsername(pc.getIdentifier()));
		}
		throw new Fault(new IOException("INVALID CREDENTIALS!!"));
	}

}
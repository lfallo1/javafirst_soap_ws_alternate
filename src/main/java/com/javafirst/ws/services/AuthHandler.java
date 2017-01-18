package com.javafirst.ws.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;


public class AuthHandler implements CallbackHandler {

	final Map<String, String> data = new HashMap<>();
	
	public AuthHandler() { 
		data.put("lfallo1", "snoopy");
		data.put("johndoe", "johndoe123");
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			String password = data.get(pc.getIdentifier());
			if(password != null){
				pc.setPassword(password);
				return;
			}
		}
	}

}
package com.javafirst.ws.services.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class CustomInterceptor extends AbstractSoapInterceptor {
 
    public CustomInterceptor() {
        super(Phase.UNMARSHAL);
    }
 
    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        System.out.println(soapMessage.toString());
    }
}

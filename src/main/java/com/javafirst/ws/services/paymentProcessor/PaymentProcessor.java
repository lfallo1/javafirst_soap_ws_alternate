package com.javafirst.ws.services.paymentProcessor;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.javafirst.ws.services.paymentProcessor.dto.PaymentProcessorRequest;
import com.javafirst.ws.services.paymentProcessor.dto.PaymentProcessorResponse;

@WebService(targetNamespace = "http://customerorders.services.lance.com/", name = "PaymentProcessor")
@HandlerChain(file="../interceptors/handlers.xml")
public interface PaymentProcessor {

	public @WebResult(name="response") PaymentProcessorResponse processPayment
		(@WebParam(name="paymentProcessorRequest") PaymentProcessorRequest paymentProcessorRequest);
}

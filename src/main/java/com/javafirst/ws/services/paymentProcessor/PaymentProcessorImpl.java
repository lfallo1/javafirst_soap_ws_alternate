package com.javafirst.ws.services.paymentProcessor;

import com.javafirst.ws.services.paymentProcessor.dto.PaymentProcessorRequest;
import com.javafirst.ws.services.paymentProcessor.dto.PaymentProcessorResponse;


public class PaymentProcessorImpl implements PaymentProcessor {

	public PaymentProcessorResponse processPayment(
			PaymentProcessorRequest paymentProcessorRequest) {
		PaymentProcessorResponse paymentProcessorResponse = new PaymentProcessorResponse();
		//Business Logic or a call  to a Business Logic Class Goes Here.
		paymentProcessorResponse.setMessage("You done paid something....");
		paymentProcessorResponse.setResult(true);
		return paymentProcessorResponse;
	}

}

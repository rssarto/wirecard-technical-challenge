package com.wirecardtechincalchallenge.payment;

import com.wirecardtechincalchallenge.exception.NotFoundException;

@SuppressWarnings("serial")
public class PaymentNotFoundException extends NotFoundException {
	
	public PaymentNotFoundException(String message) {
		super(message);
	}

}

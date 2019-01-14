package com.wirecardtechincalchallenge.payment.exception;

import com.wirecardtechincalchallenge.exception.BadRequestException;

@SuppressWarnings("serial")
public class CardIssuerException extends BadRequestException {
	
	public CardIssuerException(String message) {
		super(message);
	}

}

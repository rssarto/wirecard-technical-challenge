package com.wirecardtechincalchallenge.payment.exception;

import com.wirecardtechincalchallenge.exception.BadRequestException;

@SuppressWarnings("serial")
public class CardProcessingException extends BadRequestException {
	
	public CardProcessingException(String message){
		super(message);
	}

}

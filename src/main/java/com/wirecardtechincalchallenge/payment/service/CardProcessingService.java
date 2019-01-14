package com.wirecardtechincalchallenge.payment.service;

import com.wirecardtechincalchallenge.payment.domain.Payment;

public interface CardProcessingService {
	
	boolean process(Payment payment);

}

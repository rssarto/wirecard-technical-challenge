package com.wirecardtechincalchallenge.payment.service;

import com.wirecardtechincalchallenge.payment.domain.Payment;

public interface CardService {
	
	void defineCardIssuer(Payment payment);

}

package com.wirecardtechincalchallenge.payment.service;

import com.wirecardtechincalchallenge.payment.domain.Payment;

public class HipercardCardProcessingServiceImpl implements CardProcessingService {

	@Override
	public boolean process(Payment payment) {
		return true;
	}

}

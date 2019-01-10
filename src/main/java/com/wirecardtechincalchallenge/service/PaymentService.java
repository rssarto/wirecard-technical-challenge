package com.wirecardtechincalchallenge.service;

import com.wirecardtechincalchallenge.payment.domain.Payment;

public interface PaymentService {
	
	Payment createPayment(Payment payment);
	Payment findById(Long id);

}

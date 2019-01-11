package com.wirecardtechincalchallenge.payment.service;

import org.springframework.stereotype.Service;

import com.wirecardtechincalchallenge.payment.domain.Payment;
import com.wirecardtechincalchallenge.payment.enums.CardIssuer;

@Service
public class CardServiceImpl implements CardService {

	@Override
	public void defineCardIssuer(Payment payment) {
		payment.getCard().setCardIssuer(CardIssuer.findIssuer(payment.getCard().getNumber()));
	}

}

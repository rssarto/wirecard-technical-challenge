package com.wirecardtechincalchallenge.payment.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecardtechincalchallenge.payment.domain.Payment;
import com.wirecardtechincalchallenge.payment.enums.PaymentStatus;
import com.wirecardtechincalchallenge.payment.exception.CardIssuerException;
import com.wirecardtechincalchallenge.payment.exception.CardProcessingException;
import com.wirecardtechincalchallenge.payment.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

	@Override
	public Payment createPayment(Payment payment) {
		logger.info("Creating payment...");
		logger.info(payment.toString());
		payment.setStatus(PaymentStatus.IN_ANALYSIS);
		switch (payment.getPaymentType()) {
			case BOLETO:
				payment.setBoleto(this.boletoService.createBoleto());
				break;
			default:
				cardService.defineCardIssuer(payment);
				CardProcessingService cardProcessingService = payment.getCard().getCardIssuer().cardProcessing();
				if( cardProcessingService != null ) {
					if( !cardProcessingService.process(payment) ) {
						throw new CardProcessingException("The card issuer rejected the card.");
					}
				}else {
					throw new CardIssuerException("The card issuer wasn't identified.");
				}
				break;
		}
		this.paymentRepository.save(payment);
		logger.info("Payment created...");
		logger.info(payment.toString());
		return payment;
	}

	@Override
	public Payment findById(Long id) {
		Optional<Payment> optionalPayment = paymentRepository.findById(id);
		return optionalPayment.orElse(null);
	}
	
}

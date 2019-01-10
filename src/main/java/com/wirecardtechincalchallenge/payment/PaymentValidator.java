package com.wirecardtechincalchallenge.payment;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wirecardtechincalchallenge.payment.domain.Client;
import com.wirecardtechincalchallenge.payment.domain.Payment;
import com.wirecardtechincalchallenge.payment.enums.PaymentType;
import com.wirecardtechincalchallenge.payment.repositories.ClientRepository;

@Component
public class PaymentValidator implements Validator {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Payment.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Payment payment = (Payment) obj;
		if( !Objects.isNull(payment.getPaymentType()) ) {
			if( payment.getPaymentType() == PaymentType.CARD ) {
				if( Objects.isNull(payment.getCard()) ) {
					errors.rejectValue("card", "null", null, "Card cannot be null");
				}
			}
		}
		
		if( Objects.nonNull(payment.getClient()) ) {
			if( Objects.nonNull(payment.getClient().getId()) ) {
				Optional<Client> optionalClient = this.clientRepository.findById(payment.getClient().getId());
				if( !optionalClient.isPresent() ) {
					errors.rejectValue("Client.id", "null", null, "Client not found with id: " + payment.getClient().getId());
				}
			}else {
				errors.rejectValue("client.id", "null", null, "Client's id cannot be null");
			}
		}else {
			errors.rejectValue("client", "null", null, "Client cannot be null");
		}
	}

}

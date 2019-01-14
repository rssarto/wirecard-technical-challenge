package com.wirecardtechincalchallenge.payment.enums;

import com.wirecardtechincalchallenge.payment.service.AmexCardProcessingServiceImpl;
import com.wirecardtechincalchallenge.payment.service.CardProcessingService;
import com.wirecardtechincalchallenge.payment.service.HipercardCardProcessingServiceImpl;
import com.wirecardtechincalchallenge.payment.service.MastercardCardProcessingServiceImpl;
import com.wirecardtechincalchallenge.payment.service.VisaCardProcessingServiceImpl;

public enum CardIssuer {
    MASTERCARD {
		@Override
		public CardProcessingService cardProcessing() {
			return new MastercardCardProcessingServiceImpl();
		}
    },
    VISA {
		@Override
		public CardProcessingService cardProcessing() {
			return new VisaCardProcessingServiceImpl();
		}
    },
    AMEX {
		@Override
		public CardProcessingService cardProcessing() {
			return new AmexCardProcessingServiceImpl();
		}
    },
    HIPERCARD {
		@Override
		public CardProcessingService cardProcessing() {
			return new HipercardCardProcessingServiceImpl();
		}
    	
    },
    UNKNOWN {
		@Override
		public CardProcessingService cardProcessing() {
			return null;
		}
    };

	public static CardIssuer findIssuer(String cardNumber) {

	    if (cardNumber.startsWith("5") && (cardNumber.length() == 13 || cardNumber.length() == 16)) {
	        return MASTERCARD;
	    } else if (cardNumber.startsWith("4") && cardNumber.length() == 16){
	        return VISA;
	    } else if ((cardNumber.startsWith("34") || cardNumber.startsWith("37")) && cardNumber.length() == 15){
	        return AMEX;
	    } else if ((cardNumber.startsWith("38") || cardNumber.startsWith("60")) && (cardNumber.length() == 13 || cardNumber.length() == 16 || cardNumber.length() == 19)){
	        return HIPERCARD;
	    }
	
	    return UNKNOWN;
	}
	
	public abstract CardProcessingService cardProcessing();
}

package com.wirecardtechincalchallenge.payment;

public enum CardIssuer {
    MASTERCARD,
    VISA,
    AMEX,
    HIPERCARD,
    UNKNOWN;

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
}

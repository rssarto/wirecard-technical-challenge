package com.wirecardtechincalchallenge.payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="CARD")
public class Card {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message="Credit card holder name cannot be null or empty.")
	@Column(nullable=false)
	private String holderName;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@NotBlank(message="Credit card number cannot be null.")
	@CreditCardNumber(message="Invalid credit card number")
	@Column(nullable=false)
	private String number;
	
	@NotNull(message="Credit card's expiration month cannot be null.")
	@Range(min=1, max=12, message="Invalid credit card expiration month. Must be in the rage 1 to 12.")
	@Column(nullable=false)
	private Integer expirationMonth;
	
	@NotNull(message="Credit card expiration year cannot be null.")
	//@Length(min=2, max=4, message="Invalid credit card expiration year")
	@Column(nullable=false)
	private Integer expirationYear;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@NotNull(message="Credit card cvv code cannot be null.")
	@Column(nullable=false)
	private Integer cvv;
	
	private CardIssuer cardIssuer;
	
	@JsonIgnore
	@OneToOne(mappedBy="card", fetch=FetchType.LAZY)
	private Payment payment;
	
	public Card() {
	}
	
	public Card(String holderName, String number, Integer expirationMonth, Integer expirationYear, Integer cvv) {
		super();
		this.holderName = holderName;
		this.number = number;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cvv = cvv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
		this.setCardIssuer(CardIssuer.findIssuer(this.number));
	}

	public Integer getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public CardIssuer getCardIssuer() {
		return cardIssuer;
	}

	public void setCardIssuer(CardIssuer cardIssuer) {
		this.cardIssuer = cardIssuer;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", holderName=" + holderName + ", number=" + number + ", expirationMonth="
				+ expirationMonth + ", expirationYear=" + expirationYear + ", cvv=" + cvv + "]";
	}
}

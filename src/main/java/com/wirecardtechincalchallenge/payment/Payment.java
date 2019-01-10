package com.wirecardtechincalchallenge.payment;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PAYMENT")
public class Payment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@Valid
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="boleto_id")
	private Boleto boleto;
	
	@Valid
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="card_id")
	private Card card;
	
	@Valid
	@NotNull(message="Buyer cannot be null")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="buyer_id")
	private Buyer buyer;
	
	@Digits(integer=9, fraction = 2, message="Invalid amount. Must have the maximum of 9 integer digits and 2 fraction digits.")
	@NotNull(message="Amount cannot be null.")
	private BigDecimal amount;
	
	@NotNull(message="Payment type cannot be null")
	private PaymentType paymentType;
	
	private PaymentStatus status;
	
	public Payment() {
	}

	public Payment(Client client, 
				   Boleto boleto, 
			   	   Card card,
			   	   Buyer buyer,
			   	   BigDecimal amount,
			   	   PaymentType paymentType, 
			   	   PaymentStatus status) {
		super();
		this.client = client;
		this.boleto = boleto;
		this.card = card;
		this.buyer = buyer;
		this.amount = amount;
		this.paymentType = paymentType;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus paymentStatus) {
		this.status = paymentStatus;
	}

}

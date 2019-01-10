package com.wirecardtechincalchallenge.payment;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CLIENT")
public class Client {
	
	@NotNull(message="Client's id cannot be null")
	@Id
	private Long id;
	
	@JsonIgnore
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private List<Payment> payments;
	
	public Client() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + "]";
	}

}

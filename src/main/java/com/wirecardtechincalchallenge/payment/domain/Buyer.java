package com.wirecardtechincalchallenge.payment.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="BUYER")
public class Buyer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message="Name cannot be empty or null.")
	@Column(nullable=false)
	private String name;
	
	@Email(message="Invalid e-mail.")
	@Column(nullable=false)
	private String email;
	
	@CPF(message="Invalid CPF.")
	@Column(nullable=false)
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy="buyer", fetch=FetchType.LAZY)
	private List<Payment> payments;
	
	public Buyer(@NotBlank(message = "Name cannot be empty or null.") String name,
			@Email(message = "Invalid e-mail.") String email, @CPF(message = "Invalid CPF.") String cpf) {
		super();
		this.name = name;
		this.email = email;
		this.cpf = cpf;
	}

	public Buyer() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "Buyer [id=" + id + ", name=" + name + ", email=" + email + ", cpf=" + cpf + "]";
	}
}

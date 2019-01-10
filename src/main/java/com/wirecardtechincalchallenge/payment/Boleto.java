package com.wirecardtechincalchallenge.payment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Boleto Model specification data")
@Entity
@Table(name="BOLETO")
public class Boleto {
	
	@Id
	@GeneratedValue
	private long Id;
	
	@ApiModelProperty(notes="Boleto's expiration date in the format YYYY-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	@Column(nullable=false)
	private Date expirationDate;
	
	@JsonIgnore
	@OneToOne(mappedBy="boleto", fetch=FetchType.LAZY)
	private Payment payment;
	
	public Boleto() {
	}
	
	public Boleto(Date expirationDate) {
		super();
		this.expirationDate = expirationDate;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	@Override
	public String toString() {
		return "Boleto [Id=" + Id + ", expirationDate=" + expirationDate + "]";
	}

}

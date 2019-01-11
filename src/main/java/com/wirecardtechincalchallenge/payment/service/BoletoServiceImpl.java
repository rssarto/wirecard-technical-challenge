package com.wirecardtechincalchallenge.payment.service;

import java.time.LocalDate;
import java.sql.Date;

import org.springframework.stereotype.Service;

import com.wirecardtechincalchallenge.payment.domain.Boleto;

@Service
public class BoletoServiceImpl implements BoletoService {

	@Override
	public Boleto createBoleto() {
		Boleto boleto = new Boleto();
		
		boleto.setExpirationDate(Date.valueOf(LocalDate.now().plusDays(5)));
		boleto.setNumber("39997.12981 52182.701483 03006.480010 6 63500000038156");
		
		return boleto;
	}

}

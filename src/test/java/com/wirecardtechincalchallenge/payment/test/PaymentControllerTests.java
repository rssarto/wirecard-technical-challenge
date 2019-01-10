package com.wirecardtechincalchallenge.payment.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wirecardtechincalchallenge.payment.domain.Buyer;
import com.wirecardtechincalchallenge.payment.domain.Card;
import com.wirecardtechincalchallenge.payment.domain.Client;
import com.wirecardtechincalchallenge.payment.domain.Payment;
import com.wirecardtechincalchallenge.payment.enums.PaymentType;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTests {
	
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    
    @Autowired
    private MockMvc mockMvc;    
    

    @Test
    public void shouldNotFindPayment() throws Exception {
    		this.mockMvc.perform(get("/api/v1/payment/0"))
    			.andExpect(status().isNotFound());
    }
    
    @Test
    public void shouldCreatePaymentWithBoleto() throws Exception {
    		Payment payment = createPayment(PaymentType.BOLETO); 
    		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isOk());
    }
    
    @Test
    public void shouldCreatePaymentWithCard() throws Exception {
		Payment payment = createPayment(PaymentType.CARD); 
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isOk());
    	
    }
    
    
    @Test
    public void shouldNotAcceptPaymentWithNoType() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		payment.setPaymentType(null);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptPaymentWitnNoAmount() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		payment.setAmount(null);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptBuyerWithInvalidCpf() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		Buyer buyer = createBuyer();
		buyer.setCpf("71914360771"); // correct is 71914360770
		payment.setBuyer(buyer);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptBuyerWithInvalidEmail() throws Exception  {
		Payment payment = createPayment(PaymentType.BOLETO);
		Buyer buyer = createBuyer();
		buyer.setEmail("buzz@");
		payment.setBuyer(buyer);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptBuyerWithEmptyName() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		Buyer buyer = createBuyer();
		buyer.setName("");
		payment.setBuyer(buyer);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptBuyerWithNullName() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		Buyer buyer = createBuyer();
		buyer.setName(null);
		payment.setBuyer(buyer);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptPaymentWithNoBuyer() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		payment.setBuyer(null);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptClientWithNoId() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		Client client = createClient();
		client.setId(null);
		payment.setClient(client);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptClientNonexistent() throws Exception {
		Payment payment = createPayment(PaymentType.BOLETO);
		Client client = createClient();
		client.setId(0L);
		payment.setClient(client);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptCardWithNullHolderName() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setHolderName(null);
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptCardWithEmptyHolderName() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setHolderName("");
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAcceptCardWithNoNumber() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setNumber(null);
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptInvalidVisaCard() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setNumber("	4012001037141113"); // Correct is 4012001037141112
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptInvalidMastercardCard() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setNumber("	5555666677778886"); // Correct is 5555666677778884
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptInvalidAmexCard() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setNumber("	376449047333003"); // Correct is 376449047333005
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptInvalidDinersCard() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setNumber("	36490102462669"); // Correct is 36490102462661
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAcceptInvalidEloCard() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setNumber("	6362970000457012"); // Correct is 6362970000457012
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptCardWithNoExpirationMonth() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setExpirationMonth(null);
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptCardWithInvalidExpirationMonth() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setExpirationMonth(13);
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptCardWithNoExpirationYear() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setExpirationYear(null);
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldNotAcceptCardWithNoCvvNumber() throws Exception {
		Payment payment = createPayment(PaymentType.CARD);
		Card card = createCard();
		card.setCvv(null);
		payment.setCard(card);
		this.mockMvc.perform(post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(payment))).andExpect(status().isBadRequest());
    }
    
    private static Buyer createBuyer() {
    		return new Buyer("RICARDO", "zuum@teste.com.br", "01689384301");
    }
    
    private static Client createClient() {
		Client client = new Client();
		client.setId(10000L);
		return client;
    }
    
    private static Card createCard() {
    		return new Card("Jacquelyn Davis", "4990346692173058", 1, LocalDate.now().getYear() + 8, 793);
    }
    
    private static Payment createPayment(PaymentType paymentType) {
    		Payment payment = null;
    		switch( paymentType ) {
	    		case BOLETO: {
	        		payment = new Payment(createClient(), null, createBuyer(), BigDecimal.valueOf(1000.58), PaymentType.BOLETO);
	    			break;
	    		}
	    		default: {
	    			payment = new Payment(createClient(), createCard(), createBuyer(), BigDecimal.valueOf(1000.58), PaymentType.CARD);
	    		}
    		}
    		return payment;
    }

}

package com.wirecardtechincalchallenge.payment;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/api")
public class PaymentController {
	
	@Autowired
	@Qualifier("paymentValidator")
	private PaymentValidator paymentValidator;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
    @InitBinder("payment")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(paymentValidator);
    }	
	
	@ApiOperation(value="Creates a Payment")
	@PostMapping("/v1/payment")
	public Resource<Payment> create(@ApiParam(value="Payment definition that is going to be created.") @RequestBody @Valid Payment payment) {
		payment.setStatus(PaymentStatus.IN_ANALYSIS);
		Payment newPayment = this.paymentRepository.save(payment);
		
		Resource<Payment> resource = new Resource<Payment>(newPayment);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass(), newPayment.getId()).getPayment(newPayment.getId()));
		resource.add(linkTo.withRel("self"));
		return resource;
	}
	
	@ApiOperation(value="Retrieves a Payment by id")
	@ResponseStatus(code=HttpStatus.OK)
	@GetMapping("/v1/payment/{id}")
	public Payment getPayment(@ApiParam(value="Payments's id to be retrieved.") @PathVariable Long id) {
		
		Optional<Payment> optionalPayment = this.paymentRepository.findById(id);
		if( optionalPayment.isPresent() ) {
			return optionalPayment.get();
		}
		
		throw new PaymentNotFoundException("No Payment found using id: " + id);
	}

}

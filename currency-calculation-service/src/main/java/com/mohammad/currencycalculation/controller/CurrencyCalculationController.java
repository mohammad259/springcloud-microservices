package com.mohammad.currencycalculation.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mohammad.currencycalculation.facade.CurrencyExchangeProxy;
import com.mohammad.currencycalculation.model.CalculatedAmount;

@RestController
public class CurrencyCalculationController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	// with RestTemplate we can achieve data from one micro-service to another micro-service
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CalculatedAmount calculatedAmount(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
		
		  Map<String, String> uriVariables = new HashMap<>();
		  uriVariables.put("from",from); uriVariables.put("to", to);
		  ResponseEntity<CalculatedAmount> responseEntity = new
		  RestTemplate().getForEntity(
		  "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
		  CalculatedAmount.class, uriVariables);
		  
		  CalculatedAmount calculatedAmount = responseEntity.getBody();
		
		return new CalculatedAmount(calculatedAmount.getId(), calculatedAmount.getFrom(), calculatedAmount.getTo(),
				calculatedAmount.getConversionMultiple(), quantity, quantity.multiply(calculatedAmount.getConversionMultiple()), calculatedAmount.getPort());
		
	}
	
	// with RestTemplate we can achieve data from one micro-service to another micro-service but
	// there will be number of microservices and huge lines of code that is drawback of RestTemplate so that 
	// with the help of Feign client we can achieve easily.
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CalculatedAmount calculatedAmountFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
		
		CalculatedAmount calculatedAmount = proxy.retrieveExchangeValue(from, to);
		return new CalculatedAmount(calculatedAmount.getId(), calculatedAmount.getFrom(), calculatedAmount.getTo(),
				calculatedAmount.getConversionMultiple(), quantity, quantity.multiply(calculatedAmount.getConversionMultiple()), calculatedAmount.getPort());
		
	}

}

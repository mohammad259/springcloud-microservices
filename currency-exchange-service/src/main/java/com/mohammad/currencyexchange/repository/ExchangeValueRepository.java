/**
 * 
 */
package com.mohammad.currencyexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohammad.currencyexchange.model.ExchangeValue;

/**
 * @author headway
 *
 */
@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	public ExchangeValue findByFromAndTo(String from, String to);
	
}

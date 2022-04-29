package com.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository
        extends JpaRepository<CurrencyExchange, Long> {
  // When writing like this, Spring JPA will implement the method into a SQL query string.
  // The query string will query the table by 'from' and 'to', and return the result of
  // 'CurrencyExchange' object.
  // It's more like a magic... God knows how is this even possible.
  // The method name should be strictly 'findByFromAndTo'. We can't even make changes to the
  // capital letters. Otherwise, IT WILL THROW.
  CurrencyExchange findByFromAndTo(String from, String to);
}

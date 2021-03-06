package com.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
  private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
  
  @GetMapping("/sample-api")
  //  @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
  //  A circuit breaker. Doing this will allow Spring to return a body back without calling the
  //  method of the controller.
  //  @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
  //  A rate limiter that limits how many requests are allowed within a period of time
//  @RateLimiter(name = "default-rate-limiter")
  // @Bulkhead: A rate limiter that limits the maximum number concurrent calls
  @Bulkhead(name = "bulkhead-rate-limiter")
  public String sampleApi() {
    
//        logger.info("Sample api call received.");
//        ResponseEntity<String> entity =
//                new RestTemplate().getForEntity("http://localhost:8080/dumb-shit",
//                                                String.class);
//        return entity.getBody();
    
    // a sample response under rate limiters
    return "sample-api under rate limiter";
  }
  
  public String hardcodedResponse(Exception ex) {
    return "fall-back response";
  }
}

package com.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiGatewayConfiguration {
  //  configure the routes
  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    // return a route
    return builder.routes()
                  .route(p1 -> p1.path("/get")
                                 .filters(f -> f.addRequestHeader("MyHeader", "MyURI")
                                                .addRequestParameter("param", "myParam"))
                                 .uri("http://httpbin.org:80"))
                  // Here, the value of 'uri' is the 'Application' name registered under Eureka.
                  // Values of upper/lower cases are ignored.
                  .route(p -> p.path("/currency-exchange/**")
                               .uri("lb://currency-exchange"))
                  .route(p -> p.path("/currency-conversion/**")
                               .uri("lb://currency-conversion"))
                  .route(p -> p.path("/currency-conversion-feign/**")
                               .uri("lb://currency-conversion"))
                  .build();
  }
}

# Spring-Boot-Microservices

This is a project of Spring Boot with Load Balancing.

The project uses spring-cloud-starter-netflix-eureka-client as a load balancer(Known as Spring Cloud Load Balancer).

The load balancer talks to a naming server, requesting for the information of all available ports of the microservice for a specific incoming request at some point in time and dynamically distributes all the incoming requests loads to all available microservice instances.

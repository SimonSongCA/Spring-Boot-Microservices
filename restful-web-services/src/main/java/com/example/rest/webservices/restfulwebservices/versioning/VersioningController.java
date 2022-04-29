package com.example.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VersioningController {
  // URI Versioning: add different routes for different URIs to differentiate resources
  @GetMapping("/v1/person")
  public PersonV1 personV1() {
    return new PersonV1("First Last");
  }
  
  @GetMapping("/v2/person")
  public PersonV2 personV2() {
    return new PersonV2(new Name("Bob", "Charlie v2"));
  }
  
  // Request Parameter Versioning: use 'value' & 'params' at '@GetMapping' annotation
  // use: localhost:8080/person/param?version=1
  @GetMapping(value = "/person/param", params = "version=1")
  public PersonV1 paramV1() {
    return new PersonV1("Bob Charlie v1");
  }
  
  // use: localhost:8080/person/param?version=2
  @GetMapping(value = "/person/param", params = "version=2")
  public PersonV2 paramV2() {
    return new PersonV2(new Name("Bob", "Charlie v2"));
  }
  
  // Media Type/MIME Type Versioning (Content Negotiation): use header
  // use: localhost:8080/person/header with a header content of 'X-API-VERSION' equals 1
  @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
  public PersonV1 headerV1() {
    return new PersonV1("Bob Charlie v1");
  }
  
  // use: localhost:8080/person/header with a header content of 'X-API-VERSION' equals 2
  @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
  public PersonV2 headerV2() {
    return new PersonV2(new Name("Bob", "Charlie v2"));
  }
  
  // Accept Header Versioning: use 'produces'
  // use: localhost:8080/person/produces with a header content of 'Accept' equals
  // 'application/vnd.company.app-v1+json'
  @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
  public PersonV1 producesV1() {
    return new PersonV1("Bob Charlie v1");
  }
  
  // use: localhost:8080/person/produces with a header content of 'Accept' equals
  // 'application/vnd.company.app-v2+json'
  @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
  public PersonV2 producesV2() {
    return new PersonV2(new Name("Bob", "Charlie v2"));
  }
  
  /*
      Takeaways:
          1.  Two main types of versioning: URI versioning + header versioning
          2.  URI versioning:
              a. pros:  may utilize caching for a better performance
              b. cons:  may create URI pollution
          3.  header versioning:
              a. pros:  does not introduce URI pollution
              b. cons:  caching cannot be within the picture for a better performance
   */
}

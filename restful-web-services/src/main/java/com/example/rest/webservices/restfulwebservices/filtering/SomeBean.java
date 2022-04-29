package com.example.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Static filtering
// Use '@JsonIgnoreProperties' to exclude specific fields from being exposed as a bean return
// field content
//@JsonIgnoreProperties(value = {"field1", "field2"})
// Dynamic filtering
@JsonFilter("simple-filter")
public class SomeBean {
  private String field1;
  private String field2;
  //  Static filtering
  //  Use '@JsonIgnore' to exclude a specific field from being exposed as a bean return field
  //  content
  //  @JsonIgnore
  private String field3;
  
  public SomeBean(String field1, String field2, String field3) {
    this.field1 = field1;
    this.field2 = field2;
    this.field3 = field3;
  }
  
  public String getField1() {
    return field1;
  }
  
  public void setField1(String field1) {
    this.field1 = field1;
  }
  
  public String getField2() {
    return field2;
  }
  
  public void setField2(String field2) {
    this.field2 = field2;
  }
  
  public String getField3() {
    return field3;
  }
  
  public void setField3(String field3) {
    this.field3 = field3;
  }
}

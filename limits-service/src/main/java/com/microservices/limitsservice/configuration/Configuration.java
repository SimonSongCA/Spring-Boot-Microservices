package com.microservices.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// @Component is an annotation that allows Spring to automatically detect our custom beans.
@Component
@ConfigurationProperties("limits-service")
// The configuration in 'application.properties' will map the values like
// 'limits-service.maximum' to this file.
// In this case, the Configuration.maximum will be set to the value stored at application.properties
public class Configuration {
  private int maximum;
  private int minimum;
  
  public int getMaximum() {
    return maximum;
  }
  
  public void setMaximum(int maximum) {
    this.maximum = maximum;
  }
  
  public int getMinimum() {
    return minimum;
  }
  
  public void setMinimum(int minimum) {
    this.minimum = minimum;
  }
}

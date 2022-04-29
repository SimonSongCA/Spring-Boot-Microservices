package com.example.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


// Controller
@RestController
public class HelloWorldController {
  @Autowired
  private MessageSource messageSource;
  
  // GET
  // URI /hello-world
  // method - 'helloWorld'
  // create a mapping for the method
  //    @RequestMapping(method = RequestMethod.GET, path = "hello-world")
  @GetMapping(path = "hello-world")
  public String helloWorld() {
    return "hello world";
  }
  
  @GetMapping(path = "hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World");
  }
  
  @GetMapping(path = "hello-world-bean/path-variable/{name}")
  public HelloWorldBean helloWorldPath(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello world, %s", name));
  }
  
  // i18n
  @GetMapping(path = "hello-world-internationalized")
  public String helloWorldInternationalized() {
    // configure the values for different locales
    // create properties files and return
    // create 'messages.properties' at 'src/main/resources'
    
    // Use 'LocaleContextHolder.getLocale()' to get rid of adding a 'Locale' parameter within the
    // method below.
    // This will also get rid of the '@RequestHeader' of the current method
    return messageSource.getMessage("good.morning.message",
                                    null,
                                    "default message",
                                    LocaleContextHolder.getLocale()
                                   );
    // Below are the two parameters that need to pass in if we don't use
    // 'LocaleContextHolder.getLocale()'：
    
    //    @RequestHeader(name = "Accept-Language", required = false) Locale locale
  }
  
}

package com.example.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
  @GetMapping("/filtering")
  public SomeBean retrieveSomeBean() {
    return new SomeBean("1", "2", "3");
  }
  
  @GetMapping("/filtering-list")
  public List<SomeBean> retrieveListOfSomeBean() {
    return Arrays.asList(new SomeBean("1", "2", "3"), new SomeBean("2", "2", "3"));
  }
  
  @GetMapping("/filtering-dynamic")
  public MappingJacksonValue retrieveBeansFilteredDynamically() {
    // Notice: the configuration below is not sufficient. We still need to add a '@JsonFilter()'
    // annotation with the exact same name of the filter of the '.addFilter()' method of the
    // FilterProvider
    SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
    // create a mapping for 'someBean'
    MappingJacksonValue mapping = new MappingJacksonValue(someBean);
    // create a filter that specifies which fields need to be excluded from the filter
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1",
                                                                                  "field2");
    // add 'filter' to 'FilterProviders'-'filters'
    FilterProvider filters = new SimpleFilterProvider().addFilter("simple-filter", filter);
    // set the filter provider of 'mapping' using 'filters'
    mapping.setFilters(filters);
    // return the mapping
    return mapping;
  }
}

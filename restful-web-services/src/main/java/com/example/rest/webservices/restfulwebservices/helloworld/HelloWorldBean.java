package com.example.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
    private String message;
    private String test = "test";

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // This is what actually returns as the 'bean'
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}

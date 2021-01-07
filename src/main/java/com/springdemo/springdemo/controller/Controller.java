package com.springdemo.springdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/")
    public String printHelloWorld(){
        return "Hello World!";
    }

    @PostMapping("/")
    public String getFromURL(@RequestBody String content){
        System.out.println("From URL : "+ content);
        return "Sucess";
    }
}

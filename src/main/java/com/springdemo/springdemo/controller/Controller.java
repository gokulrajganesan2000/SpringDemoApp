package com.springdemo.springdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
//@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
//    @ResponseBody
    public String printHelloWorld(){
        return "Hello World!";
    }

    @PostMapping("/")
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    @ResponseBody
    public String getFromURL(@RequestBody String content){
        System.out.println("From URL : "+ content);
        return "Sucess!!";
    }
}

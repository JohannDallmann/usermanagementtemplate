package de.jd.usermanagementtemplate.helloworld.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("helloworld")
@CrossOrigin(origins = "http://localhost:5173")
public class HelloWorldController {

    @GetMapping("/user")
    public String sayHelloUser(){
        return "Hello User!";
    }

    @GetMapping("/admin")
    public String sayHelloAdmin(){
        return "Hello Admin!";
    }
}

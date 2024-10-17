package de.jd.usermanagementtemplate.helloworld.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("helloworld")
@CrossOrigin(origins = "http://localhost:5173")
public class HelloWorldController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('client-user')")
    public String sayHelloUser(){
        return "Hello User!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('client-admin')")
    public String sayHelloAdmin(){
        return "Hello Admin!";
    }
}

package com.am.profile.platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/label")
public class HelloController {

    @GetMapping(value = "/hello")
    public ResponseEntity<?> hello(){
        System.out.println("hello");
        return null;
    }
}

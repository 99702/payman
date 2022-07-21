package com.payman.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("{id}")
    public String testing(@PathVariable(value = "id", required = false) String id){
        if(id == null){
            id = "please provide test";
        }
        return id;
    }
}

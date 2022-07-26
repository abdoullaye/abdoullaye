package com.kebe94.springsecurityjpajwt.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping("/api")
    public String getClient(){
        return "Bienvenue api spring security jpa jwt";
    }
}

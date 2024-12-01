package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    public ResponseEntity login(){
        return null;
    }
}

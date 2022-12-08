package com.ecommerce.controllers;

import com.ecommerce.logic.AuthenticationLogic;
import com.ecommerce.models.User;
import com.ecommerce.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    final private AuthenticationLogic authenticationLogic;

    public AuthenticationController(AuthenticationLogic authenticationLogic) {
        this.authenticationLogic = authenticationLogic;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {
        AuthResponse authResponse = authenticationLogic.login(user);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }
}

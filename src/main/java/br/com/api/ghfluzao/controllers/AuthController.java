package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.auth.AuthenticateRequest;
import br.com.api.ghfluzao.interfaces.AuthServiceInterface;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-v3/auth")
public class AuthController {

    @Autowired
    private AuthServiceInterface _authService;
    
    @PostMapping("")
    public ResponseEntity<?> Authenticate(@RequestBody AuthenticateRequest request){
        return _authService.authenticate(request);
    }

}

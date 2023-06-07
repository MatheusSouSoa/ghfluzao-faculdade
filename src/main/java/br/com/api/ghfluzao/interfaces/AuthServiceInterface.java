package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.Auth.AuthenticateRequest;

public interface AuthServiceInterface {
    
    ResponseEntity<?> authenticate(AuthenticateRequest request);

}

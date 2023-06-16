package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.auth.AuthenticateRequest;
import br.com.api.ghfluzao.interfaces.AuthServiceInterface;
import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuariosTokensServiceInterface;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-v3/auth/login")
public class AuthController {

    @Autowired
    private AuthServiceInterface _authService;

    @Autowired
    private UsuariosTokensServiceInterface _tokensService;

    @Autowired
    private JwtServiceInterface _jwtService;
    
    @PostMapping("")
    public ResponseEntity<?> Authenticate(@RequestBody AuthenticateRequest request){
        return _authService.authenticate(request);
    }

    @PutMapping("logout")
    public ResponseEntity<?> Logout(@RequestParam ("logoutToken") String token){
        return ResponseEntity.ok().body(_tokensService.setarTokenFalse(Long.parseLong(_jwtService.getUserId()), _jwtService.getToken()));
    }
}

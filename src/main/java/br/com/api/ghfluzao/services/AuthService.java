package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.auth.AuthenticateRequest;
import br.com.api.ghfluzao.data.dto.auth.AuthenticateResponse;
import br.com.api.ghfluzao.interfaces.AuthServiceInterface;
import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@Service
public class AuthService implements AuthServiceInterface {
 
    @Autowired
    private UsuarioServiceInterface _usuarioService;

    @Autowired
    private JwtServiceInterface _jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public ResponseEntity<?> authenticate(AuthenticateRequest request){

        Usuario usuario = _usuarioService.pegarUsuarioPorEmail(request.email);
        
        var response = new AuthenticateResponse();

        if (usuario == null) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario invalido \n" + response);
        }

        if(!_passwordEncoder.matches(request.password, usuario.getSenha())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta." + response);
        }

        var token = _jwtService.generateToken(usuario.getId());

        response.setCodigo(usuario.getId());
        response.setToken(token);
        response.setRole(usuario.getRole());

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

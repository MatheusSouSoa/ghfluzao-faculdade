package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.Auth.AuthenticateRequest;
import br.com.api.ghfluzao.data.dto.Auth.AuthenticateResponse;
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

    public ResponseEntity<?> authenticate(AuthenticateRequest request){

        Usuario usuario = _usuarioService.pegarUsuarioPorEmail(request.email);
        
        var response = new AuthenticateResponse();

        if (usuario == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario invalido.");
        }

        if(!usuario.getSenha().equals(request.password)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha incorreta.");
        }

        var token = _jwtService.generateToken(usuario.getId());

        response.setCodigo(usuario.getId());
        response.setToken(token);

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
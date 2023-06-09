package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@RestController
@RequestMapping("/api-v3/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceInterface usuarioServiceInterface;

    @Autowired
    private JwtServiceInterface _jwtService;

    @PostMapping("")
    private ResponseEntity<?> criarUsuario(@RequestBody CreateUsuarioRequest request){
        if(_jwtService.isValidToken(_jwtService.getToken().substring(7), _jwtService.getUserId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario não autenticado");
        }
        
        return usuarioServiceInterface.criarUsuario(request);
    }

    @GetMapping("/buscar")
    private Usuario pegarUsuariosPorEmail(@RequestParam("email") String email){
        return usuarioServiceInterface.pegarUsuarioPorEmail(email);
    }



}

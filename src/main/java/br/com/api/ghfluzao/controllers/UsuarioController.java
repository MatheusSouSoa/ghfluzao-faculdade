package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@RestController
@RequestMapping("/api-v3/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceInterface usuarioServiceInterface;

    @PostMapping("")
    private ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        return usuarioServiceInterface.criarUsuario(usuario);
    }

    @GetMapping("/buscar")
    private ResponseEntity<FindUsuarioResponse> pegarUsuariosPorEmail(@RequestParam("email") String email){
        return usuarioServiceInterface.pegarUsuarioPorEmail(email);
    }

}

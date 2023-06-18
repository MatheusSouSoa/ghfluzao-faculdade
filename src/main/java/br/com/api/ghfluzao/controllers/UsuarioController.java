package br.com.api.ghfluzao.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.data.dto.usuario.LoginUsuarioRequest;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@RestController
@RequestMapping("/api-v2/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceInterface _usuarioInterface;
    
    @GetMapping("/listarTodos")
    public ResponseEntity<Iterable<Usuario>> ListaTodos(){
        return _usuarioInterface.listarTodosUsuarios();
    }

    @PostMapping("/criarUsuario")
    public ResponseEntity<FindUsuarioResponse> criarUsuario(@RequestBody CreateUsuarioRequest request){
        return _usuarioInterface.salvarUsuario(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuarioRequest request){
        return _usuarioInterface.login(request);
    }
}

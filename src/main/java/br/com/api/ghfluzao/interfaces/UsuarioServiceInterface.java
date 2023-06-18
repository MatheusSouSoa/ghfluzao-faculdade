package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.data.dto.usuario.LoginUsuarioRequest;
import br.com.api.ghfluzao.models.Usuario;

public interface UsuarioServiceInterface {
    
    ResponseEntity<Iterable<Usuario>> listarTodosUsuarios();
    ResponseEntity<FindUsuarioResponse> salvarUsuario(CreateUsuarioRequest request);
    ResponseEntity<FindUsuarioResponse> login(LoginUsuarioRequest request);

}

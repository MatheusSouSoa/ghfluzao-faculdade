package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.models.Usuario;

public interface UsuarioServiceInterface {

    ResponseEntity<?> criarUsuario(Usuario usuario);
    ResponseEntity<FindUsuarioResponse> pegarUsuarioPorEmail(String email);
}

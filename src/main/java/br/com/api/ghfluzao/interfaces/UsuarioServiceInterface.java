package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.models.Usuario;

public interface UsuarioServiceInterface {

    ResponseEntity<?> criarUsuario(CreateUsuarioRequest request);
    Usuario pegarUsuarioPorEmail(String email);
}

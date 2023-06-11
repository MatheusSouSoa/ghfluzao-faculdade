package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.models.Usuario;

public interface UsuarioServiceInterface {

    ResponseEntity<?> criarUsuario(CreateUsuarioRequest request);
    Usuario pegarUsuarioPorEmail(String email);
    Usuario pegarUsuarioPorId(Long codigo);
    ResponseEntity<?> definirRoleAdmin(String email);
    ResponseEntity<?> definirRoleFuncInep(String email);
    ResponseEntity<?> definirRoleProfessor(String email, Long codigo);
    ResponseEntity<?> definirRoleUser(String email);
    FindUsuarioResponse buscarUsuario(String email);
}

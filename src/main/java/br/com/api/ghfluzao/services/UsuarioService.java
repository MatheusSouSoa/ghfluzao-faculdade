package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.data.repositories.UsuarioRepository;
import br.com.api.ghfluzao.enums.RolesUsuarios;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@Service
public class UsuarioService implements UsuarioServiceInterface{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> criarUsuario(CreateUsuarioRequest request){
        var usuario = new Usuario(request.nome, request.email, request.senha);
        
        if(usuario.getEmail().equals(null) || usuario.getNome().equals(null) || usuario.getSenha().equals(null)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //usuario.setRole(RolesUsuarios.USER);

        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");

    }

    public ResponseEntity<?> definirRoleAdmin(String email){
        
        var usuario = pegarUsuarioPorEmail(email);
        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        usuario.setRole(RolesUsuarios.ADMIN);
        
        return new ResponseEntity<>(usuarioRepository.save(usuario) ,HttpStatus.OK);
    }
    
    public ResponseEntity<?> definirRoleFuncInep(String email){
        
        var usuario = pegarUsuarioPorEmail(email);
        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        usuario.setRole(RolesUsuarios.FUNC_INEP);
        
        return new ResponseEntity<>(usuarioRepository.save(usuario) ,HttpStatus.OK);
    }

    public ResponseEntity<?> definirRoleProfessor(String email){
        
        var usuario = pegarUsuarioPorEmail(email);
        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        usuario.setRole(RolesUsuarios.PROFESSOR);
        
        return new ResponseEntity<>(usuarioRepository.save(usuario) ,HttpStatus.OK);
    }

    public ResponseEntity<?> definirRoleUser(String email){
        
        var usuario = pegarUsuarioPorEmail(email);
        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        usuario.setRole(RolesUsuarios.USER);
        
        return new ResponseEntity<>(usuarioRepository.save(usuario) ,HttpStatus.OK);
    }

    public Usuario pegarUsuarioPorEmail(String email){
        var usuario = usuarioRepository.findByEmail(email).get(0);
        
        return usuario;
    }
    public Usuario pegarUsuarioPorId(Long codigo){
        var usuario = usuarioRepository.findById(codigo).get();
        
        return usuario;
    }
    
}

package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.data.repositories.UsuarioRepository;
import br.com.api.ghfluzao.enums.RolesUsuarios;
import br.com.api.ghfluzao.interfaces.AssuntoServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@Service
public class UsuarioService implements UsuarioServiceInterface{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private AssuntoServiceInterface _assuntoSerivce;

    public ResponseEntity<?> criarUsuario(CreateUsuarioRequest request){
        var usuario = new Usuario(request.nome, request.email);
        
        if(request.getEmail().equals(null) || request.getNome().equals(null) || request.getSenha().equals(null)){
            return new ResponseEntity<>("Todos os campos são obrigatorios.",HttpStatus.BAD_REQUEST);
        }

        if(pegarUsuarioPorEmail(request.email) != null){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Usuario ja cadastrado");
        }

        usuario.setSenha(_passwordEncoder.encode(request.senha));

        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");

    }

    public ResponseEntity<?> definirRoleAdmin(String email){
        
        var usuario = pegarUsuarioPorEmail(email);
        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        usuario.setRole(RolesUsuarios.ADMIN);
        
        usuarioRepository.save(usuario);
        var response = new FindUsuarioResponse(usuario.getId(), usuario.getNome(),usuario.getEmail(), usuario.getRole());
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    public ResponseEntity<?> definirRoleFuncInep(String email){
        
        var usuario = pegarUsuarioPorEmail(email);
        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        
        usuario.setRole(RolesUsuarios.FUNC_INEP);
        usuarioRepository.save(usuario);
        var response = new FindUsuarioResponse(usuario.getId(), usuario.getNome(),usuario.getEmail(), usuario.getRole());
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<?> definirRoleProfessor(String email, Long codigo){
        
        var usuario = pegarUsuarioPorEmail(email);
        var assunto = _assuntoSerivce.validarAssunto(codigo);

        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        if(assunto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia invalida.");
        usuario.setRole(RolesUsuarios.PROFESSOR);

        usuarioRepository.save(usuario);
        var response = new FindUsuarioResponse(usuario.getId(), usuario.getNome(),usuario.getEmail(), usuario.getRole());
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<?> definirRoleUser(String email){
        
        var usuario = pegarUsuarioPorEmail(email);
        if(usuario.getEmail().equals(null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        
        usuario.setRole(RolesUsuarios.USER);
        usuarioRepository.save(usuario);
        var response = new FindUsuarioResponse(usuario.getId(), usuario.getNome(),usuario.getEmail(), usuario.getRole());
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public Usuario pegarUsuarioPorEmail(String email){
        try{
            var usuario = usuarioRepository.findByEmail(email).get(0);
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    public FindUsuarioResponse buscarUsuario(String email){
        var usuario = pegarUsuarioPorEmail(email);

        if(usuario == null){
            return null;
        }

        var response = new FindUsuarioResponse(usuario.getId(), usuario.getNome(),usuario.getEmail(), usuario.getRole());

        return response;
    }

    public Usuario pegarUsuarioPorId(Long codigo){
        var usuario = usuarioRepository.findById(codigo).get();
        
        if(usuario == null){
            return null;
        }

        return usuario;
    }
    
}

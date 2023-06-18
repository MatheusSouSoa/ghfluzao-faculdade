package br.com.api.ghfluzao.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.data.dto.usuario.LoginUsuarioRequest;
import br.com.api.ghfluzao.data.repositories.UsuarioRepository;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@Service
public class UsuariosService implements UsuarioServiceInterface{
    
    @Autowired
    private UsuarioRepository _usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<Iterable<Usuario>> listarTodosUsuarios(){
        return ResponseEntity.ok().body(_usuarioRepository.findAll());
    }

    public ResponseEntity<Optional<Usuario>> suscarUsuarioPorId(Long id){
        return ResponseEntity.ok().body(_usuarioRepository.findById(id));
    }

    public ResponseEntity<FindUsuarioResponse> salvarUsuario(CreateUsuarioRequest request){
        var usuario = new Usuario(request.email, request.nome);
        usuario.setSenha(encoder.encode(request.senha));
        _usuarioRepository.save(usuario);
        var usuarioResponse = new FindUsuarioResponse(usuario.getId(),usuario.getEmail(),usuario.getNome());
        return ResponseEntity.ok().body(usuarioResponse);
    }

    public ResponseEntity<FindUsuarioResponse> login(LoginUsuarioRequest request){
        Optional<Usuario> optUsuario = _usuarioRepository.findByEmail(request.email);

        if(optUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        
        return (encoder.matches(request.senha, optUsuario.get().getSenha())) ?
        ResponseEntity.ok().body(new FindUsuarioResponse(optUsuario.get().getId(),optUsuario.get().getEmail(), optUsuario.get().getNome())) :
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new FindUsuarioResponse());
    }
}

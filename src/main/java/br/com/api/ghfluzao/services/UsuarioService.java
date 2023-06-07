package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.usuario.CreateUsuarioRequest;
import br.com.api.ghfluzao.data.repositories.UsuarioRepository;
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
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public Usuario pegarUsuarioPorEmail(String email){
        var usuario = usuarioRepository.findByEmail(email).get(0);
        
        return usuario;
    }
    
}

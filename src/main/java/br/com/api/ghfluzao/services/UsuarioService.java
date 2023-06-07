package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.usuario.FindUsuarioResponse;
import br.com.api.ghfluzao.data.repositories.UsuarioRepository;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.models.Usuario;

@Service
public class UsuarioService implements UsuarioServiceInterface{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> criarUsuario(Usuario usuario){
        
        if(usuario.getEmail().equals(null) || usuario.getNome().equals(null) || usuario.getSenha().equals(null)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public ResponseEntity<FindUsuarioResponse> pegarUsuarioPorEmail(String email){
        var usuario = usuarioRepository.findByEmail(email).get(0);

        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var response = new FindUsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}

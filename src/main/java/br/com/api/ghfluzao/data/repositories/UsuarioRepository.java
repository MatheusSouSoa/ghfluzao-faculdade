package br.com.api.ghfluzao.data.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    
    Optional<Usuario> findByEmail(String login);

}

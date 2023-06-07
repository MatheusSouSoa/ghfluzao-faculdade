package br.com.api.ghfluzao.data.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.Usuario;
import java.util.List;


public interface UsuarioRepository  extends CrudRepository<Usuario, Long>{
    
    List<Usuario> findByEmail(String email);

}

package br.com.api.ghfluzao.data.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.UsuarioTokens;
import java.util.List;


public interface UsuarioTokensRepository extends CrudRepository<UsuarioTokens, Long>{
 
    List<UsuarioTokens> findByToken(String token);
}

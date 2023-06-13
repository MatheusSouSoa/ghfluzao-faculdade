package br.com.api.ghfluzao.data.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.Parte;
import java.util.List;


public interface ParteRepository extends CrudRepository<Parte, Long>{
  
    List<Parte> findByNome(String nome);
}

package br.com.api.ghfluzao.data.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.Prova;

public interface ProvaRepository extends CrudRepository<Prova, Long>{
  
    long countByCodigo(Long codigo);
    
    List<Prova> findAllById(Iterable<Long> ids);

}

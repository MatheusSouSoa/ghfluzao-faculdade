package br.com.api.ghfluzao.data.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.Assunto;

public interface AssuntoRepository extends CrudRepository<Assunto, Long>{
  
    List<Assunto> findByMateria(String materia);

}

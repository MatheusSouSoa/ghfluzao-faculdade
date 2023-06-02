package br.com.api.ghfluzao.data.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.Curso;



public interface CursoRepository extends CrudRepository<Curso, Long>{
  
    List<Curso> findByNome(String nome);
}

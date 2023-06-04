package br.com.api.ghfluzao.data.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.api.ghfluzao.models.Prova;

public interface ProvaRepository extends CrudRepository<Prova, Long>{
  
    long countByCodigo(Long codigo);
    
    List<Prova> findAllById(Iterable<Long> ids);

    @Query("SELECT p FROM Prova p WHERE p.codigo_curso = :codigoCurso")
    List<Prova> findByCodigo_curso(@Param("codigoCurso") Long codigoCurso);

    @Query("SELECT p FROM Prova p WHERE p.ano = :ano")
    List<Prova> findByAno(@Param("ano") Integer codigoCurso);

}

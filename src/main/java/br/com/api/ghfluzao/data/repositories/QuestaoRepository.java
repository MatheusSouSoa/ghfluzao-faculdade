package br.com.api.ghfluzao.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.api.ghfluzao.models.Questao;
import java.util.List;


public interface QuestaoRepository extends CrudRepository<Questao, Long>{
  
    @Query("SELECT q FROM Questao q WHERE q.codigo_prova = :codigo_prova")
    List<Questao> findByCodigo_prova(Long codigo_prova);
}

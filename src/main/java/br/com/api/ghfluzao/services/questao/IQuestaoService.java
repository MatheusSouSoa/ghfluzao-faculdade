package br.com.api.ghfluzao.services.questao;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Questao;

public interface IQuestaoService {

    Iterable<Questao> listar();
    Questao validarQuestao(Long codigo);
    ResponseEntity<?> criarQuestao(CreateQuestaoRequest request);
}
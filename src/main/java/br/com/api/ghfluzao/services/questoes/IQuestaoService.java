package br.com.api.ghfluzao.services.questoes;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Questao;

public interface IQuestaoService {

    Iterable<Questao> listar();
    Questao validarQuestao(CreateQuestaoRequest request);
    ResponseEntity<?> criarQuestao(CreateQuestaoRequest request);
}

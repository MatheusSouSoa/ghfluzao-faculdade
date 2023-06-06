package br.com.api.ghfluzao.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.questao.CreateQuestaoRequest;
import br.com.api.ghfluzao.data.dto.questao.SearchQuestaoResponse;
import br.com.api.ghfluzao.models.Questao;

public interface QuestaoServiceInterface {

    Questao validarQuestao(Long codigo);
    ResponseEntity<?> criarQuestao(CreateQuestaoRequest request);
    ResponseEntity<?> editarQuestao(CreateQuestaoRequest request, Long questaoCodigo);
    ResponseEntity<?> removerQuestao(Long questaoCodigo);
    ResponseEntity<?> selecionarQuestaoPorCodigo(Long codigo);
    List<SearchQuestaoResponse> listar();
    List<SearchQuestaoResponse> findByCodigo_prova(Long codigoProva);
    ResponseEntity<SearchQuestaoResponse> aprovarQuestao(Long codigoQuestao);
    ResponseEntity<SearchQuestaoResponse> recusarQuestao(Long codigoQuestao);
    ResponseEntity<SearchQuestaoResponse> suspenderQuestao(Long codigoQuestao);
    ResponseEntity<SearchQuestaoResponse> revisarQuestao(Long codigoQuestao);
    ResponseEntity<SearchQuestaoResponse> esperarQuestao(Long codigoQuestao);
    ResponseEntity<SearchQuestaoResponse> aprontarQuestao(Long codigoQuestao);
}

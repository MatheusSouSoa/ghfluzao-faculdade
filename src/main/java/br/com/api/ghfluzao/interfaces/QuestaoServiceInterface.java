package br.com.api.ghfluzao.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.questao.CreateQuestaoRequest;
import br.com.api.ghfluzao.data.dto.questao.FindQuestaoResponse;
import br.com.api.ghfluzao.models.Questao;

public interface QuestaoServiceInterface {

    Questao validarQuestao(Long codigo);
    ResponseEntity<?> criarQuestao(CreateQuestaoRequest request);
    ResponseEntity<?> editarQuestao(CreateQuestaoRequest request, Long questaoCodigo);
    ResponseEntity<?> removerQuestao(Long questaoCodigo);
    ResponseEntity<?> selecionarQuestaoPorCodigo(Long codigo);
    List<FindQuestaoResponse> listar();
    List<FindQuestaoResponse> findByCodigo_prova(Long codigoProva);
    ResponseEntity<FindQuestaoResponse> aprovarQuestao(Long codigoQuestao);
    ResponseEntity<FindQuestaoResponse> recusarQuestao(Long codigoQuestao);
    ResponseEntity<FindQuestaoResponse> suspenderQuestao(Long codigoQuestao);
    ResponseEntity<FindQuestaoResponse> revisarQuestao(Long codigoQuestao);
    ResponseEntity<FindQuestaoResponse> esperarQuestao(Long codigoQuestao);
    ResponseEntity<FindQuestaoResponse> aprontarQuestao(Long codigoQuestao);
}

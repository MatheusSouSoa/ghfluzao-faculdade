package br.com.api.ghfluzao.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.questao.CreateQuestaoRequest;
import br.com.api.ghfluzao.data.dto.questao.SearchQuestaoResponse;
import br.com.api.ghfluzao.data.repositories.QuestaoRepository;
import br.com.api.ghfluzao.enums.QuestaoStatus;
import br.com.api.ghfluzao.interfaces.AssuntoServiceInterface;
import br.com.api.ghfluzao.interfaces.ParteServiceInterface;
import br.com.api.ghfluzao.interfaces.ProvaServiceInterface;
import br.com.api.ghfluzao.interfaces.QuestaoServiceInterface;
import br.com.api.ghfluzao.models.Questao;

@Service
public class QuestaoService implements QuestaoServiceInterface {
    
    @Autowired
    private QuestaoRepository _questaoRepository;

    @Autowired
    private ProvaServiceInterface _provaService;
    @Autowired
    private ParteServiceInterface _parteService;
    @Autowired
    private AssuntoServiceInterface _assuntoService;

    public ResponseEntity<?> criarQuestao(CreateQuestaoRequest request){ 

        var prova = _provaService.validarProva(request.getCodigoProva());
        var parte = _parteService.validarParte(request.codigoParte);
        var assunto = _assuntoService.validarAssunto(request.codigoAssunto);

        var questao = new Questao(request.getEnunciado(),request.numeroQuestao, request.urlFigura , prova.getCodigo(), parte.getCodigo(), assunto.getCodigo());

        if (prova == null || parte == null || assunto == null) {
            return new ResponseEntity<>("Erro ao criar questão", HttpStatus.BAD_REQUEST);
        }
        if(questao.getEnunciado().equals(null)){
            return new ResponseEntity<>("Questão invalida", HttpStatus.BAD_REQUEST);
        }

        questao.setSituacao(QuestaoStatus.AGUARDANDO);

        return new ResponseEntity<>(_questaoRepository.save(questao), HttpStatus.CREATED);
    }

    public Questao validarQuestao(Long codigo) {
        var questao = _questaoRepository.findById(codigo).get();

        if (questao == null) {
            throw new EmptyResultDataAccessException(0);
        }

        return questao;
    }

    public List<SearchQuestaoResponse> listar(){
        List<Questao> questoes = (List<Questao>) _questaoRepository.findAll();
        return mapToSPRList(questoes);
    }

    public ResponseEntity<?> editarQuestao(CreateQuestaoRequest request, Long questaoCodigo) {

        var questao = _questaoRepository.findById(questaoCodigo).get();

        if (questao == null) {
            return new ResponseEntity<>("Questão não existe. :(", HttpStatus.NOT_FOUND);
        }

        if(request.numeroQuestao != null){
            questao.setNumero(request.numeroQuestao);
        }

        if(request.urlFigura != null){
            questao.setFigura(request.urlFigura);
        }

        if (request.codigoAssunto != null) {
            var assunto = _assuntoService.validarAssunto(request.codigoAssunto);
            
            if(assunto == null){
                return new ResponseEntity<>("Assunto não existe. :(", HttpStatus.NOT_FOUND);
            }
            
            questao.setCodigo_assunto(request.codigoAssunto);
        }

        if (request.codigoParte != null) {
            var parte = _parteService.validarParte(request.codigoParte);

            if(parte == null){
                return new ResponseEntity<>("Parte não existe. :(", HttpStatus.NOT_FOUND);
            }
            
            questao.setCodigo_parte(request.codigoParte);
        }

        if (request.codigoProva != null) {

            var prova = _provaService.validarProva(request.codigoProva);
            if(prova == null){
                return new ResponseEntity<>("Prova não existe. :(", HttpStatus.NOT_FOUND);
            }
            
            questao.setCodigo_prova(request.codigoProva);
        }
        SearchQuestaoResponse questaoResponse = new SearchQuestaoResponse(questao.getCodigo(), questao.getCodigo_parte(), questao.getCodigo_assunto(), questao.getCodigo_prova(), questao.getEnunciado(), questao.getNumero(), questao.getFigura(), questao.getSituacao());

        _questaoRepository.save(questao);

        return ResponseEntity.status(HttpStatus.OK).body(questaoResponse);
    }

    public ResponseEntity<?> removerQuestao(Long questaoCodigo) {

        var questao = _questaoRepository.findById(questaoCodigo).get();

        if (questao == null) {
            return new ResponseEntity<>("questao não existe.", HttpStatus.NOT_FOUND);
        }

        _questaoRepository.delete(questao);
        return new ResponseEntity<>("questao removida com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarQuestaoPorCodigo(Long codigo) {
        var questao = validarQuestao(codigo);

        if (questao == null) {
            return new ResponseEntity<>("questao não encontrada.", HttpStatus.NOT_FOUND);
        }
        SearchQuestaoResponse questaoResponse = new SearchQuestaoResponse(questao.getCodigo(),questao.getCodigo_parte(),questao.getCodigo_assunto(), questao.getCodigo_prova(), questao.getEnunciado(), questao.getNumero(), questao.getFigura(), questao.getSituacao());

        return ResponseEntity.status(HttpStatus.OK).body(questaoResponse);
    }

    private List<SearchQuestaoResponse> mapToSPRList(List<Questao> questoes) {
        return questoes.stream()
                .map(questao -> new SearchQuestaoResponse(questao.getCodigo(),questao.getCodigo_parte(),questao.getCodigo_assunto(), questao.getCodigo_prova(), questao.getEnunciado(), questao.getNumero(), questao.getFigura(), questao.getSituacao()))
                .collect(Collectors.toList());

    }

    public List<SearchQuestaoResponse> findByCodigo_prova(Long codigoProva){
        return mapToSPRList(_questaoRepository.findByCodigo_prova(codigoProva));
    }

    // private List<SearchProvaResponse> mapToSPRList(List<Prova> provas) {
    //     return provas.stream()
    //             .map(prova -> new SearchProvaResponse(
    //                     prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(),
    //                     prova.getCodigo_curso()))
    //             .collect(Collectors.toList());
    // }
}

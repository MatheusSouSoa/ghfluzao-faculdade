package br.com.api.ghfluzao.services.questao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.QuestaoRepository;
import br.com.api.ghfluzao.models.Questao;
import br.com.api.ghfluzao.services.assunto.IAssuntoService;
import br.com.api.ghfluzao.services.parte.IParteService;
import br.com.api.ghfluzao.services.prova.IProvaService;

@Service
public class QuestaoService implements IQuestaoService {
    
    @Autowired
    private QuestaoRepository _questaoRepository;

    @Autowired
    private IProvaService _provaService;
    @Autowired
    private IParteService _parteService;
    @Autowired
    private IAssuntoService _assuntoService;

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
        return new ResponseEntity<>(_questaoRepository.save(questao), HttpStatus.CREATED);
    }

    public Questao validarQuestao(Long codigo) {
        var questao = _questaoRepository.findById(codigo).get();

        if (questao == null) {
            throw new EmptyResultDataAccessException(0);
        }

        return questao;
    }

    public Iterable<Questao> listar(){
        return _questaoRepository.findAll();
    }
}
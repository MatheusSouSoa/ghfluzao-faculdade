package br.com.api.ghfluzao.services.questao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.QuestaoRepository;
import br.com.api.ghfluzao.models.Questao;

@Service
public class QuestaoService implements IQuestaoService {
    
    @Autowired
    private QuestaoRepository _questaoRepository;

    public ResponseEntity<?> criarQuestao(CreateQuestaoRequest request){
        var questao = new Questao(request.enunciado);

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

package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.gabarito.CreateGabaritoRequest;
import br.com.api.ghfluzao.data.repositories.GabaritoRepository;
import br.com.api.ghfluzao.interfaces.GabaritoServiceInterface;
import br.com.api.ghfluzao.interfaces.QuestaoServiceInterface;
import br.com.api.ghfluzao.models.Gabarito;

@Service
public class GabaritoService implements GabaritoServiceInterface {

    @Autowired
    private GabaritoRepository _gabaritoRepository;

    @Autowired
    private QuestaoServiceInterface _questaoService;

    public ResponseEntity<?> criarGabarito(CreateGabaritoRequest request){

        var questao = _questaoService.validarQuestao(request.questaoCodigo);

        var gabarito = new Gabarito(request.resposta, request.questaoCodigo);

        if(gabarito.getResposta().equals(null) || questao == null || gabarito == null){
            return new ResponseEntity<>("Gabarito invalido.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_gabaritoRepository.save(gabarito), HttpStatus.CREATED);
    }

    public Iterable<Gabarito> listar(){
        return _gabaritoRepository.findAll();
    }
    
}

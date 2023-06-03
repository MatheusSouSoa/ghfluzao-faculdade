package br.com.api.ghfluzao.services.gabarito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.GabaritoRepository;
import br.com.api.ghfluzao.models.Gabarito;
import br.com.api.ghfluzao.services.questao.IQuestaoService;

@Service
public class GabaritoService implements IGabaritoService {

    @Autowired
    private GabaritoRepository _gabaritoRepository;

    @Autowired
    private IQuestaoService _questaoService;

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

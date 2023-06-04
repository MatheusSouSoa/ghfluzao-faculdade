package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.gabarito.CreateGabaritoRequest;
import br.com.api.ghfluzao.data.dto.gabarito.SearchGabaritoResponse;
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

    public Gabarito validarGabarito(Long gabaritoCodigo) {
        var gabarito = _gabaritoRepository.findById(gabaritoCodigo).get();

        if (gabarito == null) {
            throw new EmptyResultDataAccessException(0);
        }

        return gabarito;
    }

    public Iterable<Gabarito> listar(){
        return _gabaritoRepository.findAll();
    }

    public ResponseEntity<?> editarGabarito(CreateGabaritoRequest request, Long gabaritoCodigo) {

        var gabarito = _gabaritoRepository.findById(gabaritoCodigo).get();

        if (gabarito == null) {
            return new ResponseEntity<>("Gabarito não existe. :(", HttpStatus.NOT_FOUND);
        }

        if (request.resposta != null) {
            gabarito.setResposta(request.resposta);
        }
        if(request.questaoCodigo != null ){
            gabarito.setCodigo_questao(request.questaoCodigo);
        }

        SearchGabaritoResponse gabaritoResponse = new SearchGabaritoResponse(gabarito.getCodigo(), gabarito.getResposta(), gabarito.getCodigo_questao());

        _gabaritoRepository.save(gabarito);

        return ResponseEntity.status(HttpStatus.OK).body(gabaritoResponse);
    }

    public ResponseEntity<?> removerGabarito(Long GabaritoCodigo) {

        var Gabarito = _gabaritoRepository.findById(GabaritoCodigo).get();

        if (Gabarito == null) {
            return new ResponseEntity<>("Gabarito não existe.", HttpStatus.NOT_FOUND);
        }

        _gabaritoRepository.delete(Gabarito);
        return new ResponseEntity<>("Gabarito removido com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarGabaritoPorCodigo(Long codigo) {
        var gabarito = validarGabarito(codigo);

        if (gabarito == null) {
            return new ResponseEntity<>("Gabarito não encontrado.", HttpStatus.NOT_FOUND);
        }
        SearchGabaritoResponse gabaritoResponse = new SearchGabaritoResponse(gabarito.getCodigo(), gabarito.getResposta(), gabarito.getCodigo_questao());

        return ResponseEntity.status(HttpStatus.CREATED).body(gabaritoResponse);
    }
    
}

package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.opcao.CreateOpcaoRequest;
import br.com.api.ghfluzao.data.dto.opcao.FindOpcaoResponse;
import br.com.api.ghfluzao.data.repositories.OpcaoRepository;
import br.com.api.ghfluzao.interfaces.OpcaoServiceInterface;
import br.com.api.ghfluzao.interfaces.QuestaoServiceInterface;
import br.com.api.ghfluzao.models.Opcao;

@Service
public class OpcaoService implements OpcaoServiceInterface{
    
    @Autowired
    private OpcaoRepository _opcaoRepository;

    @Autowired
    private QuestaoServiceInterface _questaoService;

    public ResponseEntity<?> criarOpcao(CreateOpcaoRequest request){

        var questao = _questaoService.validarQuestao(request.questaoCodigo);

        var opcao = new Opcao(Character.toLowerCase(request.letra), request.texto, request.questaoCodigo);

        if(opcao.getLetra().equals(null) || opcao.getTexto().equals(null) || opcao.getLetra().toString().length() > 1 || questao == null){
            return new ResponseEntity<>("Dados de opção invalidos.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(_opcaoRepository.save(opcao), HttpStatus.CREATED);
    }

    public Opcao validarOpcao(Long codigo){
        var opcao = _opcaoRepository.findById(codigo).get();

        if(opcao == null){
            throw new EmptyResultDataAccessException(0);
        }

        return opcao;
    }

    public Iterable<Opcao> listar(){
        return _opcaoRepository.findAll();
    }

    public ResponseEntity<?> editarOpcao(CreateOpcaoRequest request, Long opcaoCodigo) {

        var opcao = _opcaoRepository.findById(opcaoCodigo).get();

        if (opcao == null) {
            return new ResponseEntity<>("opcao não existe. :(", HttpStatus.NOT_FOUND);
        }

        if (request.getLetra() != null) {
            opcao.setLetra(Character.toLowerCase(request.getLetra()));
        }
        if (request.texto != null) {
            opcao.setTexto(request.texto);
        }
        if (request.questaoCodigo != null) {
            var questao = _questaoService.validarQuestao(request.questaoCodigo);
            if (questao == null) {
                return new ResponseEntity<>("Questao não existe. :(", HttpStatus.NOT_FOUND);
            }
            if (questao.getOpcoes().contains(opcao)) {
                return new ResponseEntity<>("Opção ja existe na questão", HttpStatus.ALREADY_REPORTED);
            }
            opcao.setCodigo_questao(request.questaoCodigo);
        }
        FindOpcaoResponse opcaoResponse = new FindOpcaoResponse(opcao.getCodigo(), opcao.getLetra(), opcao.getTexto(), opcao.getCodigo_questao());

        _opcaoRepository.save(opcao);

        return ResponseEntity.status(HttpStatus.OK).body(opcaoResponse);
    }

    public ResponseEntity<?> removerOpcao(Long opcaoCodigo) {

        var opcao = _opcaoRepository.findById(opcaoCodigo).get();

        if (opcao == null) {
            return new ResponseEntity<>("opcao não existe.", HttpStatus.NOT_FOUND);
        }

        _opcaoRepository.delete(opcao);
        return new ResponseEntity<>("opcao removida com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarOpcaoPorCodigo(Long codigo) {
        var opcao = validarOpcao(codigo);

        if (opcao == null) {
            return new ResponseEntity<>("opcao não encontrada.", HttpStatus.NOT_FOUND);
        }
        FindOpcaoResponse opcaoResponse = new FindOpcaoResponse(opcao.getCodigo(), opcao.getLetra(),
                opcao.getTexto(), opcao.getCodigo_questao());

        return ResponseEntity.status(HttpStatus.OK).body(opcaoResponse);
    }
}

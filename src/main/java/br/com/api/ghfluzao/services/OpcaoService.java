package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.opçao.CreateOpcaoRequest;
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

}

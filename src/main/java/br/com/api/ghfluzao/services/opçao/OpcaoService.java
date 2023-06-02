package br.com.api.ghfluzao.services.opçao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.OpcaoRepository;
import br.com.api.ghfluzao.models.Opcao;

@Service
public class OpcaoService implements IOpcaoService{
    
    @Autowired
    private OpcaoRepository _opcaoRepository;

    public ResponseEntity<?> criarOpcao(CreateOpcaoRequest request){
        var opcao = new Opcao(Character.toLowerCase(request.letra), request.texto);

        if(opcao.getLetra().equals(null) || opcao.getTexto().equals(null) || opcao.getLetra().toString().length() > 1){
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

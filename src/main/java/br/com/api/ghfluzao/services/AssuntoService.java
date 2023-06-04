package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.assunto.CreateAssuntoRequest;
import br.com.api.ghfluzao.data.repositories.AssuntoRepository;
import br.com.api.ghfluzao.interfaces.AssuntoServiceInterface;
import br.com.api.ghfluzao.models.Assunto;

@Service
public class AssuntoService implements AssuntoServiceInterface {
    
    @Autowired
    private AssuntoRepository _assuntoRepository;

    public ResponseEntity<?> criarAssunto(CreateAssuntoRequest request){
        var assunto = new Assunto(request.materia);

        if(assunto.getMateria().equals(null)){
            return new ResponseEntity<>("Assunto invalido", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(_assuntoRepository.save(assunto), HttpStatus.CREATED);
    }

    public Assunto validarAssunto(Long assuntoCodigo){
        var assunto = _assuntoRepository.findById(assuntoCodigo).get();

        if(assunto == null){
            throw new EmptyResultDataAccessException(0);
        }

        return assunto;
    }

    public Iterable<Assunto> listar(){
        return _assuntoRepository.findAll();
    }
}

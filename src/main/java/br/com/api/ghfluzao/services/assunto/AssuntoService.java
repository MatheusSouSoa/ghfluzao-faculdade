package br.com.api.ghfluzao.services.assunto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.AssuntoRepository;
import br.com.api.ghfluzao.models.Assunto;

@Service
public class AssuntoService implements IAssuntoService {
    
    @Autowired
    private AssuntoRepository _assuntoRepository;

    public ResponseEntity<?> criarAssunto(CreateAssuntoRequest request){
        var assunto = new Assunto(request.materia);

        if(assunto.getMateria().equals(null)){
            return new ResponseEntity<>("Assunto invalido", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(_assuntoRepository.save(assunto), HttpStatus.CREATED);
    }

    public Assunto validarAssunto(String assuntoNome){
        var assunto = _assuntoRepository.findByMateria(assuntoNome).get(0);

        if(assunto == null){
            throw new EmptyResultDataAccessException(assuntoNome, 0);
        }

        return assunto;
    }

    public Iterable<Assunto> listar(){
        return _assuntoRepository.findAll();
    }
}

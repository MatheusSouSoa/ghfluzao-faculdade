package br.com.api.ghfluzao.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.assunto.CreateAssuntoRequest;
import br.com.api.ghfluzao.data.dto.assunto.FindAssuntoResponse;
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

    public List<FindAssuntoResponse> listar(){
        List<Assunto> assuntos = (List<Assunto>) _assuntoRepository.findAll();
        return mapToSPRList(assuntos);
    }

    public ResponseEntity<?> editarAssunto(CreateAssuntoRequest request, Long assuntoCodigo) {

        var assunto = _assuntoRepository.findById(assuntoCodigo).get();

        if (assunto == null) {
            return new ResponseEntity<>("assunto não existe. :(", HttpStatus.NOT_FOUND);
        }

        if (request.materia != null) {
            assunto.setMateria(request.materia);
        }
   
        FindAssuntoResponse assuntoResponse = new FindAssuntoResponse(assunto.getCodigo(), assunto.getMateria());

        _assuntoRepository.save(assunto);

        return ResponseEntity.status(HttpStatus.OK).body(assuntoResponse);
    }

    public ResponseEntity<?> removerAssunto(Long assuntoCodigo) {

        var assunto = _assuntoRepository.findById(assuntoCodigo).get();

        if (assunto == null) {
            return new ResponseEntity<>("assunto não existe.", HttpStatus.NOT_FOUND);
        }

        _assuntoRepository.delete(assunto);
        return new ResponseEntity<>("assunto removido com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarAssuntoPorCodigo(Long codigo) {
        var assunto = validarAssunto(codigo);

        if (assunto == null) {
            return new ResponseEntity<>("assunto não encontrado.", HttpStatus.NOT_FOUND);
        }
        FindAssuntoResponse assuntoResponse = new FindAssuntoResponse(assunto.getCodigo(), assunto.getMateria());

        return ResponseEntity.status(HttpStatus.CREATED).body(assuntoResponse);
    }

    private List<FindAssuntoResponse> mapToSPRList(List<Assunto> assuntos) {
        return assuntos.stream()
                .map(assunto -> new FindAssuntoResponse(assunto.getCodigo(), assunto.getMateria()))
                .collect(Collectors.toList());
    }
}

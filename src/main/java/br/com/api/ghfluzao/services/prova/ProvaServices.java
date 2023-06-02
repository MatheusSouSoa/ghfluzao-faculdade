package br.com.api.ghfluzao.services.prova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.ProvaRepository;
import br.com.api.ghfluzao.models.Prova;
import br.com.api.ghfluzao.services.curso.ICursoService;

@Service
public class ProvaServices implements IProvaService {

    @Autowired
    private ProvaRepository _provaRepository;

    @Autowired
    private ICursoService _cursoService;

    public ResponseEntity<?> criarProva(CreateProvaRequest request){
        var curso = _cursoService.validarCurso(request.cursoNome);
        
        if(curso == null || request.getCursoNome().equals(null)){
            return new ResponseEntity<>("Curso invalido",HttpStatus.BAD_REQUEST);
        }
        var prova = new Prova(request.getProva().getAno(),curso.getCodigo());

        if(prova.getAno().equals(null) || prova.getAno().equals(0)){
            return new ResponseEntity<>("Ano invalido",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_provaRepository.save(prova), HttpStatus.CREATED);
    }

    public Iterable<Prova> listar() {
        return _provaRepository.findAll();
    }
}

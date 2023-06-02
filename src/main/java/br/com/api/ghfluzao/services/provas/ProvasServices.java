package br.com.api.ghfluzao.services.provas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.ProvaRepository;
import br.com.api.ghfluzao.models.Prova;
import br.com.api.ghfluzao.services.cursos.ICursoService;

@Service
public class ProvasServices implements IProvasService {

    @Autowired
    private ProvaRepository _provaRepository;

    @Autowired
    private ICursoService _cursoService;

    public ResponseEntity<?> criarProva(CreateProvaRequest request){
        var prova = new Prova(request.ano);

        if(_cursoService.validarCurso(request.cursoNome) == null){
            return new ResponseEntity<>("Curso invalido",HttpStatus.BAD_REQUEST);
        }

        if(prova.getAno().equals(null)){
            return new ResponseEntity<>("Ano invalido",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_provaRepository.save(prova), HttpStatus.CREATED);
    }
}

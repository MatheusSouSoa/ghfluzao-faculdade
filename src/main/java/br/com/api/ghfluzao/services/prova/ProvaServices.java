package br.com.api.ghfluzao.services.prova;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        var prova = new Prova(request.getAno(),curso.getCodigo());

        if(prova.getAno().equals(null) || prova.getAno().equals(0)){
            return new ResponseEntity<>("Ano invalido",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_provaRepository.save(prova), HttpStatus.CREATED);
    }

    public Prova validarProva(Long codigoProva) {

        var prova = _provaRepository.findById(codigoProva).get();

        if (prova == null) {
            throw new EmptyResultDataAccessException(0);
        }

        return prova;
    }

    public Iterable<Prova> listar() {
        return _provaRepository.findAll();
    }

    public ResponseEntity<?> aplicarProva(Long codigoProva) {

        var prova = _provaRepository.findById(codigoProva).get();
        
        if(_provaRepository.countByCodigo(codigoProva) == 0) {
            return new ResponseEntity<>("Prova não existe. :(", HttpStatus.NOT_FOUND);
        }
        prova.setData_aplicacao(Calendar.getInstance());
        
        return new ResponseEntity<>(_provaRepository.save(prova), HttpStatus.OK);
    }


    public ResponseEntity<?> editarProva(ProvaEditRequest request, Long provaCodigo) {

        var prova = _provaRepository.findById(provaCodigo).get();

        if(prova == null) {
            return new ResponseEntity<>("Prova não existe. :(", HttpStatus.NOT_FOUND);
        }

        if(request.getAno() != null){
            prova.setAno(request.getAno());
        }
        if(request.getCodigoCurso() != null){
            var curso = _cursoService.validarCurso(request.codigoCurso);
            if (curso == null) {
                return new ResponseEntity<>("Curso não existe. :(", HttpStatus.NOT_FOUND);
            }
            if (!curso.getProvas().contains(prova)) {
                return ResponseEntity.notFound().build();
            }
            prova.setCodigo_curso(request.getCodigoCurso());
        }

        return new ResponseEntity<>(_provaRepository.save(prova), HttpStatus.OK);
    }

    public ResponseEntity<?> removerProva(Long provaCodigo){

        var prova = _provaRepository.findById(provaCodigo).get();

        if(prova == null){
            return new ResponseEntity<>("Prova não existe.", HttpStatus.NOT_FOUND);
        }

        _provaRepository.delete(prova);
        return new ResponseEntity<>("Prova removida com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarProvaPorCodigo(Long codigo){
        var prova = validarProva(codigo);

        if(prova == null){
            return new ResponseEntity<>("Prova não encontrada.", HttpStatus.NOT_FOUND);
        }
        SearchProvaResponse provaResponse = new SearchProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getCodigo_curso());

        return ResponseEntity.status(HttpStatus.CREATED).body(provaResponse);
    }

}

package br.com.api.ghfluzao.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.curso.CreateCursoRequest;
import br.com.api.ghfluzao.data.dto.curso.SearchCursoResponse;
import br.com.api.ghfluzao.data.repositories.CursoRepository;
import br.com.api.ghfluzao.interfaces.CursoServiceInterface;
import br.com.api.ghfluzao.models.Curso;

@Service
public class CursoService implements CursoServiceInterface{

    @Autowired
    private CursoRepository _cursoRepository;

    public ResponseEntity<?> criarCurso(CreateCursoRequest request){
        var curso = new Curso(request.nome);

        if(curso.getNome().equals(null)){
            return new ResponseEntity<>("Erro ao criar/selecionar prova", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(_cursoRepository.save(curso), HttpStatus.CREATED);
    }

    
    public Curso validarCurso(String cursoNome){

        var curso = _cursoRepository.findByNome(cursoNome).get(0);

        if(curso == null){
            throw new EmptyResultDataAccessException(cursoNome, 0);
        }

        return curso;
    }

    public Curso validarCurso(Long cursoCodigo){
        
        var curso = _cursoRepository.findById(cursoCodigo).get();

        if(curso == null){
            throw new EmptyResultDataAccessException(0);
        }

        return curso;
    }

    public List<SearchCursoResponse> listar(){
        List<Curso> cursos = (List<Curso>) _cursoRepository.findAll();
        return mapToSPRList(cursos);
    }

    public ResponseEntity<?> editarCurso(CreateCursoRequest request, Long cursoCodigo) {

        var curso = _cursoRepository.findById(cursoCodigo).get();

        if (curso == null) {
            return new ResponseEntity<>("Curso não existe. :(", HttpStatus.NOT_FOUND);
        }

        if (request.nome != null) {
            curso.setNome(request.nome);
        }

        SearchCursoResponse cursoResponse = new SearchCursoResponse(curso.getCodigo(), curso.getNome());

        _cursoRepository.save(curso);

        return ResponseEntity.status(HttpStatus.OK).body(cursoResponse);
    }

    public ResponseEntity<?> removerCurso(Long CursoCodigo) {

        var curso = _cursoRepository.findById(CursoCodigo).get();

        if (curso == null) {
            return new ResponseEntity<>("Curso não existe.", HttpStatus.NOT_FOUND);
        }

        _cursoRepository.delete(curso);
        return new ResponseEntity<>("Curso removido com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarCursoPorCodigo(Long codigo) {
        var curso = validarCurso(codigo);

        if (curso == null) {
            return new ResponseEntity<>("Curso não encontrado.", HttpStatus.NOT_FOUND);
        }
        SearchCursoResponse cursoResponse = new SearchCursoResponse(curso.getCodigo(),curso.getNome());

        return ResponseEntity.status(HttpStatus.OK).body(cursoResponse);
    }

    private List<SearchCursoResponse> mapToSPRList(List<Curso> cursos) {
        return cursos.stream()
                .map(curso -> new SearchCursoResponse(curso.getCodigo(),curso.getNome()))
                .collect(Collectors.toList());
    }

}

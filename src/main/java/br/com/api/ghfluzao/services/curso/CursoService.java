package br.com.api.ghfluzao.services.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.CursoRepository;
import br.com.api.ghfluzao.models.Curso;

@Service
public class CursoService implements ICursoService{

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
        
        // if(!_cursoRepository.findByNome(cursoNome).isEmpty()){
        //     throw new EmptyResultDataAccessException(cursoNome, 0);
        // }

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

    public Iterable<Curso> listar(){
        return _cursoRepository.findAll();
    }

}

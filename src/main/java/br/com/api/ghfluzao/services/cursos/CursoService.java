package br.com.api.ghfluzao.services.cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.CursoRepository;
import br.com.api.ghfluzao.models.Curso;

@Service
public class CursoService implements ICursoService{

    @Autowired
    private CursoRepository _cursoRepository;

    
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

    public Iterable<Curso> listar(){
        return _cursoRepository.findAll();
    }

}

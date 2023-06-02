package br.com.api.ghfluzao.services.cursos;


import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Curso;

public interface ICursoService {

    Curso validarCurso(String string);
    public Iterable<Curso> listar();
    ResponseEntity<?> criarCurso(CreateCursoRequest request);
}

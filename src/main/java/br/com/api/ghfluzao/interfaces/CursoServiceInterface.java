package br.com.api.ghfluzao.interfaces;


import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.curso.CreateCursoRequest;
import br.com.api.ghfluzao.models.Curso;

public interface CursoServiceInterface {

    Curso validarCurso(String string);
    Curso validarCurso(Long cursoCodigo);
    public Iterable<Curso> listar();
    ResponseEntity<?> criarCurso(CreateCursoRequest request);
    ResponseEntity<?> editarCurso(CreateCursoRequest request, Long cursoCodigo);
    ResponseEntity<?> removerCurso(Long CursoCodigo);
    ResponseEntity<?> selecionarCursoPorCodigo(Long codigo);
}
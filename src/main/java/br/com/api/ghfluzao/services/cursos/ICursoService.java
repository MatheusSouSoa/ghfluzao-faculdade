package br.com.api.ghfluzao.services.cursos;

import br.com.api.ghfluzao.models.Curso;

public interface ICursoService {

    Curso validarCurso(String string);
    public Iterable<Curso> listar();
}

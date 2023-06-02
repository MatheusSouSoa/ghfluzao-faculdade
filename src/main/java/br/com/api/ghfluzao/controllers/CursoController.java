package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Curso;
import br.com.api.ghfluzao.services.cursos.ICursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private ICursoService _cursoService;
    
    @GetMapping("/api")
    public Iterable<Curso> listar(){
        return _cursoService.listar();
    }
}

package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Curso;
import br.com.api.ghfluzao.services.cursos.CreateCursoRequest;
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

    @PostMapping("/api")
    public ResponseEntity<String> criarCurso(@RequestBody CreateCursoRequest request){
        _cursoService.criarCurso(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Curso criado com sucesso");
    }
}

package br.com.api.ghfluzao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.curso.CreateCursoRequest;
import br.com.api.ghfluzao.data.dto.curso.FindCursoResponse;
import br.com.api.ghfluzao.interfaces.CursoServiceInterface;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-v3/cursos")
public class CursoController {

    @Autowired
    private CursoServiceInterface _cursoService;

    
    @GetMapping("")
    public List<FindCursoResponse> listar(){
        return _cursoService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarCurso(@RequestBody CreateCursoRequest request){
        _cursoService.criarCurso(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Curso criado com sucesso");
    }

    @PutMapping("/editar-curso/{cursoCodigo}")
    public ResponseEntity<?> editarcurso(@PathVariable Long cursoCodigo,
            @RequestBody CreateCursoRequest request) {
        return _cursoService.editarCurso(request, cursoCodigo);
    }

    @DeleteMapping("/deletar-curso/{codigoCurso}")
    public ResponseEntity<?> removerCurso(@PathVariable Long codigoCurso) {
        return _cursoService.removerCurso(codigoCurso);
    }

    @GetMapping("/buscar-Curso/{codigoCurso}")
    public ResponseEntity<?> buscarCursoPeloCodigo(@PathVariable Long codigoCurso) {
        return _cursoService.selecionarCursoPorCodigo(codigoCurso);
    }
}

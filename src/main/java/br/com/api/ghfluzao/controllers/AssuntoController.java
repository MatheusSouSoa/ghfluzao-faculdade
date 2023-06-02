package br.com.api.ghfluzao.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Assunto;
import br.com.api.ghfluzao.services.assunto.CreateAssuntoRequest;
import br.com.api.ghfluzao.services.assunto.IAssuntoService;

@RestController
@RequestMapping("/assuntos")
public class AssuntoController {
    
    @Autowired
    private IAssuntoService _assuntoService;

    @GetMapping("/api")
    public Iterable<Assunto> listar(){
        return _assuntoService.listar();
    }

    @PostMapping("/api")
    public ResponseEntity<String> criarAssunto(@RequestBody CreateAssuntoRequest request){
        _assuntoService.criarAssunto(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Assunto criado com sucesso!");
    }

}

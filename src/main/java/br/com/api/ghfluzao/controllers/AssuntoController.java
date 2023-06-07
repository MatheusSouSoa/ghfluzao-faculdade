package br.com.api.ghfluzao.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.assunto.CreateAssuntoRequest;
import br.com.api.ghfluzao.data.dto.assunto.FindAssuntoResponse;
import br.com.api.ghfluzao.interfaces.AssuntoServiceInterface;

@RestController
@RequestMapping("/api-v3/assuntos")
public class AssuntoController {
    
    @Autowired
    private AssuntoServiceInterface _assuntoService;

    @GetMapping("")
    public List<FindAssuntoResponse> listar(){
        return _assuntoService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarAssunto(@RequestBody CreateAssuntoRequest request){
        _assuntoService.criarAssunto(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Assunto criado com sucesso!");
    }

    @PutMapping("/editar-assunto/{assuntoCodigo}")
    public ResponseEntity<?> editarassunto(@PathVariable Long assuntoCodigo,
            @RequestBody CreateAssuntoRequest request) {
        return _assuntoService.editarAssunto(request, assuntoCodigo);
    }

    @DeleteMapping("/deletar-assunto/{codigoAssunto}")
    public ResponseEntity<?> removerAssunto(@PathVariable Long codigoAssunto) {
        return _assuntoService.removerAssunto(codigoAssunto);
    }

    @GetMapping("/buscar-assunto/{codigoAssunto}")
    public ResponseEntity<?> buscarAssuntoPeloCodigo(@PathVariable Long codigoAssunto) {
        return _assuntoService.selecionarAssuntoPorCodigo(codigoAssunto);
    }

}

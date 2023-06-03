package br.com.api.ghfluzao.controllers;

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

import br.com.api.ghfluzao.models.Prova;
import br.com.api.ghfluzao.services.prova.CreateProvaRequest;
import br.com.api.ghfluzao.services.prova.IProvaService;
import br.com.api.ghfluzao.services.prova.ProvaEditRequest;

@RestController
@RequestMapping("/api-v1/provas")
public class ProvaController {
    
    @Autowired
    private IProvaService  _provaService;

    @PostMapping("")
    public ResponseEntity<String> criarProva(@RequestBody CreateProvaRequest request){

        _provaService.criarProva(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Prova criada com sucesso");
    }

    @GetMapping("")
    public Iterable<Prova> listar() {
        return _provaService.listar();
    }

    @PutMapping("/aplicar-prova/{codigoProva}")
    public ResponseEntity<?> aplicarProva(@PathVariable Long codigoProva){
        return _provaService.aplicarProva(codigoProva);
    }

    @PutMapping("/editar-prova/{provaCodigo}")
    public ResponseEntity<?> editarProva(@PathVariable Long provaCodigo, @RequestBody ProvaEditRequest request){
        return _provaService.editarProva(request, provaCodigo);
    }

    @DeleteMapping("/deletar-prova/{codigoProva}")
    public ResponseEntity<?> removerProva(@PathVariable Long codigoProva){
        return _provaService.removerProva(codigoProva);
    }
}

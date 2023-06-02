package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Prova;
import br.com.api.ghfluzao.services.prova.CreateProvaRequest;
import br.com.api.ghfluzao.services.prova.IProvaService;

@RestController
@RequestMapping("/provas")
public class ProvaController {
    
    @Autowired
    private IProvaService  _provaService;

    @PostMapping("/api")
    public ResponseEntity<String> criarProva(@RequestBody CreateProvaRequest request){

        _provaService.criarProva(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Prova criada com sucesso");
    }

    @GetMapping("/api")
    public Iterable<Prova> listar() {
        return _provaService.listar();
    }

}

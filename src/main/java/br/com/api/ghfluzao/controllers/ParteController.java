package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Parte;
import br.com.api.ghfluzao.services.parte.CreateParteRequest;
import br.com.api.ghfluzao.services.parte.IParteService;

@RestController
@RequestMapping("/api-v1/partes")
public class ParteController {
    
    @Autowired
    private IParteService _parteService;

    @GetMapping("")
    public Iterable<Parte> listar(){
        return _parteService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarParte(@RequestBody CreateParteRequest request){
        _parteService.criarParte(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Parte criada com sucesso!");
    }

}

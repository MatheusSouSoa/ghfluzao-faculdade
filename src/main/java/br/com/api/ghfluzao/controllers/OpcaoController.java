package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Opcao;
import br.com.api.ghfluzao.services.opçao.CreateOpcaoRequest;
import br.com.api.ghfluzao.services.opçao.IOpcaoService;

@RestController
@RequestMapping("/opcoes")
public class OpcaoController {
    
    @Autowired
    private IOpcaoService _opcaoService;

    @GetMapping("/api")
    public Iterable<Opcao> listar(){
        return _opcaoService.listar();
    }

    @PostMapping("/api")
    public ResponseEntity<String> criarOpcao(@RequestBody CreateOpcaoRequest request){
        _opcaoService.criarOpcao(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Opção criada com sucesso!");
    }
}

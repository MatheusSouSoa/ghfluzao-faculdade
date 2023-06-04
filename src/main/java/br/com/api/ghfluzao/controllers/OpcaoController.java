package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.opçao.CreateOpcaoRequest;
import br.com.api.ghfluzao.interfaces.OpcaoServiceInterface;
import br.com.api.ghfluzao.models.Opcao;
@RestController
@RequestMapping("/api-v1/opcoes")
public class OpcaoController {
    
    @Autowired
    private OpcaoServiceInterface _opcaoService;

    @GetMapping("")
    public Iterable<Opcao> listar(){
        return _opcaoService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarOpcao(@RequestBody CreateOpcaoRequest request){
        _opcaoService.criarOpcao(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Opção criada com sucesso!");
    }
}

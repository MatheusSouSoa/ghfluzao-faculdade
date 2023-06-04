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

    @PutMapping("/editar-opcao/{opcaoCodigo}")
    public ResponseEntity<?> editarOpcao(@PathVariable Long opcaoCodigo, @RequestBody CreateOpcaoRequest request) {
        return _opcaoService.editarOpcao(request, opcaoCodigo);
    }

    @DeleteMapping("/deletar-opcao/{codigoOpcao}")
    public ResponseEntity<?> removerOpcao(@PathVariable Long codigoOpcao) {
        return _opcaoService.removerOpcao(codigoOpcao);
    }

    @GetMapping("/buscar-opcao/{codigoOpcao}")
    public ResponseEntity<?> buscarOpcaoPeloCodigo(@PathVariable Long codigoOpcao) {
        return _opcaoService.selecionarOpcaoPorCodigo(codigoOpcao);
    }
}

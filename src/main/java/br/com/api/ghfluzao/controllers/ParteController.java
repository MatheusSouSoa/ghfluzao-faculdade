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

import br.com.api.ghfluzao.data.dto.parte.CreateParteRequest;
import br.com.api.ghfluzao.interfaces.ParteServiceInterface;
import br.com.api.ghfluzao.models.Parte;

@RestController
@RequestMapping("/api-v1/partes")
public class ParteController {
    
    @Autowired
    private ParteServiceInterface _parteService;

    @GetMapping("")
    public Iterable<Parte> listar(){
        return _parteService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarParte(@RequestBody CreateParteRequest request){
        _parteService.criarParte(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Parte criada com sucesso!");
    }

    @PutMapping("/editar-Parte/{ParteCodigo}")
    public ResponseEntity<?> editarParte(@PathVariable Long ParteCodigo,
            @RequestBody CreateParteRequest request) {
        return _parteService.editarParte(request, ParteCodigo);
    }

    @DeleteMapping("/deletar-Parte/{codigoParte}")
    public ResponseEntity<?> removerParte(@PathVariable Long codigoParte) {
        return _parteService.removerParte(codigoParte);
    }

    @GetMapping("/buscar-Parte/{codigoParte}")
    public ResponseEntity<?> buscarPartePeloCodigo(@PathVariable Long codigoParte) {
        return _parteService.selecionarPartePorCodigo(codigoParte);
    }

}

package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Gabarito;
import br.com.api.ghfluzao.services.gabarito.CreateGabaritoRequest;
import br.com.api.ghfluzao.services.gabarito.IGabaritoService;

@RestController
@RequestMapping("/api-v1/gabaritos")
public class GabaritoController {
    
    @Autowired
    private IGabaritoService _gabaritoService;

    @GetMapping("")
    public Iterable<Gabarito> listar(){
        return _gabaritoService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarGabarito(@RequestBody CreateGabaritoRequest request){
        _gabaritoService.criarGabarito(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Gabarito criado com sucesso!");
    }
}

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

import br.com.api.ghfluzao.data.dto.gabarito.CreateGabaritoRequest;
import br.com.api.ghfluzao.interfaces.GabaritoServiceInterface;
import br.com.api.ghfluzao.models.Gabarito;

@RestController
@RequestMapping("/api-v3/gabaritos")
public class GabaritoController {
    
    @Autowired
    private GabaritoServiceInterface _gabaritoService;

    @GetMapping("")
    public Iterable<Gabarito> listar(){
        return _gabaritoService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarGabarito(@RequestBody CreateGabaritoRequest request){
        _gabaritoService.criarGabarito(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Gabarito criado com sucesso!");
    }

    @PutMapping("/editar-gabarito/{gabaritoCodigo}")
    public ResponseEntity<?> editarGabarito(@PathVariable Long gabaritoCodigo,
            @RequestBody CreateGabaritoRequest request) {
        return _gabaritoService.editarGabarito(request, gabaritoCodigo);
    }

    @DeleteMapping("/deletar-gabarito/{codigoGabarito}")
    public ResponseEntity<?> removerGabarito(@PathVariable Long codigoGabarito) {
        return _gabaritoService.removerGabarito(codigoGabarito);
    }

    @GetMapping("/buscar-gabarito/{codigoGabarito}")
    public ResponseEntity<?> buscarGabaritoPeloCodigo(@PathVariable Long codigoGabarito) {
        return _gabaritoService.selecionarGabaritoPorCodigo(codigoGabarito);
    }
}

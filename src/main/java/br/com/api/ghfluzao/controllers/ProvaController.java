package br.com.api.ghfluzao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.data.dto.prova.CreateProvaRequest;
import br.com.api.ghfluzao.data.dto.prova.ProvaEditRequest;
import br.com.api.ghfluzao.data.dto.prova.FindProvaResponse;
import br.com.api.ghfluzao.interfaces.ProvaServiceInterface;
import br.com.api.ghfluzao.models.Prova;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-v3/provas")
public class ProvaController {
    
    @Autowired
    private ProvaServiceInterface  _provaService;

    @PostMapping("")
    public ResponseEntity<String> criarProva(@RequestBody CreateProvaRequest request){

        _provaService.criarProva(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Prova criada com sucesso");
    }

    @GetMapping("")
    public List<FindProvaResponse> listar() {
        return _provaService.listar();
    }

    
    @PutMapping("/editar-prova/{provaCodigo}")
    public ResponseEntity<?> editarProva(@PathVariable Long provaCodigo, @RequestBody ProvaEditRequest request){
        return _provaService.editarProva(request, provaCodigo);
    }
    
    @DeleteMapping("/deletar-prova/{codigoProva}")
    public ResponseEntity<?> removerProva(@PathVariable Long codigoProva){
        return _provaService.removerProva(codigoProva);
    }
    
    @GetMapping("/buscar-prova/{codigoProva}")
    public ResponseEntity<?> buscarProvaPeloCodigo(@PathVariable Long codigoProva){
        return _provaService.selecionarProvaPorCodigo(codigoProva);
    }
    
    @GetMapping("/buscar-por-curso")
    public ResponseEntity<List<FindProvaResponse>> buscarPorCodigoCurso(@RequestParam("codigoCurso") Long codigoCurso) {
        List<FindProvaResponse> provas = _provaService.buscarPorCodigoCurso(codigoCurso);
        return ResponseEntity.ok(provas);
    }
    
    @GetMapping("/buscar-por-ano")
    public ResponseEntity<List<FindProvaResponse>> buscarPorAno(@RequestParam("ano") Integer ano) {
        List<FindProvaResponse> provas = _provaService.buscarPorAno(ano);
        return ResponseEntity.ok(provas);
    }
    
    @GetMapping("/montar-prova")
    public Optional<Prova> montarProva(@RequestParam("montarProva") Long codigoProva){
        return _provaService.montarProva(codigoProva);
    }
    
    @PutMapping("/aprovar-prova")
    public ResponseEntity<FindProvaResponse> aprovaProva(@RequestParam("codigoProva") Long codigoProva){
        return _provaService.aprovarProva(codigoProva);
    }
    
    @PutMapping("/recusar-prova")
    public ResponseEntity<FindProvaResponse> recusarProva(@RequestParam("codigoProva") Long codigoProva){
        return _provaService.recusarProva(codigoProva);
    }

    @PutMapping("/suspender-prova")
    public ResponseEntity<FindProvaResponse> suspenderProva(@RequestParam("codigoProva") Long codigoProva){
        return _provaService.suspenderProva(codigoProva);
    }

    @PutMapping("/revisar-prova")
    public ResponseEntity<FindProvaResponse> revisarProva(@RequestParam("codigoProva") Long codigoProva){
        return _provaService.revisarProva(codigoProva);
    }

    @PutMapping("/esperar-prova")
    public ResponseEntity<FindProvaResponse> esperarProva(@RequestParam("codigoProva") Long codigoProva){
        return _provaService.esperarProva(codigoProva);
    }

    @PutMapping("/aplicar-prova/{codigoProva}")
    public ResponseEntity<FindProvaResponse> aplicarProva(@PathVariable Long codigoProva){
        return _provaService.aplicarProva(codigoProva);
    }
}

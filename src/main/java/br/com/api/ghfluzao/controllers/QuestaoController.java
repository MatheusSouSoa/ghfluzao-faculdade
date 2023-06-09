package br.com.api.ghfluzao.controllers;

import java.util.List;

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

import br.com.api.ghfluzao.data.dto.questao.CreateQuestaoRequest;
import br.com.api.ghfluzao.data.dto.questao.FindQuestaoResponse;
import br.com.api.ghfluzao.interfaces.QuestaoServiceInterface;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api-v3/questoes")
public class QuestaoController {
    
    @Autowired
    private QuestaoServiceInterface _questaoService;

    @GetMapping("")
    public List<FindQuestaoResponse> Listar(){
        return _questaoService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarQuestao(@RequestBody CreateQuestaoRequest request){
        _questaoService.criarQuestao(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Assunto criado com sucesso!");
    }

    @PutMapping("/editar-questao/{questaoCodigo}")
    public ResponseEntity<?> editarQuestao(@PathVariable Long questaoCodigo, @RequestBody CreateQuestaoRequest request) {
        return _questaoService.editarQuestao(request, questaoCodigo);
    }

    @DeleteMapping("/deletar-questao/{codigoQuestao}")
    public ResponseEntity<?> removerQuestao(@PathVariable Long codigoQuestao) {
        return _questaoService.removerQuestao(codigoQuestao);
    }

    @GetMapping("/buscar-questao/{codigoQuestao}")
    public ResponseEntity<?> buscarQuestaoPeloCodigo(@PathVariable Long codigoQuestao) {
        return _questaoService.selecionarQuestaoPorCodigo(codigoQuestao);
    }

    @GetMapping("/questao-por-prova/{codigoProva}")
    public ResponseEntity<List<FindQuestaoResponse>> buscarQuestaoPorProva(@PathVariable Long codigoProva) {
            List<FindQuestaoResponse> questoes = _questaoService.findByCodigo_prova(codigoProva);
            return ResponseEntity.ok(questoes);
    }

    @PutMapping("/aprovar-questao")
    public ResponseEntity<FindQuestaoResponse> aprovaQuestao(@RequestParam("codigoQuestao") Long codigoQuestao) {
        return _questaoService.aprovarQuestao(codigoQuestao);
    }

    @PutMapping("/recusar-questao")
    public ResponseEntity<FindQuestaoResponse> recusarProva(@RequestParam("codigoQuestao") Long codigoQuestao) {
        return _questaoService.recusarQuestao(codigoQuestao);
    }

    @PutMapping("/suspender-questao")
    public ResponseEntity<FindQuestaoResponse> suspenderQuestao(@RequestParam("codigoQuestao") Long codigoQuestao) {
        return _questaoService.suspenderQuestao(codigoQuestao);
    }

    @PutMapping("/revisar-questao")
    public ResponseEntity<FindQuestaoResponse> revisarQuestao(@RequestParam("codigoQuestao") Long codigoQuestao) {
        return _questaoService.revisarQuestao(codigoQuestao);
    }

    @PutMapping("/validar-questao")
    public ResponseEntity<FindQuestaoResponse> validarQuestao(@RequestParam("codigoQuestao") Long codigoQuestao) {
        return _questaoService.aprontarQuestao(codigoQuestao);
    }

    @PutMapping("/esperar-questao")
    public ResponseEntity<FindQuestaoResponse> esperarQuestao(@RequestParam("codigoQuestao") Long codigoQuestao) {
        return _questaoService.esperarQuestao(codigoQuestao);
    }
}

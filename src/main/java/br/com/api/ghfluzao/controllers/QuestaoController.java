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

import br.com.api.ghfluzao.data.dto.questao.CreateQuestaoRequest;
import br.com.api.ghfluzao.interfaces.QuestaoServiceInterface;
import br.com.api.ghfluzao.models.Questao;

@RestController
@RequestMapping("/api-v1/questoes")
public class QuestaoController {
    
    @Autowired
    private QuestaoServiceInterface _questaoService;

    @GetMapping("")
    public Iterable<Questao> Listar(){
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
}

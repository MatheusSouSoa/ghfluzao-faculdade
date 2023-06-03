package br.com.api.ghfluzao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ghfluzao.models.Questao;
import br.com.api.ghfluzao.services.questao.CreateQuestaoRequest;
import br.com.api.ghfluzao.services.questao.IQuestaoService;

@RestController
@RequestMapping("/api-v1/questoes")
public class QuestaoController {
    
    @Autowired
    private IQuestaoService _questaoService;

    @GetMapping("")
    public Iterable<Questao> Listar(){
        return _questaoService.listar();
    }

    @PostMapping("")
    public ResponseEntity<String> criarQuestao(@RequestBody CreateQuestaoRequest request){
        _questaoService.criarQuestao(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Assunto criado com sucesso!");
    }
}
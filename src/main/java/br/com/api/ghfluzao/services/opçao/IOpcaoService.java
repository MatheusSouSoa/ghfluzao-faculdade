package br.com.api.ghfluzao.services.opçao;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Opcao;

public interface IOpcaoService {
    ResponseEntity<?> criarOpcao(CreateOpcaoRequest request);
    Opcao validarOpcao(Long codigo);
    Iterable<Opcao> listar();
}

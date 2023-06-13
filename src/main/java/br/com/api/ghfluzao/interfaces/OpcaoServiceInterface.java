package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.opçao.CreateOpcaoRequest;
import br.com.api.ghfluzao.models.Opcao;

public interface OpcaoServiceInterface {
    ResponseEntity<?> criarOpcao(CreateOpcaoRequest request);
    Opcao validarOpcao(Long codigo);
    Iterable<Opcao> listar();
    ResponseEntity<?> editarOpcao(CreateOpcaoRequest request, Long opcaoCodigo);
    ResponseEntity<?> removerOpcao(Long opcaoCodigo);
    ResponseEntity<?> selecionarOpcaoPorCodigo(Long codigo);
    
}

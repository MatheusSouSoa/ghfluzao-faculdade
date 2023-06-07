package br.com.api.ghfluzao.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.assunto.CreateAssuntoRequest;
import br.com.api.ghfluzao.data.dto.assunto.FindAssuntoResponse;
import br.com.api.ghfluzao.models.Assunto;

public interface AssuntoServiceInterface {

    ResponseEntity<?> criarAssunto(CreateAssuntoRequest request);
    Assunto validarAssunto(Long assuntoCodigo);
    List<FindAssuntoResponse> listar();
    ResponseEntity<?> selecionarAssuntoPorCodigo(Long codigo);
    ResponseEntity<?> removerAssunto(Long assuntoCodigo);
    ResponseEntity<?> editarAssunto(CreateAssuntoRequest request, Long assuntoCodigo);

}

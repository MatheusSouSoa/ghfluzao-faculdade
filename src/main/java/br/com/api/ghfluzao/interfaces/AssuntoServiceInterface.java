package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.assunto.CreateAssuntoRequest;
import br.com.api.ghfluzao.models.Assunto;

public interface AssuntoServiceInterface {

    ResponseEntity<?> criarAssunto(CreateAssuntoRequest request);
    Assunto validarAssunto(Long assuntoCodigo);
    Iterable<Assunto> listar();

}

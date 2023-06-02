package br.com.api.ghfluzao.services.assunto;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Assunto;

public interface IAssuntoService {

    ResponseEntity<?> criarAssunto(CreateAssuntoRequest request);
    Assunto validarAssunto(String assuntoNome);
    Iterable<Assunto> listar();

}

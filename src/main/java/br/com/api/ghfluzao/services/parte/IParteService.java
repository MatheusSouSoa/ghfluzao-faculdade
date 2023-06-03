package br.com.api.ghfluzao.services.parte;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Parte;

public interface IParteService {

    Iterable<Parte> listar();
    Parte validarParte(Long parteCodigo);
    ResponseEntity<?> criarParte(CreateParteRequest request);
}

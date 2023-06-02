package br.com.api.ghfluzao.services.provas;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Prova;

public interface IProvasService {
  
    ResponseEntity<?> criarProva(CreateProvaRequest request);
    Iterable<Prova> listar();
}

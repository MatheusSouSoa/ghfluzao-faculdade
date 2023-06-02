package br.com.api.ghfluzao.services.provas;

import org.springframework.http.ResponseEntity;

public interface IProvasService {
  
    ResponseEntity<?> criarProva(CreateProvaRequest request);
}

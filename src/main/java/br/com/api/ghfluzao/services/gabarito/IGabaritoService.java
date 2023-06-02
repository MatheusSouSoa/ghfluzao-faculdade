package br.com.api.ghfluzao.services.gabarito;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Gabarito;

public interface IGabaritoService {

    Iterable<Gabarito> listar();
    ResponseEntity<?> criarGabarito(CreateGabaritoRequest request);
}

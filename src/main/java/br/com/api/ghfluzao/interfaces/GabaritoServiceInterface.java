package br.com.api.ghfluzao.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.gabarito.CreateGabaritoRequest;
import br.com.api.ghfluzao.models.Gabarito;

public interface GabaritoServiceInterface {

    Iterable<Gabarito> listar();
    ResponseEntity<?> criarGabarito(CreateGabaritoRequest request);
    ResponseEntity<?> selecionarGabaritoPorCodigo(Long codigo);
    ResponseEntity<?> removerGabarito(Long gabaritoCodigo);
    ResponseEntity<?> editarGabarito(CreateGabaritoRequest request, Long gabaritoCodigo);
}

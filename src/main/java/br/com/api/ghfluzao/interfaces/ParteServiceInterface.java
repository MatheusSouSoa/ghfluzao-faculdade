package br.com.api.ghfluzao.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.parte.CreateParteRequest;
import br.com.api.ghfluzao.data.dto.parte.FindParteResponse;
import br.com.api.ghfluzao.models.Parte;

public interface ParteServiceInterface {

    List<FindParteResponse> listar();
    Parte validarParte(Long parteCodigo);
    ResponseEntity<?> criarParte(CreateParteRequest request);
    ResponseEntity<?> editarParte(CreateParteRequest request, Long ParteCodigo);
    ResponseEntity<?> removerParte(Long ParteCodigo);
    ResponseEntity<?> selecionarPartePorCodigo(Long codigo);
    
}

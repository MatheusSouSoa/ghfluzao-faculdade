package br.com.api.ghfluzao.services.prova;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.models.Prova;

public interface IProvaService {
  
    ResponseEntity<?> criarProva(CreateProvaRequest request);
    Iterable<Prova> listar();
    Prova validarProva(Long codigoProva);
    ResponseEntity<?> aplicarProva(Long codigoProva);
    ResponseEntity<?> editarProva(ProvaEditRequest request, Long cursoCodigo, Long provaCodigo);
    ResponseEntity<?> removerProva(Long provaCodigo);
}

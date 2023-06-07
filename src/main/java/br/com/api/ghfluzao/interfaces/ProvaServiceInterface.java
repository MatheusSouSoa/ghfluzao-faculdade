package br.com.api.ghfluzao.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.prova.CreateProvaRequest;
import br.com.api.ghfluzao.data.dto.prova.ProvaEditRequest;
import br.com.api.ghfluzao.data.dto.prova.FindProvaResponse;
import br.com.api.ghfluzao.models.Prova;

public interface ProvaServiceInterface {
  
    ResponseEntity<?> criarProva(CreateProvaRequest request);
    Optional<Prova> montarProva(Long codigo);
    List<FindProvaResponse> listar();
    Prova validarProva(Long codigoProva);
    ResponseEntity<?> editarProva(ProvaEditRequest request, Long provaCodigo);
    ResponseEntity<?> removerProva(Long provaCodigo);
    ResponseEntity<?> selecionarProvaPorCodigo(Long codigo);
    List<FindProvaResponse> buscarPorCodigoCurso(Long codigoCurso);
    List<FindProvaResponse> buscarPorAno(Integer codigoCurso);
    ResponseEntity<FindProvaResponse> aprovarProva(Long codigoProva);
    ResponseEntity<FindProvaResponse> recusarProva(Long codigoProva);
    ResponseEntity<FindProvaResponse> suspenderProva(Long codigoProva);
    ResponseEntity<FindProvaResponse> revisarProva(Long codigoProva);
    ResponseEntity<FindProvaResponse> esperarProva(Long codigoProva);
    ResponseEntity<FindProvaResponse> aplicarProva(Long codigoProva);
}

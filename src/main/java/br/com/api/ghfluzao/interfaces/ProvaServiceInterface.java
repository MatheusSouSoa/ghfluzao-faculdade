package br.com.api.ghfluzao.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.api.ghfluzao.data.dto.prova.CreateProvaRequest;
import br.com.api.ghfluzao.data.dto.prova.ProvaEditRequest;
import br.com.api.ghfluzao.data.dto.prova.SearchProvaResponse;
import br.com.api.ghfluzao.models.Prova;

public interface ProvaServiceInterface {
  
    ResponseEntity<?> criarProva(CreateProvaRequest request);
    List<SearchProvaResponse> listar();
    Prova validarProva(Long codigoProva);
    ResponseEntity<?> aplicarProva(Long codigoProva);
    ResponseEntity<?> editarProva(ProvaEditRequest request, Long provaCodigo);
    ResponseEntity<?> removerProva(Long provaCodigo);
    ResponseEntity<?> selecionarProvaPorCodigo(Long codigo);
    List<SearchProvaResponse> buscarPorCodigoCurso(Long codigoCurso);
    List<SearchProvaResponse> buscarPorAno(Integer codigoCurso);
}

package br.com.api.ghfluzao.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.prova.CreateProvaRequest;
import br.com.api.ghfluzao.data.dto.prova.ProvaEditRequest;
import br.com.api.ghfluzao.data.dto.prova.FindProvaResponse;
import br.com.api.ghfluzao.data.repositories.ProvaRepository;
import br.com.api.ghfluzao.enums.ProvaStatus;
import br.com.api.ghfluzao.interfaces.CursoServiceInterface;
import br.com.api.ghfluzao.interfaces.ProvaServiceInterface;
import br.com.api.ghfluzao.models.Prova;

@Service
public class ProvaServices implements ProvaServiceInterface {

    @Autowired
    private ProvaRepository _provaRepository;

    @Autowired
    private CursoServiceInterface _cursoService;

    public ResponseEntity<?> criarProva(CreateProvaRequest request){
        var curso = _cursoService.validarCurso(request.cursoNome);

        if(curso == null || request.getCursoNome().equals(null)){
            return new ResponseEntity<>("Curso invalido", HttpStatus.BAD_REQUEST);
        }
        var prova = new Prova(request.getAno(),curso.getCodigo());

        if(prova.getAno().equals(null) || prova.getAno().equals(0)){
            return new ResponseEntity<>("Ano invalido", HttpStatus.BAD_REQUEST);
        }

        prova.setSituacao(ProvaStatus.ANALISE);
        _provaRepository.save(prova);

         FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());

        return new ResponseEntity<>(provaResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<FindProvaResponse> aprovarProva(Long codigoProva){
        var prova = validarProva(codigoProva);

        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());
  
        if(prova.getSituacao().equals(ProvaStatus.APROVADO)){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        if(prova.getSituacao().equals(ProvaStatus.ANALISE) || prova.getSituacao().equals(ProvaStatus.SUSPENSO) || prova.getSituacao().equals(ProvaStatus.REVISADO) || prova.getSituacao().equals(null)){
            prova.setSituacao(ProvaStatus.APROVADO);
            provaResponse.setSituacao(ProvaStatus.APROVADO);
        }
        else{
            return ResponseEntity.badRequest().body(provaResponse);
        }

        _provaRepository.save(prova);

        return ResponseEntity.ok().body(provaResponse);
    }

    public ResponseEntity<FindProvaResponse> recusarProva(Long codigoProva){
        var prova = validarProva(codigoProva);

        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());
  
        if(prova.getSituacao().equals(ProvaStatus.RECUSADO)){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        if(prova.getSituacao().equals(ProvaStatus.ANALISE) || prova.getSituacao().equals(null)){
            prova.setSituacao(ProvaStatus.RECUSADO);
            provaResponse.setSituacao(ProvaStatus.RECUSADO);
        }
        else{
            return ResponseEntity.badRequest().body(provaResponse);
        }

        _provaRepository.save(prova);

        return ResponseEntity.ok().body(provaResponse);
    }

    public ResponseEntity<FindProvaResponse> suspenderProva(Long codigoProva){
        var prova = validarProva(codigoProva);

        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());
  
        if(prova.getSituacao().equals(ProvaStatus.SUSPENSO)){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        if(prova.getSituacao().equals(ProvaStatus.APROVADO) || prova.getSituacao().equals(ProvaStatus.REVISADO)){
            
            prova.setSituacao(ProvaStatus.SUSPENSO);
            provaResponse.setSituacao(ProvaStatus.SUSPENSO);
            _provaRepository.save(prova);
            return ResponseEntity.ok().body(provaResponse);

        }
        return ResponseEntity.badRequest().body(provaResponse);
    }

    public ResponseEntity<FindProvaResponse> revisarProva(Long codigoProva){
        var prova = validarProva(codigoProva);

        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());
  
        if(prova.getSituacao().equals(ProvaStatus.REVISADO)){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        if(prova.getSituacao().equals(ProvaStatus.APROVADO) || prova.getSituacao().equals(ProvaStatus.SUSPENSO)){

            prova.setSituacao(ProvaStatus.REVISADO);
            provaResponse.setSituacao(ProvaStatus.REVISADO);
            _provaRepository.save(prova);
            return ResponseEntity.ok().body(provaResponse);

        }
        return ResponseEntity.badRequest().body(provaResponse);
    }

    public ResponseEntity<FindProvaResponse> esperarProva(Long codigoProva){
        var prova = validarProva(codigoProva);

        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());
  
        if(prova.getSituacao().equals(ProvaStatus.PRONTA)){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        if(prova.getSituacao().equals(ProvaStatus.REVISADO)){

            prova.setSituacao(ProvaStatus.PRONTA);
            provaResponse.setSituacao(ProvaStatus.PRONTA);
            _provaRepository.save(prova);
            return ResponseEntity.ok().body(provaResponse);

        }
        return ResponseEntity.badRequest().body(provaResponse);
    }

    public ResponseEntity<FindProvaResponse> aplicarProva(Long codigoProva) {

        var prova = validarProva(codigoProva);

        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());
        
        if (prova.getSituacao().equals(ProvaStatus.APLICADA)) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        if (prova.getSituacao().equals(ProvaStatus.PRONTA)) {

            prova.setSituacao(ProvaStatus.APLICADA);
            provaResponse.setSituacao(ProvaStatus.APLICADA);
            if(prova.getData_aplicacao() == null){
                prova.setData_aplicacao(Calendar.getInstance());
                provaResponse.setDataAplicacao(prova.getData_aplicacao());
            }
            _provaRepository.save(prova);
            return ResponseEntity.ok().body(provaResponse);
        }
        return ResponseEntity.badRequest().body(provaResponse);
    }

    public Prova validarProva(Long codigoProva) {

        var prova = _provaRepository.findById(codigoProva).get();

        if (prova == null) {
            throw new EmptyResultDataAccessException(0);
        }

        return prova;
    }

    public Optional<Prova> montarProva(Long codigo){
        var prova = _provaRepository.findById(codigo);
        return prova;
    }

    public List<FindProvaResponse> listar() {
        List<Prova> provas = (List<Prova>) _provaRepository.findAll();
        return mapToSPRList(provas);
    }



    public ResponseEntity<?> editarProva(ProvaEditRequest request, Long provaCodigo) {

        var prova = validarProva(provaCodigo);

        if(prova == null) {
            return new ResponseEntity<>("Prova não existe. :(", HttpStatus.NOT_FOUND);
        }

        if(request.getAno() != null){
            prova.setAno(request.getAno());
        }
        if(request.getCodigoCurso() != null){
            var curso = _cursoService.validarCurso(request.codigoCurso);
            if (curso == null) {
                return new ResponseEntity<>("Curso não existe. :(", HttpStatus.NOT_FOUND);
            }
            if (!curso.getProvas().contains(prova)) {
                return ResponseEntity.notFound().build();
            }
            prova.setCodigo_curso(request.getCodigoCurso());
        }
        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao() ,prova.getCodigo_curso());

        _provaRepository.save(prova);

        return ResponseEntity.status(HttpStatus.OK).body(provaResponse);
    }

    public ResponseEntity<?> removerProva(Long provaCodigo){

        var prova = _provaRepository.findById(provaCodigo).get();

        if(prova == null){
            return new ResponseEntity<>("Prova não existe.", HttpStatus.NOT_FOUND);
        }

        _provaRepository.delete(prova);
        return new ResponseEntity<>("Prova removida com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarProvaPorCodigo(Long codigo){
        var prova = validarProva(codigo);

        if(prova == null){
            return new ResponseEntity<>("Prova não encontrada.", HttpStatus.NOT_FOUND);
        }
        FindProvaResponse provaResponse = new FindProvaResponse(prova.getCodigo(), prova.getAno(),prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao(), prova.getCodigo_curso());

        return ResponseEntity.status(HttpStatus.OK).body(provaResponse);
    }

    private List<FindProvaResponse> mapToSPRList(List<Prova> provas) {
        return provas.stream()
                .map(prova -> new FindProvaResponse(prova.getCodigo(), prova.getAno(), prova.getData_criacao(), prova.getData_aplicacao(), prova.getSituacao() ,prova.getCodigo_curso()))
                .collect(Collectors.toList());
    }

    public List<FindProvaResponse> buscarPorCodigoCurso(Long codigoCurso) {
        return mapToSPRList( _provaRepository.findByCodigo_curso(codigoCurso));
    }

    public List<FindProvaResponse> buscarPorAno(Integer ano) {
        return mapToSPRList( _provaRepository.findByAno(ano));
    }

}

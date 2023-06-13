package br.com.api.ghfluzao.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.dto.parte.CreateParteRequest;
import br.com.api.ghfluzao.data.dto.parte.SearchParteResponse;
import br.com.api.ghfluzao.data.repositories.ParteRepository;
import br.com.api.ghfluzao.interfaces.ParteServiceInterface;
import br.com.api.ghfluzao.models.Parte;

@Service
public class ParteService implements ParteServiceInterface{
    
    @Autowired
    private ParteRepository _parteRepository;

    public ResponseEntity<?> criarParte(CreateParteRequest request){
        var parte = new Parte(request.nome,request.peso_componente, request.peso_nota);

        if(parte.getNome().equals(null) || parte.getPeso_componente().equals(null) || parte.getPeso_nota().equals(null)){
            return new ResponseEntity<>("Parte invalida", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_parteRepository.save(parte), HttpStatus.CREATED);
    }

    public Parte validarParte(Long parteCodigo){
        Parte parte = _parteRepository.findById(parteCodigo).get();

        if(parte == null){
            throw new EmptyResultDataAccessException(0);
        }
        return parte;
    }

    public List<SearchParteResponse> listar(){
        List<Parte> partes = (List<Parte>) _parteRepository.findAll();
        return mapToSPRList(partes);
    }

    public ResponseEntity<?> editarParte(CreateParteRequest request, Long ParteCodigo) {

        var parte = _parteRepository.findById(ParteCodigo).get();

        if (parte == null) {
            return new ResponseEntity<>("Parte não existe. :(", HttpStatus.NOT_FOUND);
        }

        if (request.nome != null) {
            parte.setNome(request.nome);
        }
        if (request.peso_componente != null) {
            parte.setPeso_componente(request.peso_componente);
        }
        if(request.peso_nota != null) {
            parte.setPeso_nota(request.peso_nota);
        }
        SearchParteResponse parteResponse = new SearchParteResponse(parte.getCodigo() ,request.nome, request.peso_componente, request.peso_nota);

        _parteRepository.save(parte);

        return ResponseEntity.status(HttpStatus.OK).body(parteResponse);
    }

    public ResponseEntity<?> removerParte(Long ParteCodigo) {

        var parte = _parteRepository.findById(ParteCodigo).get();

        if (parte == null) {
            return new ResponseEntity<>("Parte não existe.", HttpStatus.NOT_FOUND);
        }

        _parteRepository.delete(parte);
        return new ResponseEntity<>("Parte removido com sucesso!", HttpStatus.OK);

    }

    public ResponseEntity<?> selecionarPartePorCodigo(Long codigo) {
        var parte = validarParte(codigo);

        if (parte == null) {
            return new ResponseEntity<>("Parte não encontrado.", HttpStatus.NOT_FOUND);
        }
        SearchParteResponse parteResponse = new SearchParteResponse(parte.getCodigo(), parte.getNome(), parte.getPeso_componente(), parte.getPeso_nota());

        return ResponseEntity.status(HttpStatus.CREATED).body(parteResponse);
    }

     private List<SearchParteResponse> mapToSPRList(List<Parte> partes) {
        return partes.stream()
                .map(parte -> new SearchParteResponse(parte.getCodigo(),parte.getNome(),parte.getPeso_componente(), parte.getPeso_nota()))
                .collect(Collectors.toList());
    }
}

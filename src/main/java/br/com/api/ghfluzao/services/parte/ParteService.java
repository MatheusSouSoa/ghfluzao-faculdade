package br.com.api.ghfluzao.services.parte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.ParteRepository;
import br.com.api.ghfluzao.models.Parte;

@Service
public class ParteService implements IParteService{
    
    @Autowired
    private ParteRepository _parteRepository;

    public ResponseEntity<?> criarParte(CreateParteRequest request){
        var parte = new Parte(request.nome,request.peso_componente, request.peso_nota);

        if(parte.getNome().equals(null) || parte.getPeso_componente().equals(null) || parte.getPeso_nota().equals(null)){
            return new ResponseEntity<>("Parte invalida", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_parteRepository.save(parte), HttpStatus.CREATED);
    }

    public Parte validarParte(String parteNome){
        Parte parte = _parteRepository.findByNome(parteNome).get(0);

        if(parte == null){
            throw new EmptyResultDataAccessException(parteNome, 0);
        }
        return parte;
    }

    public Iterable<Parte> listar(){
        return _parteRepository.findAll();
    }

}

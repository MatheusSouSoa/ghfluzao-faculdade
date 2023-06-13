package br.com.api.ghfluzao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.data.repositories.UsuarioTokensRepository;
import br.com.api.ghfluzao.interfaces.UsuariosTokensServiceInterface;
import br.com.api.ghfluzao.models.UsuarioTokens;

@Service
public class UsuarioTokensService implements UsuariosTokensServiceInterface{
    
    @Autowired
    private UsuarioTokensRepository _tokensRepository;

    public Long criarRegistroToken(Long codigo_usuario){
        var historico = new UsuarioTokens(codigo_usuario);
        var codigoToken = _tokensRepository.save(historico);
        return codigoToken.getId();
    }

    public UsuarioTokens setarToken(Long codigo, String token){
        try {
            var historico = _tokensRepository.findById(codigo).get();
            historico.setToken(token);
            _tokensRepository.save(historico);
            return historico;
        } catch (Exception e) {
            return null;
        }
    }

    public UsuarioTokens setarTokenFalse(Long codigo, String token){
        try {
            var historico = _tokensRepository.findById(codigo).get();
            historico.setValid(false);
            _tokensRepository.save(historico);
            return historico;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean verificarIdToken(Long codigotToken){
        try {
            var token = _tokensRepository.findById(codigotToken).get();
            if(token != null){
                if(token.isValid()){
                    return true;
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}

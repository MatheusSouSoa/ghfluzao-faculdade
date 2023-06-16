package br.com.api.ghfluzao.interfaces;

import br.com.api.ghfluzao.models.UsuarioTokens;

public interface UsuariosTokensServiceInterface {

    Long criarRegistroToken(Long codigo_usuario);
    UsuarioTokens setarToken(Long codigo, String token);
    boolean verificarIdToken(Long codigotToken) throws Exception;
    UsuarioTokens setarTokenFalse(Long codigo, String token);

}

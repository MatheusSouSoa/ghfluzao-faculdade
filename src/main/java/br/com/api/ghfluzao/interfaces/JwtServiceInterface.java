package br.com.api.ghfluzao.interfaces;

import br.com.api.ghfluzao.enums.RolesUsuarios;

public interface JwtServiceInterface {
    String generateToken(Long codigoUsuario);
    boolean isValidToken(String token, String userId);
    String getToken();
    String getUserId();
    boolean verificarRole(String userId, RolesUsuarios rotaRole);
}

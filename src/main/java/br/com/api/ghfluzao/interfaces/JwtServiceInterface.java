package br.com.api.ghfluzao.interfaces;

public interface JwtServiceInterface {
    String generateToken(Long codigoUsuario);
    boolean isValidToken(String token, String userId, int rotaRole);
    String getToken();
    String getUserId();
}

package br.com.api.ghfluzao.services;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.api.ghfluzao.enums.RolesUsuarios;
import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuariosTokensServiceInterface;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService implements JwtServiceInterface{

    @Value("${JWT.EXPIRATION}")
    private long EXPIRATION_TIME;
    @Value("${JWT.SECRET}")// SE N FOR, VOLTAR PARA FINAL E TRAZER A KEY DO APP.PROPERTIES
    private String KEY;

    @Autowired
    private UsuarioServiceInterface _usuarioServiceInterface;

    @Autowired
    private UsuariosTokensServiceInterface _tokensService;
    
    public String generateToken(Long codigoUsuario){

        var usuario = _usuarioServiceInterface.pegarUsuarioPorId(codigoUsuario);
        var historico = _tokensService.criarRegistroToken(codigoUsuario);
        
        Claims claims = Jwts.claims();
        claims.put("ROLE", usuario.getRole());
        claims.put("TOKEN_ID", historico);

        var token  = Jwts
                        .builder()
                        .setClaims(claims)
                        .setSubject(codigoUsuario.toString())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                        .compact();

        _tokensService.setarToken(historico, token);
                    
        return token;
    }

    public boolean isValidToken(String token, String userId)  {
        
        var sub = getClaims(token, Claims::getSubject);
        var tExpiration = getClaims(token, Claims::getExpiration);
        var tokenId = getClaim(token, "TOKEN_ID");

        if ((sub.equals(userId) && !tExpiration.before(new Date()) && _tokensService.verificarIdToken(Long.parseLong(tokenId)))){
            return true;
        }
        return false;
    }

    private <T> T getClaims(String token, Function<Claims, T> claimsResolver){
        var claims =    Jwts
                        .parserBuilder()
                        .setSigningKey(genSignInKey())
                        .build().parseClaimsJws(token)
                        .getBody();
        return claimsResolver.apply(claims);                        
    }

    private String getClaim(String token, String claimName) {
    Claims claims = Jwts.parserBuilder()
            .setSigningKey(genSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();

    Integer claimValue = claims.get(claimName, Integer.class);
    return claimValue != null ? claimValue.toString() : null;
}

    public boolean verificarRole(String userId, RolesUsuarios rotaRole){

        var usuario = _usuarioServiceInterface.pegarUsuarioPorId(Long.parseLong(userId));
        
        if(usuario.getRole().getValue() > rotaRole.getValue() ){
            return true;
        }
        return false;
    }

    private Key genSignInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }

    public String getToken() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getHeader("Authorization");
    }

    public String getUserId() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getHeader("RequestedBy");
    }

}

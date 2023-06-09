package br.com.api.ghfluzao.services;

import java.security.Key;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import br.com.api.ghfluzao.interfaces.UsuarioServiceInterface;
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
    
    public String generateToken(Long codigoUsuario){

        var usuario = _usuarioServiceInterface.pegarUsuarioPorId(codigoUsuario);
        
        Claims claims = Jwts.claims();
        claims.put("ROLE", usuario.getRole());
        
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(codigoUsuario.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token, String userId)  {
        var claims =    Jwts.parserBuilder()
                            .setSigningKey(genSignInKey())
                            .build().parseClaimsJws(token)
                            .getBody();

        var sub = claims.getSubject();
        var tExpiration = claims.getExpiration();
        
 

        return (sub.equals(userId) && !tExpiration.before(new Date()));
    }

    public boolean verificarRole(String userId, int rotaRole){

        var usuario = _usuarioServiceInterface.pegarUsuarioPorId(Long.parseLong(userId));
 
        if(usuario.getRole().getValue() > rotaRole ){
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
                .getHeader("UserId");
    }

}

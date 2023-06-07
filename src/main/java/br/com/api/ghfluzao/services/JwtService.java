package br.com.api.ghfluzao.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.api.ghfluzao.interfaces.JwtServiceInterface;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements JwtServiceInterface{

    private final long EXPIRATION_TIME = 10800000;
    @Value("${JWT_KEY}")// SE N FOR, VOLTAR PARA FINAL E TRAZER A KEY DO APP.PROPERTIES
    private String KEY;
    
    public String generateToken(Long codigoUsuario){
        return Jwts
                .builder()
                .setSubject(codigoUsuario.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key genSignInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }

}

package br.com.api.ghfluzao.data.dto.auth;

import lombok.Data;

@Data
public class AuthenticateResponse {
    
    public Long codigo;
    public String token;

}

package br.com.api.ghfluzao.data.dto.Auth;

import lombok.Data;

@Data
public class AuthenticateResponse {
    
    public Long codigo;
    public String token;

}

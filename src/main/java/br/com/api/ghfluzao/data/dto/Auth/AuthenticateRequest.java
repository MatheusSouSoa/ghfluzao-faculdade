package br.com.api.ghfluzao.data.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateRequest {
    
    public String email;
    public String password;

}

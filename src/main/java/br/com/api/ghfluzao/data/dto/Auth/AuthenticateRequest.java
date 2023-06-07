package br.com.api.ghfluzao.data.dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateRequest {
    
    public String email;
    public String password;

}

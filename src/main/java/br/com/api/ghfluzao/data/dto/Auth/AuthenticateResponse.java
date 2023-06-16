package br.com.api.ghfluzao.data.dto.auth;

import br.com.api.ghfluzao.enums.RolesUsuarios;
import lombok.Data;

@Data
public class AuthenticateResponse {
    
    public Long codigo;
    public String token;
    public RolesUsuarios role;

}

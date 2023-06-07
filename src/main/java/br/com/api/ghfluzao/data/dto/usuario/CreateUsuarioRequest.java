package br.com.api.ghfluzao.data.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUsuarioRequest {
    
    public String nome;
    public String email;
    public String senha;

}

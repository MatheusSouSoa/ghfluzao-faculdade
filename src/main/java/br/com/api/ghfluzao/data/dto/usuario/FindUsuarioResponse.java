package br.com.api.ghfluzao.data.dto.usuario;

import br.com.api.ghfluzao.enums.RolesUsuarios;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUsuarioResponse {
    
    public Long id;
    public String nome;
    public String email;
    public RolesUsuarios role;

    public FindUsuarioResponse(Long codigo, String nome, String email){
        this.id = codigo;
        this.nome = nome;
        this.email = email;
    }

}

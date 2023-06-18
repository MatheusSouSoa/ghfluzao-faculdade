package br.com.api.ghfluzao.data.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUsuarioResponse {
    
    public Long id;
    public String email;
    public String nome;
}
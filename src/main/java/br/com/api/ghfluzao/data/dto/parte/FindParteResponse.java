package br.com.api.ghfluzao.data.dto.parte;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindParteResponse {
    
    public Long codigo;
    public String nome;
    public Integer peso_componente;
    public Integer peso_nota;

}

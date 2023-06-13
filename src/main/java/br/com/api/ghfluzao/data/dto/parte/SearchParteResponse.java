package br.com.api.ghfluzao.data.dto.parte;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchParteResponse {
    
    public Long codigo;
    public String nome;
    public Integer peso_componente;
    public Integer peso_nota;

}

package br.com.api.ghfluzao.data.dto.prova;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CreateProvaRequest {
    
    public String cursoNome;
    public Integer ano;

}

package br.com.api.ghfluzao.services.provas;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CreateProvaRequest {
    
    public int ano;
    public String cursoNome;

}

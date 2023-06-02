package br.com.api.ghfluzao.services.prova;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CreateProvaRequest {
    
    public int ano;
    public String cursoNome;

}

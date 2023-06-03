package br.com.api.ghfluzao.services.prova;

import br.com.api.ghfluzao.models.Prova;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CreateProvaRequest {
    
    public String cursoNome;
    public String assuntoNome;
    public String parteNome;
    public Prova prova;


}

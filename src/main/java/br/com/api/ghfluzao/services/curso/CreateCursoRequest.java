package br.com.api.ghfluzao.services.curso;

import br.com.api.ghfluzao.models.Prova;
import lombok.Data;

@Data
public class CreateCursoRequest {

    public String nome;
    public Prova prova;
}

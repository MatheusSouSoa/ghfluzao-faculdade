package br.com.api.ghfluzao.services.curso;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchCursoResponse {
    
    public Long codigoCurso;
    public String nomeCurso;
}

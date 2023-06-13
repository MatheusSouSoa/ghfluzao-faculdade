package br.com.api.ghfluzao.data.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchCursoResponse {
    
    public Long codigoCurso;
    public String nomeCurso;
}

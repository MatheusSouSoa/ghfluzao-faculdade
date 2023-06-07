package br.com.api.ghfluzao.data.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FindCursoResponse {
    
    public Long codigoCurso;
    public String nomeCurso;
}

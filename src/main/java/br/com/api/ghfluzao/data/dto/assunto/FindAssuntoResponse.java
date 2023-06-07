package br.com.api.ghfluzao.data.dto.assunto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAssuntoResponse {
    
    public Long assuntoCodigo;
    public String materia;
}

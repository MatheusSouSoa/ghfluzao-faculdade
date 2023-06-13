package br.com.api.ghfluzao.data.dto.gabarito;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchGabaritoResponse {
    
    public Long gabaritoCodigo;
    public String resposta;
    public Long questaoCodigo;
}

package br.com.api.ghfluzao.data.dto.opçao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindOpcaoResponse {
    
    public Long codigoOpcao;
    public Character letra;
    public String texto;
    public Long questaoCodigo;

}

package br.com.api.ghfluzao.data.dto.gabarito;

import lombok.Data;

@Data
public class CreateGabaritoRequest {

    public String resposta;
    public Long questaoCodigo;
}

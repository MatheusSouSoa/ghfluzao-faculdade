package br.com.api.ghfluzao.services.gabarito;

import lombok.Data;

@Data
public class CreateGabaritoRequest {

    public String resposta;
    public Long questaoCodigo;
}

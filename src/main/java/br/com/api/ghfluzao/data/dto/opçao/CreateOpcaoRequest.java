package br.com.api.ghfluzao.data.dto.opçao;

import lombok.Data;

@Data
public class CreateOpcaoRequest {

    public Character letra;
    public String texto;
    public Long questaoCodigo;
}

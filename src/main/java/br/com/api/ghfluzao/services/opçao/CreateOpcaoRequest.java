package br.com.api.ghfluzao.services.opçao;

import lombok.Data;

@Data
public class CreateOpcaoRequest {

    public Character letra;
    public String texto;
    public Long codigo_questao;
}

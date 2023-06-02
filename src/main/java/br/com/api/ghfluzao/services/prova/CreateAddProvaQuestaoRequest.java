package br.com.api.ghfluzao.services.prova;

import lombok.Data;

@Data
public class CreateAddProvaQuestaoRequest {

    public Long codigoProva;
    public String enunciado;
}

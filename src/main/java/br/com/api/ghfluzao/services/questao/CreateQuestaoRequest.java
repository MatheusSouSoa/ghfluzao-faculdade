package br.com.api.ghfluzao.services.questao;

import lombok.Data;

@Data
public class CreateQuestaoRequest {

    public Long codigoParte;
    private Long codigoAssunto;
    public Long codigoProva;
    public String enunciado;
}

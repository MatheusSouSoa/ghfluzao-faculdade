package br.com.api.ghfluzao.services.questao;

import lombok.Data;

@Data
public class CreateQuestaoRequest {

    public Long codigoParte;
    public Long codigoAssunto;
    public Long codigoProva;
    public String enunciado;
    public Integer numeroQuestao;
    public String urlFigura;
}

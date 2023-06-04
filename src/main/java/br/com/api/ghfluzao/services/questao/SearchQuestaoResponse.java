package br.com.api.ghfluzao.services.questao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchQuestaoResponse {

    public Long codigoQuestao;
    public Long codigoParte;
    public Long codigoAssunto;
    public Long codigoProva;
    public String enunciado;
    public Integer numeroQuestao;
    public String urlFigura;
    
}

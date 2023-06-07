package br.com.api.ghfluzao.data.dto.questao;

import br.com.api.ghfluzao.enums.QuestaoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindQuestaoResponse {

    public Long codigoQuestao;
    public Long codigoParte;
    public Long codigoAssunto;
    public Long codigoProva;
    public String enunciado;
    public Integer numeroQuestao;
    public String urlFigura;
    public QuestaoStatus situacao;
    
}

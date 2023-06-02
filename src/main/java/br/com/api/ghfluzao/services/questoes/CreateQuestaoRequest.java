package br.com.api.ghfluzao.services.questoes;

import lombok.Data;

@Data
public class CreateQuestaoRequest {

    public String enunciado;
    public Long codigo;
}

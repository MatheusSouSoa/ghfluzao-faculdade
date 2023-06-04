package br.com.api.ghfluzao.data.dto.prova;

import java.util.List;

import br.com.api.ghfluzao.data.dto.questao.SearchQuestaoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvaDTOQuestao {
    private List<SearchQuestaoResponse> questoes;
}

package br.com.api.ghfluzao.data.dto.prova;

import java.util.Calendar;

import br.com.api.ghfluzao.enums.ProvaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchProvaResponse {
    
    public Long codigoProva;
    public Integer ano;
    public Calendar dataCriacao;
    public Calendar dataAplicacao;
    public ProvaStatus situacao;
    public Long codigoCurso;

}

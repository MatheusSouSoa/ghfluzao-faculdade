package br.com.api.ghfluzao.services.prova;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchProvaResponse {
    
    public Long codigoProva;
    public Integer ano;
    public Calendar dataCriacao;
    public Calendar dataAplicacao;
    public Long codigoCurso;

}

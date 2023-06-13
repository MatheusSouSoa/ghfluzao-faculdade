package br.com.api.ghfluzao.data.dto.parte;

import lombok.Data;

@Data
public class CreateParteRequest {

    public String nome;
    public Integer peso_componente;
    public Integer peso_nota;

}

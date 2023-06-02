package br.com.api.ghfluzao.services.parte;

import lombok.Data;

@Data
public class CreateParteRequest {

    public String nome;
    public int peso_componente;
    public int peso_nota;

}

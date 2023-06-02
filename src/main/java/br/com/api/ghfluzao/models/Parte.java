package br.com.api.ghfluzao.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "partes")
@Data
public class Parte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  private String nome;
  private Integer peso_componente;
  private Integer peso_nota;

  public Parte(String nome, int peso_componente, int peso_nota){
    this.nome = nome;
    this.peso_componente = peso_componente;
    this.peso_nota = peso_nota;
  }

  @OneToMany
  @JoinColumn(name = "codigo_parte")
  private List<Questao> questoes;
  
}

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

@Entity
@Table(name = "partes")
@Data
public class Parte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  private String nome;
  private int peso_componente;
  private int peso_nota;

  @OneToMany
  @JoinColumn(name = "codigo_parte")
  private List<Questao> questoes;
  
}

package br.com.api.ghfluzao.models;

import java.util.Calendar;
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
@Table(name = "provas")
@Data
public class Prova {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  private Integer ano;
  private Calendar data_criacao = Calendar.getInstance();
  private Calendar data_aplicacao;

  public Prova(int ano){
    this.ano = ano;
  }

  @OneToMany
  @JoinColumn(name = "codigo_prova")
  private List<Questao> questoes;
}

package br.com.api.ghfluzao.models;

import java.util.Calendar;
import java.util.List;

import br.com.api.ghfluzao.enums.ProvaStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
  private Long codigo_curso;
  @Enumerated(EnumType.STRING)
  private ProvaStatus situacao;

  public Prova(int ano, Long codigo_curso){
    this.ano = ano;
    this.codigo_curso = codigo_curso;
  }

  public Prova(){
    
  }

  @OneToMany
  @JoinColumn(name = "codigo_prova")
  private List<Questao> questoes;
}

package br.com.api.ghfluzao.models;

import java.util.List;

import br.com.api.ghfluzao.enums.QuestaoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "questoes")
@Data
public class Questao {
  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  @Column(length = 4000)
  private String enunciado;
  private int numero;
  @Column(length = 4000)
  private String figura;
  private Long codigo_prova;
  private Long codigo_parte;
  private Long codigo_assunto;
  @Enumerated(EnumType.STRING)
  private QuestaoStatus situacao;

  public Questao(String enunciado, int numeroQuestao, String urlFigura  ,Long codigo_prova, Long codigo_parte, Long codigo_assunto) {
    this.enunciado = enunciado;
    this.numero = numeroQuestao;
    this.figura = urlFigura;
    this.codigo_prova = codigo_prova;
    this.codigo_parte = codigo_parte;
    this.codigo_assunto = codigo_assunto;
  }

  @OneToMany
  @JoinColumn(name = "codigo_questao")
  private List<Opcao> opcoes;

  @OneToMany
  @JoinColumn(name = "codigo_questao")
  private List<Gabarito> gabaritos; 
}

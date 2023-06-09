package br.com.api.ghfluzao.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "gabaritos")
@Data
public class Gabarito {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  @Column(length = 4000)
  private String resposta;
  private Long codigo_questao;

  public Gabarito(String resposta, Long codigoQuestao){
    this.resposta = resposta;
    this.codigo_questao = codigoQuestao;
  }
}
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
@Table(name = "opcoes")
@Data
public class Opcao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  private Character letra;  
  @Column(length = 4000)
  private String texto;
  private Long codigo_questao;
  
  public Opcao(Character letra, String texto, Long codigo_questao) {
    this.letra = letra;
    this.texto = texto;
    this.codigo_questao = codigo_questao;
  }
}

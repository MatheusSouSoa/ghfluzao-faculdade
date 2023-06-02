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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "cursos")
@Data
public class Curso {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  private String nome;

  @OneToMany
  @JoinColumn(name = "codigo_curso")
  private List<Prova> provas;

  
}

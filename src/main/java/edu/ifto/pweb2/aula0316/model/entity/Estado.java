package edu.ifto.pweb2.aula0316.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Estado implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String nome;

  private String sigla;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

  @Override
  public String toString() {
    return "Estado{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", sigla='" + sigla + '\'' +
        '}';
  }
}

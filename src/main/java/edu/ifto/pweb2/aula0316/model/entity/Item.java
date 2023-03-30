package edu.ifto.pweb2.aula0316.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;
  private Produto produto;
  private Double quantidade;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public Double getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Double quantidade) {
    this.quantidade = quantidade;
  }

  public double total() {
    return this.produto.getValor()*this.quantidade;
  }

  public String dados() {
    return this.toString();
  }
}

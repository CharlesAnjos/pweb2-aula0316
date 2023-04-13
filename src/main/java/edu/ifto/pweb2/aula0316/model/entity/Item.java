package edu.ifto.pweb2.aula0316.model.entity;

import jakarta.persistence.*;

@Entity
public class Item {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @OneToOne
  @JoinColumn(name = "id_produto")
  private Produto produto;
  @ManyToOne
  @JoinColumn(name = "id_venda")
  private Venda venda;
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

  public Venda getVenda() {
    return venda;
  }

  public void setVenda(Venda venda) {
    this.venda = venda;
  }

  public double total() {
    return this.produto.getValor()*this.quantidade;
  }

  public String dados() {
    return this.toString();
  }
}

package edu.ifto.pweb2.aula0316.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "produto")
public class Produto implements Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String descricao;
}

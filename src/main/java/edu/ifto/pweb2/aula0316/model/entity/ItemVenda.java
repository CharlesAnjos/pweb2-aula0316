package edu.ifto.pweb2.aula0316.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class ItemVenda implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private double qtd;

    public double total(){
        double valorTotal = 0.00;
        return valorTotal;
    }
}

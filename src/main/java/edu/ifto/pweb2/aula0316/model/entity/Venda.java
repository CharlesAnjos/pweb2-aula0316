package edu.ifto.pweb2.aula0316.model.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Scope("session")
@Component
public class Venda implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    private LocalDateTime data;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    private List<Item> items = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double total(){
        double valorTotal = 0.0;
        if(items != null){
            for (Item item : items) {
                valorTotal += item.total();
            }
        }
        return valorTotal;
    }

    public String dados() {
        return this.toString();
    }



}

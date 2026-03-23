package dev.java10x.CadastroDeNinjas;

import jakarta.persistence.*;

// Entity ele transforma uma classe em uma entidade do DB
// JPS = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String nome;
    String email;
    int idade;

    public NinjaModel() {}

    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.Controller.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

// @Entity = transforma essa classe em uma tabela no banco de dados
@Entity

// @Table = define o nome da tabela. Sem isso, viraria "MissoesModel"
@Table(name = "tb_missoes")
public class MissoesModel {

    // @Id = chave primária da tabela
    // @GeneratedValue = banco gera o id automaticamente (1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;        // nome da missão
    private String dificuldade; // rank da missão (D, C, B, A, S)

    // @OneToMany = UMA missão pode ter MUITOS ninjas
    // Lê assim: "um (missão) para muitos (ninjas)"
    // mappedBy = aponta para o atributo "missoes" que está no NinjaModel
    // É o NinjaModel quem tem o @JoinColumn — aqui a gente só espelha a relação
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjas;

}
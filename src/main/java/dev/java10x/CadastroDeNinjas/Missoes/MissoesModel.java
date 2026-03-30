package dev.java10x.CadastroDeNinjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// @Entity = transforma essa classe em uma tabela no banco de dados
@Entity

// @Table = define o nome da tabela. Sem isso, viraria "MissoesModel"
@Table(name = "tb_missoes")

// @NoArgsConstructor = gera o construtor vazio automaticamente
// Obrigatório para o Hibernate reconstruir objetos ao buscar do banco
@NoArgsConstructor

// @AllArgsConstructor = gera o construtor com todos os atributos automaticamente
// Se você adicionar um novo atributo, o Lombok já inclui ele aqui sozinho
@AllArgsConstructor

// @Data = gera todos os Getters e Setters automaticamente
// Também gera toString() e equals()/hashCode() por baixo dos panos
@Data
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
    @JsonIgnore
    private List<NinjaModel> ninjas;

}
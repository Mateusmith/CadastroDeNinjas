package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// @Entity = transforma essa classe em uma tabela no banco de dados
// JPA (contrato) → Hibernate (executa) → cria o SQL automaticamente
@Entity

// @Table = define o nome da tabela. Sem isso, viraria "NinjaModel"
@Table(name = "tb_cadastro")

// @NoArgsConstructor = gera o construtor vazio automaticamente
// Obrigatório para o Hibernate reconstruir objetos ao buscar do banco
@NoArgsConstructor

// @AllArgsConstructor = gera o construtor com todos os atributos automaticamente
// Se você adicionar um novo atributo, o Lombok já inclui ele aqui sozinho
@AllArgsConstructor

// @Data = gera todos os Getters e Setters automaticamente
// Também gera toString() e equals()/hashCode() por baixo dos panos
@Data
@ToString(exclude = "missoes")
public class NinjaModel {

    // @Id = chave primária — identifica cada linha da tabela de forma única
    // @GeneratedValue = banco gera o id automaticamente (1, 2, 3...)
    // long em vez de int → int estoura em sistemas grandes, long não
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;   // vira coluna VARCHAR no banco

    // @Column(unique = true) = garante que dois ninjas não podem ter o mesmo email
    // Use unique = true em dados que são únicos por natureza: email, CPF, RG, passaporte
    // NÃO use em nome ou senha — pessoas podem ter nomes iguais e senhas iguais
    @Column(unique = true)
    private String email;

    @Column (name = "img_url")
    private String imgUrl;

    @Column (name = "rank")
    private String rank;

    @Column (name = "idade")
    private int idade;     // vira coluna INT no banco

    // @ManyToOne = MUITOS ninjas podem ter UMA única missão
    // Lê assim: "muitos (ninjas) para um (missão)"
    // Essa anotação fica no lado que tem a restrição — o ninja só pode ter 1 missão por vez
    @ManyToOne
    // @JoinColumn = cria a coluna da chave estrangeira (Foreign Key) nessa tabela
    // name = nome da coluna que vai aparecer no banco: missoes_id
    // Ela guarda o id da missão atrelada àquele ninja
    @JoinColumn(name = "missoes_id")
    private MissoesModel missoes;

}
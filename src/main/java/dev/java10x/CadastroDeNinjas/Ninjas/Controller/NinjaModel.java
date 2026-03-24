package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;

// @Entity = transforma essa classe em uma tabela no banco de dados
// JPA (contrato) → Hibernate (executa) → cria o SQL automaticamente
@Entity

// @Table = define o nome da tabela. Sem isso, viraria "NinjaModel"
@Table(name = "tb_cadastro")
public class NinjaModel {

    // @Id = chave primária — identifica cada linha da tabela de forma única
    // @GeneratedValue = banco gera o id automaticamente (1, 2, 3...)
    // long em vez de int → int estoura em sistemas grandes, long não
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;   // vira coluna VARCHAR no banco
    private String email;  // dica futura: @Column(unique = true)
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

    // NoArgs Constructor — obrigatório para o Hibernate reconstruir objetos ao buscar do banco
    public NinjaModel() {}

    // AllArgs Constructor — sem o id, pois o banco gera automaticamente. Nunca passe id manualmente
    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    // Getters e Setters — atributos são private, só acessados por aqui
    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getIdade() { return idade; }
    public MissoesModel getMissoes() { return missoes; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setMissoes(MissoesModel missoes) { this.missoes = missoes; }

    // Sem isso, System.out.println(ninja) mostraria: NinjaModel@3a2f1b
    @Override
    public String toString() {
        return "NinjaModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", missoes=" + missoes +
                '}';
    }
}
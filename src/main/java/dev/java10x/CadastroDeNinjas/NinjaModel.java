package dev.java10x.CadastroDeNinjas;

import jakarta.persistence.*;

// @Entity = transforma essa classe em uma tabela no banco de dados
// JPA (contrato) → Hibernate (executa) → cria o SQL automaticamente
@Entity

// @Table = define o nome da tabela. Sem isso, viraria "NinjaModel"
@Table(name = "tb_cadastro")
public class NinjaModel {

    // @Id = chave primária da tabela
    // long em vez de int → int estoura em sistemas grandes, long não
    @Id
    // @GeneratedValue = banco gera o id automaticamente (1, 2, 3...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;   // vira coluna VARCHAR no banco
    private String email;  // dica futura: @Column(unique = true)
    private int idade;     // vira coluna INT no banco

    // Obrigatório para o Hibernate reconstruir objetos ao buscar do banco
    public NinjaModel() {}

    // Sem o id — o banco gera, nunca passe id no construtor
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

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setIdade(int idade) { this.idade = idade; }

    // Sem isso, System.out.println(ninja) mostraria: NinjaModel@3a2f1b
    @Override
    public String toString() {
        return "NinjaModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                '}';
    }
}
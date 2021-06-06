package br.senai.sc.edu.aula_spring_data.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
//@Entity(name="eventos") *** Para usar um nome da tabela diferente da classe
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column
    private String nome;

    @OneToMany(mappedBy = "evento")
    List<Inscricao> inscricoes;

    public Evento() {

    }

    public Evento(String evento) {
        this.nome = evento;
    }

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Evento [codigo=" + codigo + ", nome=" + nome + "]";
    }
}

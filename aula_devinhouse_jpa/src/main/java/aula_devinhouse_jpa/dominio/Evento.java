package aula_devinhouse_jpa.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long codigo;

    @Column
    private String nome;

    public Evento(){

    }

    public Evento(String evento){
        this.nome = evento;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
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
        return "Evento{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                '}';
    }
}

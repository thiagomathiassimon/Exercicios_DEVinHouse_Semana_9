package Model;

import java.sql.Timestamp;

public class Atendimento {

    private Integer codigo;
    private Integer pacinte;
    private String descricao;
    private Integer medico;
    private String situacao;
    private double valor;
    private Timestamp data;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getPacinte() {
        return pacinte;
    }

    public void setPacinte(Integer pacinte) {
        this.pacinte = pacinte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getMedico() {
        return medico;
    }

    public void setMedico(Integer medico) {
        this.medico = medico;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "codigo=" + codigo +
                ", pacinte=" + pacinte +
                ", descricao='" + descricao + '\'' +
                ", medico=" + medico +
                ", situacao='" + situacao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }
}

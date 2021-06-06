package br.senai.sc.edu.aula_spring_data.dto;

import lombok.Data;

@Data
public class ClienteEventoDTO {

    private String cleinte;
    private String evento;

    public ClienteEventoDTO(String cleinte, String evento) {
        this.cleinte = cleinte;
        this.evento = evento;
    }

    public String getCleinte() {
        return cleinte;
    }

    public void setCleinte(String cleinte) {
        this.cleinte = cleinte;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "ClienteEventoDTO{" +
                "cleinte='" + cleinte + '\'' +
                ", evento='" + evento + '\'' +
                '}';
    }
}

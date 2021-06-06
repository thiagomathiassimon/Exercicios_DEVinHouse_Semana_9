package dto;

import Model.Medico;
import Model.Paciente;
import lombok.*;

public class MedicoEPacienteDTO {

    private Medico medico;
    private Paciente paciente;

    public MedicoEPacienteDTO() {
    }

    public MedicoEPacienteDTO(Medico medico, Paciente paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "MedicoEPacienteDTO{" +
                "medico=" + medico +
                ", paciente=" + paciente +
                '}';
    }
}

package Main;

import DAO.AtendimentoDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Model.Atendimento;
import Model.Conexao;
import Model.Medico;
import Model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Conexao conexao = new Conexao();

        conexao.conecta();

        PacienteDAO pacienteDAO = new PacienteDAO();

        pacienteDAO.setConexao(conexao);

//        List<Paciente> pacientes = pacienteDAO.listarTodosOsPacientes();
//
//        for (Paciente paciente : pacientes){
//            System.out.println(paciente.getNome());
//        }

//        MedicoDAO medicoDAO = new MedicoDAO();
//
//        medicoDAO.setConexao(conexao);
//
//        List<Medico> medicos = medicoDAO.listarTodosOsMedicos();
//
//        for (Medico medico : medicos) {
//            System.out.println("Nome: " + medico.getNome() + "\nCRM: " + medico.getCrm());
//        }

        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();

        atendimentoDAO.setConexao(conexao);

        List<Atendimento> atendimentos = atendimentoDAO.listarTodosOsAtendimentos();

        for (Atendimento atendimento : atendimentos){
            System.out.println("Atendimento: " + atendimento.getDescricao());
        }
    }

}

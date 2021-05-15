package Main;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Model.Conexao;
import Model.Medico;
import Model.Paciente;

import java.sql.ResultSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Conexao conexao = new Conexao();

        conexao.conecta();

        PacienteDAO pacienteDAO = new PacienteDAO();

        pacienteDAO.setConexao(conexao);

        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.setConexao(conexao);

        for (Medico medico : medicoDAO.listarMedicosSemAtendimento()){
            System.out.println("Nome: " + medico.getNome() + " | CRM: " + medico.getCrm());
        }

//        for(List<Paciente> pacientes : pacienteDAO.listarTodosOsPacientesDeModoPaginado()){
//            for (Paciente paciente : pacientes){
//                System.out.println(paciente.getNome());
//            }
//            System.out.println("------------------------------------------------------------------------------");
//        }

//        for (String item : pacienteDAO.listarReferenciaMedicaDosPacientes()) {
//            System.out.println(item);
//        }


//        System.out.println(pacienteDAO.buscarMenorEMaiorIdade());

//        List<String> pacientes = pacienteDAO.listarPcientesPorMesDeNascimento();
//
//      for (String item : pacientes){
//          System.out.println(item + "\n");
//      }
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

//        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
//
//        atendimentoDAO.setConexao(conexao);
//
//        List<Atendimento> atendimentos = atendimentoDAO.listarTodosOsAtendimentos();
//
//        for (Atendimento atendimento : atendimentos){
//            System.out.println("Atendimento: " + atendimento.getDescricao());
//        }
    }

}

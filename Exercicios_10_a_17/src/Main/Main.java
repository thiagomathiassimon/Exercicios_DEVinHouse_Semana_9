package Main;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Model.Conexao;
import Model.Medico;
import Model.Paciente;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Conexao conexao = new Conexao();

        conexao.conecta();

        PacienteDAO pacienteDAO = new PacienteDAO();

        pacienteDAO.setConexao(conexao);

        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.setConexao(conexao);

        Paciente paciente = new Paciente();
        paciente.setNome("Getudres");
        paciente.setNascimento(Date.valueOf("1977-09-11"));
        paciente.setEmail("gertrudes@gmail.com");
        paciente.setTelefone("54966553300");

        pacienteDAO.setPaciente(paciente);
        pacienteDAO.setConexao(conexao);
//
//       boolean inseriu = pacienteDAO.inserirPacientesReferenciandoMedicoECriandoAtendimento("7799/RS", "Tratamento cardiológico",
//                "Agendado");
//
//        System.out.println(inseriu);

        paciente.setCodigo(28);

        boolean excluiu = pacienteDAO.excluirPacienteEAlterarAsFichasDeAtendimentoParaAnuladas();

        System.out.println(excluiu);

        conexao.confirmarTransacao();





//
//        for (Medico medico : medicoDAO.listarMedicosSemAtendimento()){
//            System.out.println("Nome: " + medico.getNome() + " | CRM: " + medico.getCrm());
//        }

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

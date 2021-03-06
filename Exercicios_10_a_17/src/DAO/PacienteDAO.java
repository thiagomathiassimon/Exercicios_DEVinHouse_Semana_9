package DAO;

import Model.Atendimento;
import Model.Conexao;
import Model.Medico;
import Model.Paciente;
import dto.MedicoEPacienteDTO;

import javax.sound.midi.SysexMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacienteDAO implements IGerenciamentoDAO{

    private Paciente paciente;
    private Conexao conexao;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public List<Paciente> listarTodosOsPacientes(){
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT * FROM paciente");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Paciente> pacientes = new ArrayList<>();

            while (resultSet.next()){
                Paciente paciente = new Paciente();

                paciente.setCodigo(resultSet.getInt("codigo"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setNascimento(resultSet.getDate("nascimento"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setTelefone(resultSet.getString("telefone"));

                pacientes.add(paciente);
            }

            resultSet.close();
            preparedStatement.close();

            return pacientes;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean inserir() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("INSERT INTO paciente (nome, nascimento, email, telefone) VALUES (?, ?, ? ,?) ");

            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setDate(2, paciente.getNascimento());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setString(4, paciente.getTelefone());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("UPDATE paciente SET nome = ?, nascimento = ?, email = ?, telefone = ? WHERE codigo = ?");

            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setDate(2, paciente.getNascimento());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setString(4, paciente.getTelefone());
            preparedStatement.setInt(5, paciente.getCodigo());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean excluir() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("DELETE FROM paciente WHERE codigo = ?");

            preparedStatement.setInt(1, paciente.getCodigo());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    public List<Paciente> listarPacientesPorAnoDeNascimento(int anoDeNascimento){
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT * FROM paciente WHERE EXTRACT(YEAR FROM nascimento) = ?;");

            preparedStatement.setInt(1, anoDeNascimento);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Paciente> pacientes = new ArrayList<>();

            while (resultSet.next()) {

                Paciente paciente = new Paciente();

                paciente.setCodigo(resultSet.getInt("codigo"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setNascimento(resultSet.getDate("nascimento"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setTelefone(resultSet.getString("telefone"));

                pacientes.add(paciente);

            }

            resultSet.close();
            preparedStatement.close();

            return pacientes;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public List<String> listarPcientesPorMesDeNascimento() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT EXTRACT(MONTH FROM nascimento), COUNT (*) FROM paciente \n" +
                            "\tGROUP BY EXTRACT(MONTH FROM nascimento);");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> list = new ArrayList<>();

            while (resultSet.next()) {

                String mes = "m??s: " + resultSet.getDouble("date_part");
                String quantidade = "\nquantia: " + resultSet.getInt("count");

                list.add(mes + quantidade);
            }

            resultSet.close();
            preparedStatement.close();

            return list;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public String buscarMenorEMaiorIdade() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT MIN(AGE(nascimento)), MAX(AGE(NASCIMENTO)) FROM paciente;");

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            String result = "Menor idade: " + resultSet.getString("min") + " | Maior idade: " + resultSet.getString("max");

            resultSet.close();
            preparedStatement.close();

            return result;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "";
        }
    }

    public List<String> listarReferenciaMedicaDosPacientes() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT p.nome as \"paciente\", p.nascimento, p.email, p.telefone, m.nome as \"m??dico\", m.crm\n" +
                            "\tFROM paciente p INNER JOIN atendimento a ON p.codigo = a.paciente\n" +
                            "\tINNER JOIN medico m ON a.medico = m.codigo ORDER BY p.nome;");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> list = new ArrayList<>();

            while (resultSet.next()){

                String paciente = "Nome do paciente: " + (resultSet.getString("paciente"));
                paciente += "\nData de nascimento: " + (resultSet.getDate("nascimento"));
                paciente += "\nE-mail: " + (resultSet.getString("email"));
                paciente += "\nTelefone: " + (resultSet.getString("paciente"));
                String medico = "\nNome do m??dico: " + (resultSet.getString("m??dico"));
                medico += "\nCRM: " + (resultSet.getString("crm"));

                list.add(paciente + medico + "\n");
            }

            resultSet.close();
            preparedStatement.close();

            return list;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public List<List<Paciente>> listarTodosOsPacientesDeModoPaginado() {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT * FROM paciente LIMIT(10) OFFSET(0);");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<List<Paciente>> listaPaginada = new ArrayList<>();
            List<Paciente> firstList = new ArrayList<>();
            List<Paciente> secondList = new ArrayList<>();

            while (resultSet.next()){
                Paciente paciente = new Paciente();

                paciente.setCodigo(resultSet.getInt("codigo"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setNascimento(resultSet.getDate("nascimento"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setTelefone(resultSet.getString("telefone"));

                firstList.add(paciente);
            }

            listaPaginada.add(firstList);

            preparedStatement = this.conexao.getConexao().prepareStatement("SELECT * FROM paciente LIMIT(10) OFFSET(10);");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Paciente paciente = new Paciente();

                paciente.setCodigo(resultSet.getInt("codigo"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setNascimento(resultSet.getDate("nascimento"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setTelefone(resultSet.getString("telefone"));

                secondList.add(paciente);
            }

            listaPaginada.add(secondList);

            resultSet.close();
            preparedStatement.close();

            return listaPaginada;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public boolean inserirPacientesReferenciandoMedicoECriandoAtendimento(String crmDoMedico, String descricao, String situacao) {

        MedicoDAO medicoDAO = new MedicoDAO();
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();

        medicoDAO.setConexao(this.conexao);
        atendimentoDAO.setConexao(this.conexao);

        List<Medico> medicos = medicoDAO.listarTodosOsMedicos();

        for (Medico medico : medicos) {
            if (medico.getCrm().equals(crmDoMedico)){
                System.out.println(this.inserir());

                List<Atendimento> atendimentos = atendimentoDAO.listarTodosOsAtendimentos();
                int maiorCodigoDeAtendimento = 0;
                for (Atendimento atendimento : atendimentos){
                    if (atendimento.getCodigo() > maiorCodigoDeAtendimento){
                        maiorCodigoDeAtendimento = atendimento.getCodigo();
                    }
                }

                int maiorCodigoDePaciente = 0;
                for(Paciente paciente : this.listarTodosOsPacientes()){
                    if (paciente.getCodigo() > maiorCodigoDePaciente){
                        maiorCodigoDePaciente = paciente.getCodigo();
                    }
                }

                Atendimento atendimento = new Atendimento();
                atendimento.setCodigo(maiorCodigoDeAtendimento + 1);
                atendimento.setPacinte(maiorCodigoDePaciente);
                atendimento.setDescricao(descricao);
                atendimento.setMedico(medico.getCodigo());
                atendimento.setSituacao(situacao);

                atendimentoDAO.setAtendimento(atendimento);
                atendimentoDAO.inserir();

                return true;
            }
        }
        return false;
    }

    public boolean excluirPacienteEAlterarAsFichasDeAtendimentoParaAnuladas() {
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        atendimentoDAO.setConexao(this.conexao);

        List<Atendimento> atendimentos = atendimentoDAO.listarTodosOsAtendimentos();

        boolean excluiu = false;
        for (Atendimento atendimento : atendimentos){
            System.out.println(paciente.getCodigo());
            if (paciente.getCodigo() == atendimento.getPacinte()){
                atendimentoDAO.setAtendimento(atendimento);
                atendimentoDAO.excluir();
                excluiu = true;
            }
        }

        this.excluir();

        return excluiu;
    }

    public List<MedicoEPacienteDTO> listarPacienteEMedico() {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT p.codigo as idPaciente, p.nome as nomePaciente, p.email, p.nascimento, p.telefone, " +
                            " m.codigo as idMedico, m.nome as nomeMedico, m.crm FROM paciente p INNER JOIN atendimento a " +
                            "ON a.paciente = p.codigo INNER JOIN medico m ON m.codigo = a.medico;");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<MedicoEPacienteDTO> list = new ArrayList<>();

            while (resultSet.next()){
               MedicoEPacienteDTO medicoEPacienteDTO = new MedicoEPacienteDTO();

               Medico medico = new Medico();
               Paciente paciente = new Paciente();

               paciente.setCodigo(resultSet.getInt("idPaciente"));
               paciente.setNome(resultSet.getString("nomePaciente"));
               paciente.setEmail(resultSet.getString("email"));
               paciente.setTelefone(resultSet.getString("telefone"));

               medico.setCodigo(resultSet.getInt("idMedico"));
               medico.setNome(resultSet.getString("nomeMedico"));
               medico.setCrm(resultSet.getString("crm"));

               medicoEPacienteDTO.setPaciente(paciente);
               medicoEPacienteDTO.setMedico(medico);

               list.add(medicoEPacienteDTO);
            }

            return list;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

}

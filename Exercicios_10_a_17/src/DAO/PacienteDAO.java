package DAO;

import Model.Conexao;
import Model.Medico;
import Model.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

                String mes = "mês: " + resultSet.getDouble("date_part");
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
                    .prepareStatement("SELECT p.nome as \"paciente\", p.nascimento, p.email, p.telefone, m.nome as \"médico\", m.crm\n" +
                            "\tFROM paciente p INNER JOIN atendimento a ON p.codigo = a.paciente\n" +
                            "\tINNER JOIN medico m ON a.medico = m.codigo ORDER BY p.nome;");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> list = new ArrayList<>();

            while (resultSet.next()){

                String paciente = "Nome do paciente: " + (resultSet.getString("paciente"));
                paciente += "\nData de nascimento: " + (resultSet.getDate("nascimento"));
                paciente += "\nE-mail: " + (resultSet.getString("email"));
                paciente += "\nTelefone: " + (resultSet.getString("paciente"));
                String medico = "\nNome do médico: " + (resultSet.getString("médico"));
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

}

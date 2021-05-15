package DAO;

import Model.Conexao;
import Model.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}

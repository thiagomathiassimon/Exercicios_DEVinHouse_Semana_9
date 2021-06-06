package DAO;

import Model.Atendimento;
import Model.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO implements IGerenciamentoDAO {

    private Atendimento atendimento;
    private Conexao conexao;

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public List<Atendimento> listarTodosOsAtendimentos() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT * FROM atendimento");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Atendimento> atendimentos = new ArrayList<>();

            while (resultSet.next()){

                Atendimento atendimento = new Atendimento();

                atendimento.setCodigo(resultSet.getInt("codigo"));
                atendimento.setPacinte(resultSet.getInt("paciente"));
                atendimento.setDescricao(resultSet.getString("descricao"));
                atendimento.setMedico(resultSet.getInt("medico"));
                atendimento.setSituacao(resultSet.getString("situacao"));
                atendimento.setValor(resultSet.getDouble("valor"));
                atendimento.setData(resultSet.getTimestamp("data"));

                atendimentos.add(atendimento);
            }

            resultSet.close();
            preparedStatement.close();

            return atendimentos;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean inserir() {

        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("INSERT INTO atendimento (codigo, paciente, descricao, medico, situacao, valor, data) VALUES (?, ?, ?, ?, ?, ?, NOW())");

            preparedStatement.setInt(1, atendimento.getCodigo());
            preparedStatement.setInt(2, atendimento.getPacinte());
            preparedStatement.setString(3, atendimento.getDescricao());
            preparedStatement.setInt(4, atendimento.getMedico());
            preparedStatement.setString(5, atendimento.getSituacao());
            preparedStatement.setDouble(6, atendimento.getValor());

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
                    .prepareStatement("UPDATE atendimento SET paciente = ?, descricao = ?, medico = ?, situacao = ?, valor = ?, data = ? " +
                            "WHERE codigo = ?");

            preparedStatement.setInt(1, atendimento.getPacinte());
            preparedStatement.setString(2, atendimento.getDescricao());
            preparedStatement.setInt(3, atendimento.getMedico());
            preparedStatement.setString(4, atendimento.getSituacao());
            preparedStatement.setDouble(5, atendimento.getValor());
            preparedStatement.setTimestamp(6, atendimento.getData());
            preparedStatement.setInt(7, atendimento.getCodigo());

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
                    .prepareStatement("DELETE FROM atendimento WHERE codigo = ?");

            preparedStatement.setInt(1, atendimento.getCodigo());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    public List<Atendimento> buscarAtendimentosDePacientesComMaisDeUmMedico() {
        List<Atendimento> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.conexao.getConexao()
                    .prepareStatement("""
                            SELECT * FROM (SELECT a.paciente, COUNT(a.medico) FROM atendimento a
                                        GROUP BY a.paciente) as result\s
                                        INNER JOIN atendimento a\s
                                        ON a.paciente = result.paciente
                                        WHERE result.count > 1
                                    """);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Atendimento atendimento = new Atendimento();

                atendimento.setCodigo(resultSet.getInt("codigo"));
                atendimento.setPacinte(resultSet.getInt("paciente"));
                atendimento.setDescricao(resultSet.getString("descricao"));
                atendimento.setMedico(resultSet.getInt("medico"));
                atendimento.setSituacao(resultSet.getString("situacao"));
                atendimento.setValor(resultSet.getDouble("valor"));
                atendimento.setData(resultSet.getTimestamp("data"));

                list.add(atendimento);
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return list;
    }

}

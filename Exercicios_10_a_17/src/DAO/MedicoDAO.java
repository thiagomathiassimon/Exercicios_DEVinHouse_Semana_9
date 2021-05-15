package DAO;

import Model.Conexao;
import Model.Medico;

import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO implements IGerenciamentoDAO {

    private Medico medico;
    private Conexao conexao;

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public List<Medico> listarTodosOsMedicos() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT * FROM medico");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Medico> medicos = new ArrayList<>();

            while (resultSet.next()){

                Medico medico = new Medico();

                medico.setCodigo(resultSet.getInt("codigo"));
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getString("crm"));

                medicos.add(medico);
            }

            resultSet.close();
            preparedStatement.close();

            return medicos;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean inserir() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("INSET INTO medico (nome, crm) VALUES (?, ?)");

            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCrm());

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
                    .prepareStatement("UPDATE medico SET nome = ?, crm = ? WHERE codigo = ?");

            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(1, medico.getCrm());
            preparedStatement.setInt(1, medico.getCodigo());

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
                    .prepareStatement("DELETE FROM medico WHERE codigo = ?");

            preparedStatement.setInt(1, medico.getCodigo());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    public List<Medico> listarMedicosSemAtendimento() {
        try {
            PreparedStatement preparedStatement = this.conexao.getConexao()
                    .prepareStatement("SELECT * FROM medico m LEFT JOIN atendimento a ON m.codigo = a.medico WHERE a.medico IS NULL;");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Medico> medicos = new ArrayList<>();

            while (resultSet.next()){
                Medico medico = new Medico();

                medico.setCodigo(resultSet.getInt("codigo"));
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getString("crm"));

                medicos.add(medico);
            }

            resultSet.close();
            preparedStatement.close();

            return medicos;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}

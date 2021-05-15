package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class Conexao {

    final static String URL = "jdbc:postgresql://127.0.0.2:5433/exercicios_dev_in_house";

    final static String USER = "postgres";
    final static String PASS = "postgres";

    private static Connection conexao;

    public boolean conecta(){
        try{
            if (conexao != null && !conexao.isClosed()){
                return true;
            }
            Class.forName("org.postgresql.Driver");

            conexao = DriverManager.getConnection(URL, USER, PASS);

            conexao.setAutoCommit(false);
            conexao.setTransactionIsolation(TRANSACTION_READ_COMMITTED);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean conecta(String ip, String banco, String user, String pass) throws Exception{
        try{
            if (conexao != null && !conexao.isClosed()){
                return true;
            }

            Class.forName("org.postgresql.Driver");
            StringBuilder url = new StringBuilder("jdbc:postgresql://");
            url.append(ip).append("/").append(banco);

            conexao = DriverManager.getConnection(url.toString(), user, pass);

            conexao.setAutoCommit(false);
            conexao.setTransactionIsolation(TRANSACTION_READ_COMMITTED);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public Connection getConexao(){
        return conexao;
    }

    public void fechar() throws Exception{
        try {
            if (conexao == null || conexao.isClosed()){
                return;
            }
            conexao.close();
        } catch (SQLException sqlException) {
            throw new Exception("Falha ocorrida: " + sqlException.getMessage());
        }
    }

    public void confirmarTransacao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed()) {
                return;
            }
            conexao.commit();
        } catch (SQLException sqlException) {
            throw new Exception("Falha ocorrida: " + sqlException.getMessage());
        }
    }

    public void cancelarTranscao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed()) {
                return;
            }
            conexao.rollback();
        } catch (SQLException sqlException) {
            throw new Exception("Falha ocorrida: " + sqlException.getMessage());
        }
    }
}

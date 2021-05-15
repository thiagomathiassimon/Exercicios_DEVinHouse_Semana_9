package DAO;

import java.sql.SQLException;
import java.util.List;

public interface IGerenciamentoDAO {

    public boolean inserir() throws SQLException;

    public boolean atualizar();

    public boolean excluir();

}

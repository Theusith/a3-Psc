package Conexao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Conexao instancia;
    private Connection conexao;

    private static final String url = "jdbc:mysql://localhost:3306/testedb";
    private static final String user = "root";
    private static final String password = "54678";

    private Conexao() throws SQLException {
        this.conexao = DriverManager.getConnection(url, user, password);
    }

    public static Conexao getConexao() throws SQLException {
        if (instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }

    public Connection getConnection() {
        return conexao;
    }

    // Método para fechar a conexão (opcional)
    public void closeConnection() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
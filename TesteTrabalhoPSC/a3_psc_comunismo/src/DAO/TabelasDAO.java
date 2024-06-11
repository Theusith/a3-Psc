package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entity.Login;
import Entity.TabelaReservas;
import Model.Reserva;
import Conexao.Conexao;

public class TabelasDAO {

    public void cadastrarUsuario1(Login login) {
        Connection conn = null;
        PreparedStatement psLogin = null;
    
        String sqlLogin = "INSERT INTO LOGIN (NOME, CPF, EMAIL, SENHA) VALUES (?, ?, ?, ?)";
    
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false); // Desabilita autocommit para transação
    
            // Inserção na tabela USUARIO
            psLogin = conn.prepareStatement(sqlLogin, PreparedStatement.RETURN_GENERATED_KEYS);
            psLogin.setString(1, login.getNome());
            psLogin.setString(2, login.getCpf());
            psLogin.setString(3, login.getEmail());
            psLogin.setString(4, login.getSenha());
            psLogin.executeUpdate();
    
            // Recuperar o ID gerado
            ResultSet rs = psLogin.getGeneratedKeys();
            if (rs != null && rs.next()) {
                int codigoUsuario = rs.getInt(1);
                // Você pode usar o códigoUsuario para o que precisar.
            }
    
            conn.commit(); // Commit da transação
    
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback em caso de erro
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (psLogin != null) psLogin.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
   public void cadastrarUsuario2(TabelaReservas tabelaReservas) {
    Connection conn = null;
    PreparedStatement pstabelaReservas = null;

    String sqlReserva = "INSERT INTO RESERVAS (ORIGEM , DESTINO, DATA_VIAGEM) VALUES (?, ?, ?)";

    try {
        conn = Conexao.getConexao();
        conn.setAutoCommit(false); // Desabilita autocommit para transação

        // Inserção na tabela RESERVAS
        pstabelaReservas = conn.prepareStatement(sqlReserva, PreparedStatement.RETURN_GENERATED_KEYS);
         // Assumindo que você tenha um método getCodigo() em Cliente

        pstabelaReservas.setString(1, tabelaReservas.getOrigem());
        pstabelaReservas.setString(2, tabelaReservas.getDestino());
        pstabelaReservas.setString(3, tabelaReservas.getDataViagem());
        pstabelaReservas.executeUpdate();
        
        ResultSet rs = pstabelaReservas.getGeneratedKeys();
        if (rs != null && rs.next()) {
            int codigoUsuario = rs.getInt(1);
            // Você pode usar o códigoUsuario para o que precisar.
        }

        conn.commit(); // Commit da transação

        System.out.println("Reserva cadastrada com sucesso!");

    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); // Rollback em caso de erro
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        try {
            if (pstabelaReservas != null) pstabelaReservas.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
}
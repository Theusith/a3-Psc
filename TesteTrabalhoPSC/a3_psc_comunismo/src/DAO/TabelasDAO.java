package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entity.Usuarios;
import Entity.TabelaReservas;
import Conexao.Conexao;
import Model.Cliente;

public class TabelasDAO {


    
   public void cadastrarReserva(TabelaReservas tabelaReservas) {
    Connection conn = null;
    PreparedStatement pstabelaReservas = null;

    String sqlReserva = "INSERT INTO RESERVAS (ORIGEM , DESTINO, DATA_VIAGEM) VALUES (?, ?, ?)";

    try {
        conn = (Connection) Conexao.getConexao();
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
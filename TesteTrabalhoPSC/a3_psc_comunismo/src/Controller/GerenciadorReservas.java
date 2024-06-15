package Controller;

import Conexao.Conexao;
import Model.Cliente;
import Model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar reservas de clientes.
 */
public class GerenciadorReservas {
    public void adicionarReserva(Reserva reserva) {
        String sql = "INSERT INTO reservas (idPessoa, origem, destino, dataViagem) VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdCliente());
            stmt.setString(2, reserva.getOrigem());
            stmt.setString(3, reserva.getDestino());
            stmt.setDate(4, reserva.getDataViagem()); // Certifique-se de que o tipo de dado da coluna dataViagem no banco seja compatível

            stmt.executeUpdate();
            System.out.println("Reserva adicionada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar reserva: " + e.getMessage());
            // Tratar o erro de forma mais adequada (rollback, log, etc.)
        }
    }

    public List<Reserva> visualizarReservasPorCliente(int idCliente) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas WHERE idPessoa = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int idReserva = (rs.getInt("idReserva"));
                idCliente = (rs.getInt("idCliente"));
                String origem = (rs.getString("origem"));
                String destino = (rs.getString("destino"));
                Date dataViagem = (rs.getDate("dataViagem"));
                Reserva reserva = new Reserva(idCliente, origem, idReserva, destino, dataViagem);

                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao visualizar reservas: " + e.getMessage());
            // Tratar o erro de forma mais adequada
        }
        return reservas;
    }
    public void deletarReservasPorCliente(int idCliente) {
        String sql = "DELETE FROM reservas WHERE idPessoa = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Reservas do cliente deletadas com sucesso!");
            } else {
                System.out.println("Nenhuma reserva encontrada para o cliente.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar reservas: " + e.getMessage());
            // Tratar o erro de forma mais adequada (rollback, log, etc.)
        }
    }

}

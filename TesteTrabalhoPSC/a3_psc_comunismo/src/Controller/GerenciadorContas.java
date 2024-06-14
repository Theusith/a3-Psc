package Controller;

import java.sql.*;
import Conexao.Conexao;

import Model.Administrador;
import Model.Cliente;
import Model.Pessoa;


public class GerenciadorContas {

    public GerenciadorContas() {
        adicionarAdministradorPadrao();

    }
    public void cadastrarCliente(Cliente usuario) {
        Connection conn = null;
        PreparedStatement psDados = null;

        String sqlDados = "INSERT INTO USUARIOS (NOME, CPF, EMAIL, SENHA, TIPO) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false); // Desabilita autocommit para transação

            // Inserção na tabela USUARIO
            psDados = conn.prepareStatement(sqlDados, PreparedStatement.RETURN_GENERATED_KEYS);
            psDados.setString(1, usuario.getNome());
            psDados.setString(2, usuario.getCpf());
            psDados.setString(3, usuario.getEmail());
            psDados.setString(4, usuario.getSenha());
            psDados.setString(5, usuario.getTipo());
            psDados.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");

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
                if (psDados != null) psDados.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void adicionarAdministradorPadrao() {
        boolean adicionado = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conexao = Conexao.getConexao(); // Obtém a conexão existente
            String sql = "SELECT * FROM usuarios WHERE tipo = 'Administrador' "; // Sua consulta SQL
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                Pessoa ps = new Pessoa("Administrador", "000000000000", "Administrador@adm.com", "Administrador");
                String tipo = "Administrador";
                ps.setMatricula("01");
                ps.setTipo(tipo);
                Connection conn = null;
                PreparedStatement psDados = null;

                String sqlDados = "INSERT INTO USUARIOS (NOME, CPF, EMAIL, SENHA, TIPO) VALUES (?, ?, ?, ?, ?)";

                try {
                    conn = Conexao.getConexao();
                    conn.setAutoCommit(false); // Desabilita autocommit para transação

                    // Inserção na tabela USUARIO
                    psDados = conn.prepareStatement(sqlDados, PreparedStatement.RETURN_GENERATED_KEYS);
                    psDados.setString(1, ps.getNome());
                    psDados.setString(2, ps.getCpf());
                    psDados.setString(3, ps.getEmail());
                    psDados.setString(4, ps.getSenha());
                    psDados.setString(5, ps.getTipo());
                    psDados.executeUpdate();
                    System.out.println("Administrador Cadastrado com sucesso!");

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
                        if (psDados != null) psDados.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos (ResultSet, PreparedStatement) aqui
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

        public Pessoa autenticarPessoa (String email, String senha){
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Pessoa cl = null;

            try {
                Connection conexao = Conexao.getConexao();
                String sql = "SELECT * FROM usuarios WHERE email = ? and senha = ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, senha);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    int id1 = rs.getInt("ID");
                    String nome = rs.getString("NOME");
                    String cpf = rs.getString("CPF");
                    email = rs.getString("EMAIL");
                    senha = rs.getString("SENHA");
                    String matricula = rs.getString("MATRICULA");
                    String tipo = rs.getString("TIPO");
                    cl = new Pessoa(nome, cpf, email, senha);
                    cl.setTipo(tipo);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Fechar recursos (ResultSet, PreparedStatement) aqui
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return cl; // Pessoa não encontrada ou senha incorreta
        }

        public Cliente obterClientePorId ( int id){
            Cliente cliente = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                Connection conexao = Conexao.getConexao(); // Obtém a conexão existente
                String sql = "SELECT * FROM usuarios WHERE id = ?"; // Sua consulta SQL
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    String id1 = rs.getString("id");
                    String nome = rs.getString("NOME");
                    String cpf = rs.getString("CPF");
                    String email = rs.getString("EMAIL");
                    String senha = rs.getString("SENHA");
                    cliente = new Cliente(nome, cpf, email, senha);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Fechar recursos (ResultSet, PreparedStatement) aqui
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return cliente;
        }

}



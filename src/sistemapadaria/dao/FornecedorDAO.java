/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemapadaria.model.Fornecedor; // 2. Import do modelo no novo pacote
import sistemapadaria.util.Conexao;


public class FornecedorDAO {
    public List<Fornecedor> listarFornecedores() {
        String sql = "SELECT id, nome, cnpj, email, telefone FROM fornecedor";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return fornecedores;
            }

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                // Adicione aqui outros campos se sua classe Fornecedor os tiver:
                // fornecedor.setEmail(rs.getString("email"));
                // fornecedor.setTelefone(rs.getString("telefone"));

                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            // 4. JOptionPane removido. Log técnico via console.
            System.err.println("Erro técnico ao listar fornecedores: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return fornecedores;
    }

    public List<Fornecedor> listarTodos() {
        String sql = "SELECT id, nome FROM fornecedor ORDER BY nome";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return fornecedores;
            }

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                fornecedores.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar para ComboBox: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return fornecedores;
    }

    public void salvarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (nome, cnpj, email, telefone) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return;
            }

            stmt = con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            // Se o objeto fornecedor já tiver esses dados, use os getters:
            stmt.setString(3, null); 
            stmt.setString(4, null); 

            stmt.executeUpdate();
            
            // 4. Log de sucesso substituindo o JOptionPane
            System.out.println("✅ Fornecedor '" + fornecedor.getNome() + "' salvo com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro técnico ao salvar fornecedor: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}


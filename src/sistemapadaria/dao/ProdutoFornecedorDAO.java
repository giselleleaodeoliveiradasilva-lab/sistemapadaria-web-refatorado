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

import sistemapadaria.model.ProdutoFornecedor;
import sistemapadaria.util.Conexao;

public class ProdutoFornecedorDAO {
public void vincular(ProdutoFornecedor pf) {
        String sql = "INSERT INTO produto_fornecedor (id_produto, id_fornecedor) VALUES (?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pf.getIdProduto());
            stmt.setInt(2, pf.getIdFornecedor());

            stmt.executeUpdate();
            System.out.println("✅ Vínculo Produto-Fornecedor criado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro técnico ao vincular produto e fornecedor: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    /**
     * Lista todos os vínculos existentes.
     */
    public List<ProdutoFornecedor> listarVinculos() {
        String sql = "SELECT * FROM produto_fornecedor";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ProdutoFornecedor> lista = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return lista;

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoFornecedor pf = new ProdutoFornecedor();
                pf.setId(rs.getInt("id"));
                pf.setIdProduto(rs.getInt("id_produto"));
                pf.setIdFornecedor(rs.getInt("id_fornecedor"));
                lista.add(pf);
            }
        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar vínculos: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    /**
     * Remove um vínculo específico pelo ID.
     */
    public void removerVinculo(int id) {
        String sql = "DELETE FROM produto_fornecedor WHERE id = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Vínculo removido com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro técnico ao remover vínculo: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}


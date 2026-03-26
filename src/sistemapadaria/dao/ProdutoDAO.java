/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sistemapadaria.model.Produto;
import sistemapadaria.util.Conexao;



public class ProdutoDAO {
    private static final SimpleDateFormat FORMATO_BD = new SimpleDateFormat("yyyy-MM-dd");

    private String formatarDataParaBD(String dataJava) throws ParseException {
        if (dataJava == null || dataJava.isEmpty()) {
            return null;
        }
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatoEntrada.parse(dataJava);
        return FORMATO_BD.format(data);
    }

    public void salvarProduto(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, preco, quantidade_estoque, data_cadastro, data_validade, id_fornecedor) " +
                     "VALUES (?, ?, ?, ?, CURDATE(), ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;
        String dataValidadeBD = null;

        try {
            dataValidadeBD = formatarDataParaBD(produto.getDataValidade());
            con = Conexao.getConnection();
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            stmt.setString(5, dataValidadeBD);
            stmt.setInt(6, produto.getIdFornecedor());

            stmt.executeUpdate();
            System.out.println("✅ Produto '" + produto.getNome() + "' salvo com sucesso!");

        } catch (ParseException e) {
            System.err.println("Erro de data no produto: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar produto: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Produto> listarProdutos() {
        String sql = "SELECT * FROM produto";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return produtos;

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                produtos.add(mapearProduto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public List<Produto> buscarProdutosPorNome(String nome) {
        String sql = "SELECT * FROM produto WHERE nome LIKE ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return produtos;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                produtos.add(mapearProduto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getId());

            stmt.executeUpdate();
            System.out.println("✅ Produto ID " + produto.getId() + " atualizado.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void excluirProduto(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Produto ID " + id + " excluído.");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public Produto buscarProdutoPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            con = Conexao.getConnection();
            if (con == null) return null;

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                produto = mapearProduto(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produto;
    }

    /**
     * Método auxiliar para evitar repetição de código (DRY).
     * Converte uma linha do banco em um objeto Produto.
     */
    private Produto mapearProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao(rs.getString("descricao"));
        p.setPreco(rs.getDouble("preco"));
        p.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
        p.setDataCadastro(rs.getString("data_cadastro"));
        p.setDataValidade(rs.getString("data_validade"));
        p.setIdFornecedor(rs.getInt("id_fornecedor"));
        return p;
    }
}


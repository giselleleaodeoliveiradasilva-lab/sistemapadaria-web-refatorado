/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import sistemapadaria.model.ItemVenda;
import sistemapadaria.util.Conexao;


public class ItemVendaDAO {
    public boolean salvarItensVenda(List<ItemVenda> itens, int idVenda) {

        String sql = "INSERT INTO item_venda (id_venda, id_produto, quantidade, preco_unitario, sub_total) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return false;
            }

            // Desativa o commit automático para iniciar a transação manual
            con.setAutoCommit(false);

            stmt = con.prepareStatement(sql);

            for (ItemVenda item : itens) {
                stmt.setInt(1, idVenda);
                stmt.setInt(2, item.getIdProduto());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getPrecoUnitario());
                stmt.setDouble(5, item.getSubTotal());
                stmt.addBatch(); // Adiciona ao lote
            }

            stmt.executeBatch(); // Executa todos os itens de uma vez
            con.commit();        // Confirma a gravação no banco
            
            System.out.println("✅ Itens da venda salvos com sucesso!");
            return true;

        } catch (SQLException e) {
            // Caso ocorra qualquer erro, desfazemos tudo o que foi tentado
            try {
                if (con != null) {
                    con.rollback();
                    System.err.println("⚠ Transação revertida (Rollback) devido a erro.");
                }
            } catch (SQLException ex) {
                System.err.println("Erro ao tentar fazer rollback: " + ex.getMessage());
            }
            
            // Log técnico no console substituindo o JOptionPane
            System.err.println("Erro técnico ao salvar itens em lote: " + e.getMessage());
            return false;

        } finally {
            // Restaura o estado original da conexão antes de fechar
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                System.err.println("Erro ao restaurar AutoCommit: " + ex.getMessage());
            }
            Conexao.closeConnection(con, stmt);
        }
    }
}


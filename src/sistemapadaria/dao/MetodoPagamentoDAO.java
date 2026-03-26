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
import sistemapadaria.model.MetodoPagamento;
import sistemapadaria.util.Conexao;

public class MetodoPagamentoDAO {
    public void salvarMetodoPagamento(MetodoPagamento metodo) {
        String sql = "INSERT INTO metodo_pagamento (nome) VALUES (?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return;
            }

            stmt = con.prepareStatement(sql);
            stmt.setString(1, metodo.getNome());

            stmt.executeUpdate();
            
            // Log de sucesso no console para o desenvolvedor verificar
            System.out.println("✅ Método de Pagamento '" + metodo.getNome() + "' salvo com sucesso!");

        } catch (SQLException e) {
            // Log técnico de erro substituindo o JOptionPane
            System.err.println("Erro técnico ao salvar método de pagamento: " + e.getMessage());

        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<MetodoPagamento> listarMetodosPagamento() {
        String sql = "SELECT id, nome FROM metodo_pagamento";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<MetodoPagamento> metodos = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return metodos;
            }

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MetodoPagamento metodo = new MetodoPagamento();
                metodo.setId(rs.getInt("id"));
                metodo.setNome(rs.getString("nome"));

                metodos.add(metodo);
            }

        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar métodos de pagamento: " + e.getMessage());

        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return metodos;
    }
}


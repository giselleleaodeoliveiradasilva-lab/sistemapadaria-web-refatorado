/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sistemapadaria.model.Venda;
import sistemapadaria.util.Conexao;



public class VendaDAO {
    public int salvarVenda(Venda venda) {
        String sql = "INSERT INTO venda (data_hora, valor_total, id_funcionario, id_metodo_pagamento) "
                   + "VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idGerado = 0;

        try {
            con = Conexao.getConnection();
            if (con == null) return 0;

            // Prepara o statement para retornar a chave primária (ID) criada pelo banco
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, venda.getDataHora());
            stmt.setDouble(2, venda.getValorTotal());
            stmt.setInt(3, venda.getIdFuncionario());
            stmt.setInt(4, venda.getIdMetodoPagamento());

            stmt.executeUpdate();

            // Recupera o ID da venda recém-criada
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
            
            System.out.println("✅ Venda registrada com sucesso! ID: " + idGerado);

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao registrar venda: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return idGerado;
    }

    public List<Venda> listarVendas() {
        String sql = "SELECT id, data_hora, valor_total, id_funcionario, id_metodo_pagamento FROM venda";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Venda> vendas = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return vendas;

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setDataHora(rs.getString("data_hora"));
                venda.setValorTotal(rs.getDouble("valor_total"));
                venda.setIdFuncionario(rs.getInt("id_funcionario"));
                venda.setIdMetodoPagamento(rs.getInt("id_metodo_pagamento"));

                vendas.add(venda);
            }
        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar vendas: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return vendas;
    }
}


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

import sistemapadaria.model.Telefone;
import sistemapadaria.util.Conexao;


public class TelefoneDAO {
    public int salvarTelefone(Telefone telefone) {
        String sql = "INSERT INTO telefone (ddd, numero, tipo) VALUES (?, ?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idGerado = 0;

        try {
            con = Conexao.getConnection();
            if (con == null) return 0;

            // Mantendo a captura da chave gerada (Auto-incremento)
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setString(3, telefone.getTipo());

            stmt.executeUpdate();

            // Recupera o ID gerado pelo MySQL
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
            
            System.out.println("✅ Telefone salvo com ID: " + idGerado);

        } catch (SQLException e) {
            System.err.println("Erro técnico ao salvar telefone: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return idGerado;
    }

    public List<Telefone> listarTelefones() {
        String sql = "SELECT id, ddd, numero, tipo FROM telefone";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Telefone> telefones = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return telefones;

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setDdd(rs.getString("ddd"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(rs.getString("tipo"));

                telefones.add(telefone);
            }

        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar telefones: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return telefones;
    }
}

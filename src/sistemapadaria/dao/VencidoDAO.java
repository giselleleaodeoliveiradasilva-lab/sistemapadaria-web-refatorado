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

import sistemapadaria.model.Vencido;
import sistemapadaria.util.Conexao;


public class VencidoDAO {
    private static final SimpleDateFormat FORMATO_BD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Auxiliar para converter data do padrão brasileiro para o padrão MySQL.
     */
    private String formatarDataParaBD(String dataJava) throws ParseException {
        if (dataJava == null || dataJava.isEmpty()) {
            return null;
        }
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatoEntrada.parse(dataJava);
        return FORMATO_BD.format(data);
    }

    public void salvarVencido(Vencido vencido) {
        String sql = "INSERT INTO vencido (data_descarte, quantidade, id_produto) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            String dataDescarteBD = formatarDataParaBD(vencido.getDataDescarte());

            con = Conexao.getConnection();
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, dataDescarteBD);
            stmt.setInt(2, vencido.getQuantidade());
            stmt.setInt(3, vencido.getIdProduto());

            stmt.executeUpdate();
            
            System.out.println("✅ Registro de descarte (ID Produto: " + vencido.getIdProduto() + ") salvo com sucesso!");

        } catch (ParseException e) {
            System.err.println("Erro de formatação de data: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar vencido: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Vencido> listarVencidos() {
        String sql = "SELECT id, data_descarte, quantidade, id_produto FROM vencido";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vencido> vencidos = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return vencidos;

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vencido vencido = new Vencido();
                vencido.setId(rs.getInt("id"));
                vencido.setDataDescarte(rs.getString("data_descarte"));
                vencido.setQuantidade(rs.getInt("quantidade"));
                vencido.setIdProduto(rs.getInt("id_produto"));

                vencidos.add(vencido);
            }

        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar vencidos: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return vencidos;
    }
}


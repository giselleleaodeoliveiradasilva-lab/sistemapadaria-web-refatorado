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

import sistemapadaria.model.Manutencao;
import sistemapadaria.util.Conexao;



public class ManutencaoDAO {
    private static final SimpleDateFormat FORMATO_BD = new SimpleDateFormat("yyyy-MM-dd");

    private String formatarDataParaBD(String dataJava) throws ParseException {
        if (dataJava == null || dataJava.isEmpty()) {
            return null;
        }
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatoEntrada.parse(dataJava);
        return FORMATO_BD.format(data);
    }

    public void salvarManutencao(Manutencao manutencao) {
        String sql = "INSERT INTO manutencao (nome_empresa, data_ultima_manutencao, nome_equipamento, id_telefone, id_endereco) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;
        String dataManutencaoBD = null;

        try {
            dataManutencaoBD = formatarDataParaBD(manutencao.getDataUltimaManutencao());
            con = Conexao.getConnection();
            
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, manutencao.getNomeEmpresa());
            stmt.setString(2, dataManutencaoBD);
            stmt.setString(3, manutencao.getNomeEquipamento());
            stmt.setInt(4, manutencao.getIdTelefone());
            
            // Uso do setObject para tratar nulos em chaves estrangeiras, se necessário
            stmt.setObject(5, manutencao.getIdEndereco(), java.sql.Types.INTEGER);

            stmt.executeUpdate();
            
            // Log de sucesso no console para o desenvolvedor
            System.out.println("✅ Registro de Manutenção salvo com sucesso!");

        } catch (ParseException e) {
            System.err.println("Erro na formatação da data: A data deve ser dd/MM/yyyy. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro técnico de SQL ao salvar manutenção: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Manutencao> listarManutencoes() {
        String sql = "SELECT id, nome_empresa, data_ultima_manutencao, nome_equipamento, id_telefone, id_endereco FROM manutencao";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Manutencao> manutencoes = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return manutencoes;

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Manutencao manutencao = new Manutencao();
                manutencao.setId(rs.getInt("id"));
                manutencao.setNomeEmpresa(rs.getString("nome_empresa"));
                manutencao.setDataUltimaManutencao(rs.getString("data_ultima_manutencao"));
                manutencao.setNomeEquipamento(rs.getString("nome_equipamento"));
                manutencao.setIdTelefone(rs.getInt("id_telefone"));
                manutencao.setIdEndereco(rs.getInt("id_endereco"));

                manutencoes.add(manutencao);
            }
        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar manutenções: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return manutencoes;
    }
}

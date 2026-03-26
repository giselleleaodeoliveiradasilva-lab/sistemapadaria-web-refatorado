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
import sistemapadaria.model.Funcionario;
import sistemapadaria.util.Conexao;

public class FuncionarioDAO {
    private static final SimpleDateFormat FORMATO_BD = new SimpleDateFormat("yyyy-MM-dd");

    private String formatarDataParaBD(String dataJava) throws ParseException {
        if (dataJava == null || dataJava.isEmpty()) {
            return null;
        }
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatoEntrada.parse(dataJava);
        return FORMATO_BD.format(data);
    }

    public void salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome_completo, data_admissao, horario_entrada, horario_saida, perfil, login, senha, id_telefone, id_endereco) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;
        String dataAdmissaoBD = null;

        try {
            dataAdmissaoBD = formatarDataParaBD(funcionario.getDataAdmissao());
            con = Conexao.getConnection();
            
            if (con == null) return;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNomeCompleto());
            stmt.setString(2, dataAdmissaoBD);
            stmt.setString(3, funcionario.getHorarioEntrada());
            stmt.setString(4, funcionario.getHorarioSaida());
            stmt.setString(5, funcionario.getPerfil());
            stmt.setString(6, funcionario.getLogin());
            stmt.setString(7, funcionario.getSenha());
            stmt.setInt(8, funcionario.getIdTelefone());
            stmt.setInt(9, funcionario.getIdEndereco());

            stmt.executeUpdate();
            System.out.println("✅ Funcionário '" + funcionario.getNomeCompleto() + "' salvo com sucesso!");

        } catch (ParseException e) {
            System.err.println("Erro na formatação da data: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar funcionário: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Funcionario> listarFuncionarios() {
        String sql = "SELECT id, nome_completo, data_admissao, horario_entrada, horario_saida, perfil, login, senha, id_telefone, id_endereco FROM funcionario";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) return funcionarios;

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNomeCompleto(rs.getString("nome_completo"));
                funcionario.setDataAdmissao(rs.getString("data_admissao"));
                funcionario.setHorarioEntrada(rs.getString("horario_entrada"));
                funcionario.setHorarioSaida(rs.getString("horario_saida"));
                funcionario.setPerfil(rs.getString("perfil"));
                funcionario.setLogin(rs.getString("login"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setIdTelefone(rs.getInt("id_telefone"));
                funcionario.setIdEndereco(rs.getInt("id_endereco"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }
}


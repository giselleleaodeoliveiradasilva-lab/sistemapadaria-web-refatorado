/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemapadaria.model.Usuario;
import sistemapadaria.util.Conexao;

public class UsuarioDAO {
    public Usuario autenticar(String login, String senha) {
        // SQL focado no mapeamento correto dos campos do banco
        String sql = "SELECT id, nome_usuario, perfil FROM usuario WHERE nome_usuario = ? AND senha = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            con = Conexao.getConnection();
            if (con == null) return null;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                // Note que o banco usa 'nome_usuario', mas o seu Model usa 'login'
                usuario.setLogin(rs.getString("nome_usuario"));
                usuario.setPerfil(rs.getString("perfil"));
                
                System.out.println("✅ Usuário '" + login + "' autenticado com sucesso!");
            } else {
                System.out.println("⚠️ Tentativa de login inválida para o usuário: " + login);
            }

        } catch (SQLException e) {
            System.err.println("Erro técnico de SQL na autenticação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado no processo de login: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return usuario;
    }
}


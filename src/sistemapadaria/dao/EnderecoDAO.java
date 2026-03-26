
package sistemapadaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistemapadaria.model.Endereco; // Importa o modelo
import sistemapadaria.util.Conexao;   // Importa a conexão

/**
 * Classe refatorada para o sistema Web.
 * Removidas dependências de interface gráfica (Swing).
 */
public class EnderecoDAO {

    public int salvarEndereco(Endereco endereco) {
        String sql = "INSERT INTO endereco (rua, numero, complemento, bairro, cidade, estado, cep) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idGerado = 0;

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return 0;
            }

            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setString(7, endereco.getCep());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
            
            System.out.println("Endereço salvo com sucesso no banco de dados.");

        } catch (SQLException e) {
            // Em sistemas Web, usamos logs no console em vez de janelas gráficas
            System.err.println("Erro técnico ao salvar o endereço: " + e.getMessage());

        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return idGerado;
    }

    public List<Endereco> listarEnderecos() {
        String sql = "SELECT id, rua, numero, complemento, bairro, cidade, estado, cep FROM endereco";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Endereco> enderecos = new ArrayList<>();

        try {
            con = Conexao.getConnection();
            if (con == null) {
                return enderecos;
            }

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();

                endereco.setId(rs.getInt("id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCep(rs.getString("cep"));

                enderecos.add(endereco);
            }

        } catch (SQLException e) {
            System.err.println("Erro técnico ao listar endereços: " + e.getMessage());

        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return enderecos;
    }
}

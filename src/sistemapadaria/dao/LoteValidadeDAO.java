/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sistemapadaria.model.LoteValidade;
import sistemapadaria.util.Conexao;


public class LoteValidadeDAO {
    private static final SimpleDateFormat FORMATO_BD = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat FORMATO_JAVA = new SimpleDateFormat("dd/MM/yyyy");

    // Método auxiliar para converter a data do formato brasileiro para o banco
    private String formatarParaBD(String dataString) throws ParseException {
        if (dataString == null || dataString.isEmpty()) return null;
        Date data = FORMATO_JAVA.parse(dataString);
        return FORMATO_BD.format(data);
    }

    public void salvarLote(LoteValidade lote) {
        String sql = "INSERT INTO lote_validade (data_fabricacao, data_validade, id_produto, quantidade) "
                   + "VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            if (con == null) return;

            stmt = con.prepareStatement(sql);

            // Convertendo as datas antes de enviar para o banco
            stmt.setString(1, formatarParaBD(lote.getDataFabricacao()));
            stmt.setString(2, formatarParaBD(lote.getDataValidade()));
            stmt.setInt(3, lote.getIdProduto());
            stmt.setInt(4, lote.getQuantidade());

            stmt.executeUpdate();
            
            System.out.println("✅ Lote de validade registrado com sucesso!");

        } catch (ParseException e) {
            System.err.println("Erro: Formato de data inválido (use dd/MM/yyyy). " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar lote: " + e.getMessage());
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.teste;
import sistemapadaria.dao.UsuarioDAO;
import sistemapadaria.dao.ProdutoDAO;
import sistemapadaria.model.Usuario;
import sistemapadaria.model.Produto;
import java.util.List;


public class TesteSistema {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTES DA ETAPA 6 ---");

        // 1. Teste de Autenticação (Ajuste o login e senha para um que exista no seu Banco)
        System.out.println("\n[Teste 1: Autenticação]");
        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario user = userDAO.autenticar("gerente", "123"); // Coloque dados reais aqui

        if (user != null) {
            System.out.println("✅ Sucesso: Usuário " + user.getLogin() + " encontrado!");
            System.out.println("Perfil: " + user.getPerfil());
        } else {
            System.out.println("❌ Falha: Usuário não encontrado ou senha incorreta.");
        }

        // 2. Teste de Listagem de Produtos
        System.out.println("\n[Teste 2: Listagem de Produtos]");
        ProdutoDAO prodDAO = new ProdutoDAO();
        List<Produto> lista = prodDAO.listarProdutos();

        if (!lista.isEmpty()) {
            System.out.println("✅ Sucesso: " + lista.size() + " produtos encontrados no banco.");
            // Mostra o primeiro produto da lista como exemplo
            System.out.println("Exemplo: " + lista.get(0).getNome() + " - R$ " + lista.get(0).getPreco());
        } else {
            System.out.println("⚠️ Aviso: A lista de produtos está vazia (mas a conexão funcionou).");
        }

        System.out.println("\n--- FIM DOS TESTES ---");
    }
}


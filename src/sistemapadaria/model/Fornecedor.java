/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.model;

/**
 *
 * @author eberf
 */
public class Fornecedor {
     private int id;
    private String nome;
    private String dataUltimoPedido;
    private String dataEntrega;
    private String cnpj;
    private int idTelefone;
    private int idEndereco;

    // Construtor Padrão
    public Fornecedor() {
    }

    // --- Métodos Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataUltimoPedido() {
        return dataUltimoPedido;
    }

    public void setDataUltimoPedido(String dataUltimoPedido) {
        this.dataUltimoPedido = dataUltimoPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
    
    /**
     * Sobrescreve o método toString para retornar apenas o nome.
     * Isso é essencial para que o JComboBox exiba o nome do fornecedor corretamente.
     */
    @Override
    public String toString() {
        return this.nome;
    }
}


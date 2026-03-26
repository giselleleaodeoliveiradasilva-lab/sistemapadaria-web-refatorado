/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.model;

/**
 *
 * @author eberf
 */
public class Vencido {
    private int id;
    private String nome;
    private String dataDescarte;
    private int idProduto;    // Chave estrangeira para o produto descartado
    private int quantidade;   // Quantidade descartada

    // Construtor Padrão
    public Vencido() {
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

    public String getDataDescarte() {
        return dataDescarte;
    }

    public void setDataDescarte(String dataDescarte) {
        this.dataDescarte = dataDescarte;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

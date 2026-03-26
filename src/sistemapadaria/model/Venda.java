/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.model;

/**
 *
 * @author eberf
 */
public class Venda {
     private int id;
    private String dataHora;
    private double valorTotal;
    private int idFuncionario;      // Chave estrangeira para o funcionário que realizou a venda
    private int idMetodoPagamento;  // Chave estrangeira para o método de pagamento

    // Construtor Padrão
    public Venda() {
    }

    // --- Métodos Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdMetodoPagamento() {
        return idMetodoPagamento;
    }

    public void setIdMetodoPagamento(int idMetodoPagamento) {
        this.idMetodoPagamento = idMetodoPagamento;
    }
}


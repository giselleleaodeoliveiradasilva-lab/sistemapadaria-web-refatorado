/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapadaria.model;

/**
 *
 * @author eberf
 */
public class Manutencao {
     private int id;
    private String nomeEmpresa;
    private String dataUltimaManutencao;
    private String nomeEquipamento;
    private int idTelefone;   // Chave estrangeira para a tabela 'telefone'
    private Integer idEndereco;   // Chave estrangeira para a tabela 'endereco'

    // Construtor Padrão
    public Manutencao() {
    }

    // --- Métodos Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    public void setDataUltimaManutencao(String dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }
}

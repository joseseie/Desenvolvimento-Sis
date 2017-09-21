/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Seie José
 */
public class ModeloCidade {
    
    private int cod;
    private String nome;
    private int cod_provincia;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_provincia() {
        return cod_provincia;
    }

    /**
     * @param cod_provincia the cod_provincia to set
     */
    public void setCod_provincia(int cod_provincia) {
        this.cod_provincia = cod_provincia;
    }
    
    
}

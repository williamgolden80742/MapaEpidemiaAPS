/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author William
 */
public class CurrentCidade {
    private static int  idCidade = 0;
    private static String nomeCidade = null;
    
    public void setCidade (String nome, int id) {
       this.idCidade = id;
       this.nomeCidade = nome;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }
    
}

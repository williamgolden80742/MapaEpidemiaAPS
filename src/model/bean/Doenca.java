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
public class Doenca extends Exame {
    private int IdDoenca;
    
    private String nomeDoenca;

    public int getIdDoenca() {
        return IdDoenca;
    }    
    public void setIdDoenca(int IdDoenca) {
        this.IdDoenca = IdDoenca;
    }

    public String getNomeDoenca() {
        return nomeDoenca;
    }

    public void setNomeDoenca(String nomeDoenca) {
        this.nomeDoenca = nomeDoenca;
    }   
}

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
public class Exame extends Paciente {

    private String datadeCriacaoE;
    private int IdExame;
    
    public int getIdExame() {
        return IdExame;
    }

    public void setIdExame(int IdExame) {
        this.IdExame = IdExame;
    }

    public String getDatadeCriacaoE() {
        return datadeCriacaoE;
    }

    public void setDatadeCriacaoE(String datadeCriacaoE) {
        this.datadeCriacaoE = datadeCriacaoE;
    }

    
}

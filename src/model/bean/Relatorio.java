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
public class Relatorio extends Resultado {
    private int casos;
    private double percent;
    private String dataCasos;

    public String getDataCasos() {
        return dataCasos;
    }

    public void setDataCasos(String dataCasos) {
        this.dataCasos = dataCasos;
    }
    
    
    public double getPercent() {
        return percent;
    }

    public void setPercent(float c,float p) {
        if (p != 0) { 
           this.percent=(c/p)*100;
        } else {
            this.percent = 0; 
        }
    }
    
    public int getCasos() {
        return casos;
    }

    public void setCasos(int casos) {
        this.casos = casos;
    }
    
}

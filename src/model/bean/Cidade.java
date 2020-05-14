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
public class Cidade extends Estado {

    private String cidadeNome;
    private int cidadeId; 
    private static String currentCidadeNome;
    private static int currentCidadeId;       
    private int populacao;   
    
    public void setCurrentCidade (String nome, int id) {
       this.currentCidadeNome = nome;
       this.currentCidadeId = id;
    }    
    
    public String getCurrentCidadeNome() {
        return currentCidadeNome;
    }

    public int getCurrentCidadeId() {
        return currentCidadeId;
    }

    public void setCurrentCidadeNome(String currentCidadeNome) {
        Cidade.currentCidadeNome = currentCidadeNome;
    }

    public void setCurrentCidadeId(int currentCidadeId) {
        Cidade.currentCidadeId = currentCidadeId;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }
    
    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public int getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(int cidadeId) {
        this.cidadeId = cidadeId;
    }
        
    
}

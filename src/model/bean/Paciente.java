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
public class Paciente extends Cidade {
    private int id;
    private String nome;
    private String data;
    private String nasc;
    private int falecido;
    private char sexo;

    public int getFalecido() {
        return falecido;
    }

    public void setFalecido(int falecido) {
        this.falecido = falecido;
    }
    
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


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNasc() {
        return nasc;
    }

    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    
}

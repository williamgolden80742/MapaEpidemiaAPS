/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author William
 */
public class RandonCasos {
    
   public void create() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;      
        
        try {
            String nomes[] = {"Miguel","Sophia","Davi","Alice","Arthur","Julia","Pedro","Isabella","Gabriel","Manuela","Bernardo","Laura","Lucas","Luiza","Matheus","Valentina","Rafael","Giovanna","Heitor","Maria Eduarda","Enzo","Helena","Guilherme","Beatriz","Nicolas","Maria Luiza","Lorenzo","Lara","Gustavo","Mariana","Felipe","Nicole","Samuel","Rafaela","João Pedro","Heloísa","Daniel","Isadora","Vitor","Lívia","Leonardo","Maria Clara","Henrique","Ana Clara","Theo","Lorena","Murilo","Gabriela","Eduardo","Yasmin","Pedro Henrique","Isabelly","Pietro","Sarah","Cauã","Ana Julia","Isaac","Letícia","Caio","Ana Luiza","Vinicius","Melissa","Benjamin","Marina","João","Clara","Lucca","Cecília","João Miguel","Esther","Bryan","Emanuelly","Joaquim","Rebeca","João Vitor","Ana Beatriz","Thiago","Lavínia","Antônio","Vitória","Davi Lucas","Bianca","Francisco","Catarina","Enzo Gabriel","Larissa","Bruno","Maria Fernanda","Emanuel","Fernanda","João Gabriel","Amanda","Ian","Alícia","Davi Luiz","Carolina","Rodrigo","Agatha","Otávio","Gabrielly"}; 
            int cidade[] = {5270,4920,5252,5095,5038};
            Random gerador = new Random();
            for (int i = 0; i < 10000; i++) {
                stmt = con.prepareStatement("INSERT INTO Pacientes (Idpaciente,nomecompleto,idCidade)VALUES("+(i+20220)+",'"+nomes[gerador.nextInt(100)]+"',"+cidade[gerador.nextInt(5)]+");");  
                stmt.executeUpdate();
                stmt = con.prepareStatement("INSERT INTO Exames (IdExame,Idpaciente)VALUES("+(i+20220)+","+(i+20220)+");"); 
                stmt.executeUpdate();
                stmt = con.prepareStatement("INSERT INTO Positivos (IdExame,IdDoenca,resultado)VALUES("+(i+20220)+","+(2+gerador.nextInt(4))+",1);");           
                stmt.executeUpdate();
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Resultado;

/**
 *
 * @author William
 */
public class ResultadoDAO {

    private List<Resultado> read (String request) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;


        List<Resultado> resultados = new ArrayList<>();

        try {
            stmt = con.prepareStatement(request);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Resultado resultado = new Resultado();         
                resultado.setIdDoenca(rs.getInt("IdDoenca"));                
                resultado.setNomeDoenca(rs.getString("nomeDoenca"));  
                try {
                    resultado.setResultado(rs.getInt("resultado"));
                } catch (Exception ex) {
                }
                resultados.add(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return resultados;
    } 
    
    
    public List<Resultado> read (int E) {
        String request = " Positivos.IdExame = 0";               
        if (E != 0) {
            request = " Positivos.IdExame = "+E;
        } 
        return read("SELECT * FROM Positivos inner join doencas on doencas.IdDoenca = Positivos.IdDoenca where "+request);
    }   
       
    
    public List<Resultado> read () {
        return read ("SELECT * FROM doencas");  
    }
    
    public void salvar(String request) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("INSERT INTO POSITIVOS "+request);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    
    }
    
    public void salvar(int idEx, String[][] positivos, int height) {
          String request = "";
          delete (idEx);
          for (int i=0;i<height;i++) {
                request = " (IdExame,resultado,IdDoenca) values ("+idEx+","+positivos[i][0]+","+positivos[i][1]+")";
                salvar(request);

          }
    }
    
    public void delete (int idEx) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete FROM POSITIVOS WHERE IdExame = '"+idEx+"'");
            stmt.executeUpdate();          
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }     
}

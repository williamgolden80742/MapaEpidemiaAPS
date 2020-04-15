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
import model.bean.Exame;
import model.bean.Paciente;

/**
 *
 * @author William
 */
public class ExameDAO {
    
    private static String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
    
    public List<Exame> read () {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Exame> exames = new ArrayList<>();

        try {
            try {
                stmt = con.prepareStatement("SELECT * FROM Exames inner join pacientes on pacientes.Idpaciente = Exames.Idpaciente ");
                rs = stmt.executeQuery();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            while (rs.next()) {
                Exame exame = new Exame();        
                exame.setIdExame(rs.getInt("IdExame"));                
                exame.setId(rs.getInt("Idpaciente"));    
                exame.setNome(rs.getString("nomecompleto"));   
                exames.add(exame);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return exames;
    } 
    
        public List<Exame> readResultado (int idP) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String request = "";
        
        if(idP != 0){
            request = " inner join Positivos on Positivos.IdExame = (SELECT IdExame FROM Exames where Idpaciente like "+idP+" order by Idpaciente desc limit 1)";
        } else {
            request = "";    
        }
        
        List<Exame> exames = new ArrayList<>();

        try {
            try {
                stmt = con.prepareStatement("SELECT * FROM Exames "+request);
                rs = stmt.executeQuery();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            while (rs.next()) {
                Exame exame = new Exame();        
                exame.setIdExame(rs.getInt("IdExame"));                
                exame.setId(rs.getInt("Idpaciente"));       
                exames.add(exame);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return exames;
    }  
    
    public int readLastID (int idP) {
        Exame exame = new Exame();
        for (Exame e : readResultado (idP)) {
            exame.setIdExame(e.getIdExame());
            System.out.println(e.getIdExame());
        }
        return (exame.getIdExame() == 0)?null:exame.getIdExame();
    } 

     public void create(int id) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;      
        
        try {
            stmt = con.prepareStatement("INSERT INTO Exames (Idpaciente)VALUES("+id+")");
            stmt.executeUpdate();
            setStatus("Exame criado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
       
    
}

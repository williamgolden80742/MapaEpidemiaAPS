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
import model.bean.Paciente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author William
 */
public class PacienteDAO {
    
    private static String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void clearStatus() {
        this.status = "";
    }    
    
    public void create(Paciente p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;      
        
        try {
 
            stmt = con.prepareStatement("INSERT INTO Pacientes (nomecompleto,dataNascimento,sexo,idCidade,falecido)VALUES(?,?,?,?,?)");
            stmt.setString(1,p.getNome());            
            stmt.setString(2,p.getNasc());
            stmt.setString(3,String.valueOf(p.getSexo()));
            stmt.setInt(4,p.getCidadeId());
            stmt.setInt(5,p.getFalecido());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
 
    public List<Paciente> read (String request) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Paciente> pacientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement(request);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();         
                paciente.setId(rs.getInt("Idpaciente"));  
                try  {
                    paciente.setCidadeNome(rs.getString("nomeCidade"));
                    paciente.setSexo( rs.getString("sexo").charAt(0) );                      
                    paciente.setCidadeId(rs.getInt("idCidade")); 
                } catch (Exception ex) {
                
                }
                paciente.setNasc(rs.getString("dataNascimento"));  
                paciente.setNome(rs.getString("nomecompleto"));        
                paciente.setFalecido(rs.getInt("falecido"));                     
                paciente.setData(rs.getString("datadeCriacaoP"));                 
                pacientes.add(paciente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pacientes;
    }

    public List<Paciente> read (String nome,boolean b) {    
        return read("SELECT * FROM Pacientes WHERE nomecompleto like '"+nome+"%'");
    }
    
    public Paciente readById(int id) {  
        Paciente paciente = new Paciente();

        for(Paciente p : read("SELECT * FROM Pacientes Inner Join cidade ON cidade.idCidade = Pacientes.idCidade WHERE Idpaciente like '"+id+"'")){
                paciente.setNome(p.getNome());
                paciente.setCidadeNome(p.getCidadeNome());  
                paciente.setCidadeId(p.getCidadeId());   
                paciente.setNasc(p.getNasc());
                paciente.setSexo(p.getSexo());   
                paciente.setFalecido(p.getFalecido());
        }
        return paciente;        
    }
    
    public void delete (Paciente p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete FROM Pacientes WHERE Idpaciente = ?");
            stmt.setInt(1,p.getId());
            stmt.executeUpdate();
            setStatus("Deletado com sucesso!");            
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }  


    public void update(Paciente p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;      
        
        try {
            stmt = con.prepareStatement("UPDATE Pacientes SET nomecompleto = ?, dataNascimento = ?, sexo = ?, idCidade = ?, falecido = ? WHERE Idpaciente = ?");
            stmt.setString(1,p.getNome());        
            stmt.setString(2,p.getNasc());
            stmt.setString(3,String.valueOf(p.getSexo()));
            stmt.setInt(4,p.getCidadeId());   
            stmt.setInt(5,p.getFalecido());             
            stmt.setInt(6,p.getId()); 
            stmt.executeUpdate();
            setStatus("Atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }       
    } 
}

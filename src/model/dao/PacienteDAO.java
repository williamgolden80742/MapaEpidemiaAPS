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
 
            stmt = con.prepareStatement("INSERT INTO Pacientes (nomecompleto,dataNascimento,sexo,idCidade)VALUES(?,?,?,?)");
            stmt.setString(1,p.getNome());            
            stmt.setString(2,p.getNasc());
            stmt.setString(3,String.valueOf(p.getSexo()));
            stmt.setInt(4,p.getCidadeId());
            stmt.executeUpdate();
            setStatus("Criado com sucesso!");
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
                paciente.setNome(rs.getString("nomecompleto"));                    
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

        Connection con = ConnectionFactory.getConnection();   
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Paciente paciente = new Paciente();

        try {
                stmt = con.prepareStatement("SELECT * FROM Pacientes Inner Join cidade ON cidade.idCidade = Pacientes.idCidade WHERE Idpaciente like '%"+id+"%'");
            rs = stmt.executeQuery();

            while (rs.next()) {                  
                paciente.setNome(rs.getString("nomecompleto"));
                paciente.setCidadeNome(rs.getString("nomeCidade"));  
                paciente.setCidadeId(rs.getInt("idCidade"));   
                paciente.setNasc(rs.getString("dataNascimento"));
                System.out.println("BD nasc :  " + rs.getString("dataNascimento") );
                paciente.setSexo(rs.getString("sexo").charAt(0));    
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
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
            stmt = con.prepareStatement("UPDATE Pacientes SET nomecompleto = ?, dataNascimento = ?, sexo = ?, idCidade = ? WHERE Idpaciente = ?");
            stmt.setString(1,p.getNome());        
            stmt.setString(2,p.getNasc());
            stmt.setString(3,String.valueOf(p.getSexo()));
            stmt.setInt(4,p.getCidadeId());             
            stmt.setInt(5,p.getId());              
            stmt.executeUpdate();
            setStatus("Atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }       
    } 
}

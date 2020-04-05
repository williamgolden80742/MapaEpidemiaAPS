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
import javax.swing.JOptionPane;
import model.bean.Paciente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author William
 */
public class PacienteDAO {
    

    public void create(Paciente p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;      
        
        try {
 
            stmt = con.prepareStatement("INSERT INTO Pacientes (nomecompleto,cpf,email,dataNascimento,documento,sexo,telefone,observacao)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getCpf());
            stmt.setString(3,p.getEmail());            
            stmt.setString(4,p.getNasc());
            stmt.setString(5,p.getDoc());
            stmt.setString(6,String.valueOf(p.getSexo()));
            stmt.setString(7,p.getTel());
            stmt.setString(8,p.getObs());   
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public List<Paciente> read (String tel,String cpf) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Paciente> pacientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Pacientes WHERE telefone Like '%"+tel+"%' AND cpf like '%"+cpf+"%'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();         
                paciente.setId(rs.getInt("Idpaciente"));                
                paciente.setNome(rs.getString("nomecompleto"));
                paciente.setTel(rs.getString("telefone"));      
                paciente.setObs(rs.getString("observacao"));                  
                paciente.setData(rs.getString("datadeCriacao"));                 
                pacientes.add(paciente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pacientes;
    }

  
}

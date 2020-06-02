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
import model.bean.Estado;
/**
 *
 * @author William
 */
public class EstadoDAO {

    
    public List<Estado> readUF () {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estado> estados = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT uf FROM Estado");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Estado estado = new Estado();                      
                estado.setUF(rs.getString("uf"));             
                estados.add(estado);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return estados;
    }   
    
    public Estado readIdByUF (String uf) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        Estado estado = new Estado(); 

        try {
            stmt = con.prepareStatement("SELECT * FROM estado where uf like '"+uf+"'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                estado.setEstadoId(rs.getInt("idEstado"));                      
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return estado;
    }      
}

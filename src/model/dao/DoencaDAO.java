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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Doenca;

/**
 *
 * @author William
 */
public class DoencaDAO {
    
    
    public int readId (String nome) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;


        Doenca doencas = new Doenca();

        try {
            stmt = con.prepareStatement("Select IdDoenca from doencas where nomeDoenca like '"+nome+"'");
            rs = stmt.executeQuery();
            while (rs.next()) {       
                doencas.setIdDoenca(rs.getInt("IdDoenca"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return doencas.getIdDoenca();
    }     
}

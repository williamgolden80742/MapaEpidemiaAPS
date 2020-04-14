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

/**
 *
 * @author William
 */
public class ExameDAO {
    public List<Exame> read () {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Exame> exames = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Exames inner join pacientes on pacientes.Idpaciente = Exames.Idpaciente");
            rs = stmt.executeQuery();

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
}

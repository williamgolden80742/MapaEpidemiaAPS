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
import model.bean.Cidade;

/**
 *
 * @author William
 */
public class CidadeDAO {
        
    public List<Cidade> readCidade (int uf) {
        

       // SELECT nome FROM cidade ;
                       
        String request = "INNER JOIN estado ON cidade.estado = '"+uf+"'";
        
        if (uf == 0) { 

            request = ""; 
        }

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cidade> cidades = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT nomeCidade FROM Cidade "+request);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();         
                cidade.setCidadeNome(rs.getString("nomeCidade"));                          
                cidades.add(cidade);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cidades;
    }     
   


    
}

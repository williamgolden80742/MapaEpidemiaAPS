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
public class CidadeDAO extends EstadoDAO { 
 
    public List<Cidade> readCidade (String uf, String nome) {
        
       // SELECT nome FROM cidade ;                 
        String request = "";
        
        if (uf.equals("--")) { 
            request = " "; 
        } else {
            request = " WHERE uf LIKE '"+uf+"'"; 
        }
        if (!nome.equals("")) { 
            request += " AND nomeCidade LIKE '"+nome+"%'";  
        }

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cidade> cidades = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT idCidade, uf, nomeCidade FROM Cidade INNER JOIN estado ON cidade.estado = estado.idEstado "+request);
            rs = stmt.executeQuery();            
            while (rs.next()) {
                Cidade cidade = new Cidade(); 
                cidade.setCidadeId(rs.getInt("idCidade"));     
                cidade.setUF(rs.getString("uf"));       
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
    
    public List<Cidade> readCidade (String uf) {
        return readCidade(uf,"") ;
    }
    
    public List<Cidade> readCidadeNome (String nome) {
        return readCidade("--",nome);
    } 
    
   public Cidade readCurrentCidade (int uf, String nome) {
                      
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Cidade cidade = new Cidade();

        try {
            stmt = con.prepareStatement("SELECT idCidade, uf, nomeCidade FROM Cidade INNER JOIN estado ON cidade.estado = '"+uf+"' WHERE nomeCidade LIKE '"+nome+"%'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cidade.setCidadeId(rs.getInt("idCidade"));         
                cidade.setCidadeNome(rs.getString("nomeCidade"));                          
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cidade;
    }     
    
}

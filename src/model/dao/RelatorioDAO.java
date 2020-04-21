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
import model.bean.Relatorio;

/**
 *
 * @author William
 */
public class RelatorioDAO {
    
   public List<Relatorio> read (String request) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String date = "";
        
        if (!request.equals("")) {
            date = "and positivos.datadeCriacaoPS like '"+request+"%'";
        }

        List<Relatorio> relatorios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT cidade.nomeCidade, cidade.populacao, COUNT(DISTINCT pacientes.Idpaciente), DATE_FORMAT(positivos.datadeCriacaoPS, '%d/%m/%Y') FROM exames INNER JOIN positivos ON positivos.IdExame = exames.IdExame inner join pacientes on pacientes.Idpaciente = exames.Idpaciente inner join cidade on cidade.idCidade = pacientes.idCidade WHERE positivos.resultado = 1 "+date+" GROUP by date(positivos.datadeCriacaoPS), cidade.idCidade ORDER by pacientes.idCidade, positivos.datadeCriacaoPS");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Relatorio relatorio = new Relatorio();      
//                relatorio.setNome(rs.getString("nomeCompleto"));                  
                relatorio.setCidadeNome(rs.getString("nomeCidade"));                
                relatorio.setPopulacao(rs.getInt("populacao"));  
//                relatorio.setNomeDoenca(rs.getString("nomeDoenca"));               
                relatorio.setCasos(rs.getInt("COUNT(DISTINCT pacientes.Idpaciente)"));    
                relatorio.setPercent(rs.getInt("COUNT(DISTINCT pacientes.Idpaciente)"),rs.getInt("populacao"));
                relatorio.setDataCasos(rs.getString("DATE_FORMAT(positivos.datadeCriacaoPS, '%d/%m/%Y')"));
                relatorios.add(relatorio);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return relatorios;
   }
   
   public List<Relatorio> read () {
       return read ("");
   }

   public List<Relatorio> readDate() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;


        List<Relatorio> relatorios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT DISTINCT DATE( positivos.datadeCriacaoPS) AS dataP FROM positivos");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Relatorio relatorio = new Relatorio();                        
                relatorio.setDataCasos(rs.getString("dataP")); 
                relatorios.add(relatorio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return relatorios;
    }    
}

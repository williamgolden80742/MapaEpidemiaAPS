/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.text.DecimalFormat;
import model.bean.Relatorio;
import model.dao.RelatorioDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author William
 */
public final class Grafico {
    
    RelatorioDAO rdao = new RelatorioDAO();
    
    public Image criarGrafico(String request, int Width , int Height) throws IOException {
        DefaultCategoryDataset linha = new DefaultCategoryDataset();
        JFreeChart grafico;
        linha.clear();
        DecimalFormat df =  new DecimalFormat();
        df.setMaximumFractionDigits(3);  
        rdao.read(request).forEach((Relatorio r) -> {  
            linha.addValue(Double.parseDouble(df.format(r.getPercent()).replace(",",".")),r.getCidadeNome()+" ("+r.getPopulacao()+")",r.getDataCasos());
        });   
        if (request.equals("")) {
            grafico = ChartFactory.createLineChart("Evolução do Casos \n","Dias","Porcentagem da população",linha,PlotOrientation.VERTICAL,true,true,true);   
            grafico.getPlot().setBackgroundPaint(Color.DARK_GRAY);
        } else {
            grafico = ChartFactory.createBarChart3D("Casos","Dia","Porcentagem da população",linha,PlotOrientation.VERTICAL,true,true,true);    
            grafico.getPlot().setBackgroundPaint(Color.DARK_GRAY);
        }
        return grafico.createBufferedImage(Width,Height);  
    }  
    
    public Image criarGrafico(String request) throws IOException {
        return criarGrafico(request,600,360);
    }    

    public Image criarGraficoMorte(int Width , int Height) throws IOException {
        DefaultPieDataset  linha = new DefaultPieDataset();
        JFreeChart grafico;
        linha.clear();

        rdao.readMortes().forEach((Relatorio r) -> {  
            linha.setValue(r.getCidadeNome()+" ("+r.getCasos()+") ",r.getCasos());
        });          
        grafico = ChartFactory.createPieChart("Mortes por cidade", linha); 
        grafico.getPlot().setBackgroundPaint(Color.DARK_GRAY);  
        return grafico.createBufferedImage(Width,Height);  
    }  
    
    public Image criarGraficoMorte() throws IOException {
        return criarGraficoMorte(600,360);
    }    
        
    
    public Image criarGrafico() throws IOException {
        return criarGrafico("",600,360);
    }    
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Relatorio;
import model.dao.RelatorioDAO;

/**
 *
 * @author William
 */
public class Relatorios extends javax.swing.JFrame {

    /**
     * Creates new form Relatorios
     */
    public Relatorios() {
        initComponents();
        readJTable();
        setDate();
        DefaultTableModel modelo = (DefaultTableModel) relatorioTable.getModel();
        relatorioTable.setRowSorter(new TableRowSorter(modelo));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        relatorio = new javax.swing.JTabbedPane();
        graficos = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        relatorioTable = new javax.swing.JTable();
        data = new javax.swing.JComboBox<>();

        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        relatorio.addTab("Gráfico", graficos);

        relatorioTable.setAutoCreateRowSorter(true);
        relatorioTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cidade", "População", "Evolução", "Porcentagem", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(relatorioTable);
        if (relatorioTable.getColumnModel().getColumnCount() > 0) {
            relatorioTable.getColumnModel().getColumn(0).setResizable(false);
            relatorioTable.getColumnModel().getColumn(1).setResizable(false);
            relatorioTable.getColumnModel().getColumn(2).setResizable(false);
            relatorioTable.getColumnModel().getColumn(3).setResizable(false);
        }

        relatorio.addTab("Evolução", jScrollPane1);

        data.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE DATA" }));
        data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(data, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(relatorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(relatorio, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        readJTable();
    }//GEN-LAST:event_formWindowActivated

    private void dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataActionPerformed
        readJTable();
    }//GEN-LAST:event_dataActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    RelatorioDAO rdao = new RelatorioDAO();
    
    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) relatorioTable.getModel();
        modelo.setNumRows(0);
        DecimalFormat df =  new DecimalFormat();
        df.setMaximumFractionDigits(6);
        int soma = 0;
        double somaPercent = 0;
        String cidadeAnterior = "", cidadeAtual = "";
        for (Relatorio r : rdao.read(currentDate())) {
            cidadeAtual = r.getCidadeNome();
            if (cidadeAtual.equals(cidadeAnterior)) {
                soma += r.getCasos();
                somaPercent += r.getPercent();
            } else {
                soma = r.getCasos();
                somaPercent = r.getPercent();
            }
            modelo.addRow(new Object[]{    
                r.getCidadeNome(),      
                r.getPopulacao(),
                soma,
                df.format(somaPercent)+"%",
                r.getDataCasos()
            });
            cidadeAnterior = cidadeAtual; 
        }
    }    
    
    public String currentDate() {
        String value = "";
        if (!data.getSelectedItem().equals("SELECIONE DATA")) {
            value = (String) data.getSelectedItem();
        }
        return value;
    }
    
    public void setDate() {   
        for (Relatorio e : rdao.readDate()) {
            data.addItem(e.getDataCasos());          
        } 
    }     
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> data;
    private javax.swing.JTabbedPane graficos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTabbedPane relatorio;
    private javax.swing.JTable relatorioTable;
    // End of variables declaration//GEN-END:variables
}

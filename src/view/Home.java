/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Toolkit;
import model.dao.RandonCasos;

/**
 *
 * @author William
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    private Pacientes paciente = new Pacientes();
    private Relatorios relatorio = new Relatorios(); 
    private Cidades cidades = new Cidades();
    private Exames exame = new Exames();    
    private RandonCasos rand = new RandonCasos();

    public Home() {         

        initComponents();
        setIconTop ();
//        rand.create();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Paciente = new javax.swing.JToggleButton();
        relatorios = new javax.swing.JButton();
        exames = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viral.City-5");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setResizable(false);

        Paciente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Paciente.setForeground(new java.awt.Color(0, 0, 0));
        Paciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pacienteAdd.png"))); // NOI18N
        Paciente.setToolTipText("Pacientes");
        Paciente.setBorder(null);
        Paciente.setBorderPainted(false);
        Paciente.setFocusPainted(false);
        Paciente.setVerifyInputWhenFocusTarget(false);
        Paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PacienteActionPerformed(evt);
            }
        });

        relatorios.setForeground(new java.awt.Color(51, 102, 255));
        relatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/relatorio_Small.png"))); // NOI18N
        relatorios.setToolTipText("Relatorios");
        relatorios.setBorder(null);
        relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoriosActionPerformed(evt);
            }
        });

        exames.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exame.png"))); // NOI18N
        exames.setToolTipText("Exames");
        exames.setBorder(null);
        exames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examesActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(Paciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(exames, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(relatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exames, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(relatorios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(Paciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void PacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PacienteActionPerformed
        paciente.setVisible(true);    
    }//GEN-LAST:event_PacienteActionPerformed

    private void examesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examesActionPerformed
        exame.setVisible(true);
    }//GEN-LAST:event_examesActionPerformed

    private void relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoriosActionPerformed
        relatorio.setVisible(true);
    }//GEN-LAST:event_relatoriosActionPerformed

    private void setIconTop () {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/Viral.City-5.png")));
    }    
    /**
     * @param args the command line arguments
     */
// 
 public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Paciente;
    private javax.swing.JToggleButton exames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton relatorios;
    // End of variables declaration//GEN-END:variables
}

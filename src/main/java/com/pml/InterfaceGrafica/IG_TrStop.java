/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.InterfaceGrafica;

import com.pml.Configuracoes.ConfigOrdens;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Felipe Mattos
 */
public class IG_TrStop extends javax.swing.JFrame {

    private static boolean podeSimular = false;
    private static boolean relatorioCompleto = false;
    
    /**
     * Creates new form IG_Indicadores
     */
    public IG_TrStop() {
        initComponents();
        verificaInterface();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTemTrStop = new javax.swing.JCheckBox();
        jTrStop_PtsAcion = new javax.swing.JFormattedTextField();
        jTrStop_PtsAcionFin = new javax.swing.JFormattedTextField();
        jPassojTrStop_FreqAtualiza = new javax.swing.JFormattedTextField();
        jTrStop_PtsMin = new javax.swing.JFormattedTextField();
        jTrStop_PtsMinFin = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jPassoTrStop_PtsMin = new javax.swing.JFormattedTextField();
        jTrStop_FreqAtualiza = new javax.swing.JFormattedTextField();
        jTrStop_FreqAtualizaFin = new javax.swing.JFormattedTextField();
        jPassoTrStop_PtsAcion = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Trailing Stop");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Aplicar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Trailing Stop:"));

        jTemTrStop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemTrStop.setText("Trailing Stop");
        jTemTrStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });

        jTrStop_PtsAcion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jTrStop_PtsAcion.setText("1");
        jTrStop_PtsAcion.setEnabled(false);

        jTrStop_PtsAcionFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jTrStop_PtsAcionFin.setText("1");
        jTrStop_PtsAcionFin.setEnabled(false);

        jPassojTrStop_FreqAtualiza.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassojTrStop_FreqAtualiza.setText("1");
        jPassojTrStop_FreqAtualiza.setToolTipText("");
        jPassojTrStop_FreqAtualiza.setEnabled(false);

        jTrStop_PtsMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jTrStop_PtsMin.setText("1");
        jTrStop_PtsMin.setEnabled(false);

        jTrStop_PtsMinFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jTrStop_PtsMinFin.setText("1");
        jTrStop_PtsMinFin.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Passo");
        jLabel12.setEnabled(false);

        jPassoTrStop_PtsMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoTrStop_PtsMin.setText("1");
        jPassoTrStop_PtsMin.setToolTipText("");
        jPassoTrStop_PtsMin.setEnabled(false);

        jTrStop_FreqAtualiza.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jTrStop_FreqAtualiza.setText("1");
        jTrStop_FreqAtualiza.setEnabled(false);

        jTrStop_FreqAtualizaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jTrStop_FreqAtualizaFin.setText("1");
        jTrStop_FreqAtualizaFin.setEnabled(false);

        jPassoTrStop_PtsAcion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoTrStop_PtsAcion.setText("1");
        jPassoTrStop_PtsAcion.setToolTipText("");
        jPassoTrStop_PtsAcion.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Final");
        jLabel8.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Pts Acionamento:");
        jLabel9.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Pts Mín Desejado:");
        jLabel11.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Freq. Atualização:");
        jLabel13.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTemTrStop)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTrStop_FreqAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTrStop_FreqAtualizaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTrStop_PtsMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTrStop_PtsMinFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTrStop_PtsAcion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jTrStop_PtsAcionFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPassojTrStop_FreqAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPassoTrStop_PtsMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPassoTrStop_PtsAcion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTemTrStop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTrStop_PtsAcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTrStop_PtsAcionFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jPassoTrStop_PtsAcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTrStop_PtsMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(jTrStop_PtsMinFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPassoTrStop_PtsMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTrStop_FreqAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(jTrStop_FreqAtualizaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPassojTrStop_FreqAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean
        temTrStop = jTemTrStop.isSelected();

        double
        trStop_PtsAcionamento = Double.parseDouble(jTrStop_PtsAcion.getText().replace(",", ".")),
        trStop_PtsAcionamentoFin = Double.parseDouble(jTrStop_PtsAcionFin.getText().replace(",", ".")),
        trStop_Passo_PtsAcionamento = Double.parseDouble(jPassoTrStop_PtsAcion.getText().replace(",", ".")),

        trStop_PtsMin = Double.parseDouble(jTrStop_PtsMin.getText().replace(",", ".")),
        trStop_PtsMinFin = Double.parseDouble(jTrStop_PtsMinFin.getText().replace(",", ".")),
        trStop_Passo_PtsMin = Double.parseDouble(jPassoTrStop_PtsMin.getText().replace(",", ".")),

        trStop_FreqAtualiza = Double.parseDouble(jTrStop_FreqAtualiza.getText().replace(",", ".")),
        trStop_FreqAtualizaFin = Double.parseDouble(jTrStop_FreqAtualizaFin.getText().replace(",", ".")),
        trStop_Passo_FreqAtualiza = Double.parseDouble(jPassojTrStop_FreqAtualiza.getText().replace(",", "."));

        if(trStop_PtsMin >= trStop_PtsAcionamento){
            JOptionPane.showMessageDialog(null, "ERRO! 'PtsMin' não pode ser igual ou maior que 'PtsAcionamento'");
            return;
        }
        if(trStop_FreqAtualiza <= 0){
            JOptionPane.showMessageDialog(null, "ERRO! 'Freq Atualização' tem que ser maior que 0");
            return;
        }
        
        ConfigOrdens.setTrStopPasso(trStop_Passo_PtsAcionamento, trStop_Passo_PtsMin, trStop_Passo_FreqAtualiza);
        ConfigOrdens.setTrStopLoop(temTrStop, trStop_PtsAcionamento, trStop_PtsAcionamentoFin,
                                    trStop_PtsMin, trStop_PtsMinFin,
                                    trStop_FreqAtualiza, trStop_FreqAtualizaFin);

        podeSimular = true;
        JOptionPane.showMessageDialog(null, "Valores Registrados");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void atualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizaInterface
        jTrStop_PtsAcion.setEnabled(jTemTrStop.isSelected());
        jTrStop_PtsAcionFin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jPassoTrStop_PtsAcion.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        
        jTrStop_PtsMin.setEnabled(jTemTrStop.isSelected());
        jTrStop_PtsMinFin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jPassoTrStop_PtsMin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        
        jTrStop_FreqAtualiza.setEnabled(jTemTrStop.isSelected());
        jTrStop_FreqAtualizaFin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jPassojTrStop_FreqAtualiza.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
    }//GEN-LAST:event_atualizaInterface

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private static javax.swing.JLabel jLabel11;
    private static javax.swing.JLabel jLabel12;
    private static javax.swing.JLabel jLabel13;
    private static javax.swing.JLabel jLabel8;
    private static javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JFormattedTextField jPassoTrStop_PtsAcion;
    private static javax.swing.JFormattedTextField jPassoTrStop_PtsMin;
    private static javax.swing.JFormattedTextField jPassojTrStop_FreqAtualiza;
    private static javax.swing.JCheckBox jTemTrStop;
    private static javax.swing.JFormattedTextField jTrStop_FreqAtualiza;
    private static javax.swing.JFormattedTextField jTrStop_FreqAtualizaFin;
    private static javax.swing.JFormattedTextField jTrStop_PtsAcion;
    private static javax.swing.JFormattedTextField jTrStop_PtsAcionFin;
    private static javax.swing.JFormattedTextField jTrStop_PtsMin;
    private static javax.swing.JFormattedTextField jTrStop_PtsMinFin;
    // End of variables declaration//GEN-END:variables

    /**
     * verifica se o ger. de risco foi atualizado
     * @return 
     */
    public static boolean isPodeSimular() {
        return podeSimular;
    }
    
    /**
     * reseta a variavel 'podeSimular'
     */
    public static void resetPodeSimular(){
        podeSimular = false;
    }

    public static void recebeTipoRelatorio(JRadioButton relatorioCompleto){
        IG_TrStop.relatorioCompleto = relatorioCompleto.isSelected();
    }
    
    public static void verificaInterface(){
        jTemTrStop.setSelected(ConfigOrdens.isTemTrStop());
        jTrStop_PtsAcion.setEnabled(jTemTrStop.isSelected());
        jTrStop_PtsAcion.setText(String.valueOf(ConfigOrdens.getTrStopAcionamentoIni()));
        jTrStop_PtsAcionFin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jTrStop_PtsAcionFin.setText(String.valueOf(ConfigOrdens.getTrStopAcionamentoFin()));
        jPassoTrStop_PtsAcion.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jPassoTrStop_PtsAcion.setText(String.valueOf(ConfigOrdens.getPassoTrStop_Acion()));
        
        jTrStop_PtsMin.setEnabled(jTemTrStop.isSelected());
        jTrStop_PtsMin.setText(String.valueOf(ConfigOrdens.getTrStopPtsMinIni()));
        jTrStop_PtsMinFin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jTrStop_PtsMinFin.setText(String.valueOf(ConfigOrdens.getTrStopPtsMinFin()));
        jPassoTrStop_PtsMin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jPassoTrStop_PtsMin.setText(String.valueOf(ConfigOrdens.getPassoTrStop_PtsMin()));
        
        jTrStop_FreqAtualiza.setEnabled(jTemTrStop.isSelected());
        jTrStop_FreqAtualiza.setText(String.valueOf(ConfigOrdens.getTrStopFrequeAtualizaIni()));
        jTrStop_FreqAtualizaFin.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jTrStop_FreqAtualizaFin.setText(String.valueOf(ConfigOrdens.getTrStopFrequeAtualizaFin()));
        jPassojTrStop_FreqAtualiza.setEnabled(jTemTrStop.isSelected() && relatorioCompleto);
        jPassojTrStop_FreqAtualiza.setText(String.valueOf(ConfigOrdens.getPassoTrStop_FreqAtual()));
    }
}
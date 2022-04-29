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
public class IG_GerRisco extends javax.swing.JFrame {

    private static boolean podeSimular = false;
    private static boolean relatorioCompleto = false;
    
    /**
     * Creates new form IG_Indicadores
     */
    public IG_GerRisco() {
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
        jTemPrejMax = new javax.swing.JCheckBox();
        jTemSaldoDesej = new javax.swing.JCheckBox();
        jGerRisco_Saldo = new javax.swing.JFormattedTextField();
        jGerRisco_SaldoFin = new javax.swing.JFormattedTextField();
        jPassoGerRisco_PrejPerm = new javax.swing.JFormattedTextField();
        jGerRisco_PtsCont = new javax.swing.JFormattedTextField();
        jGerRisco_PtsContFin = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jPassoGerRisco_PtsCont = new javax.swing.JFormattedTextField();
        jGerRisco_PrejPerm = new javax.swing.JFormattedTextField();
        jGerRisco_PrejPermFin = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jPassoGerRisco_Saldo = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jTemCompensaSaldoCumulativo = new javax.swing.JCheckBox();
        jTemCompensaSaldoSimples = new javax.swing.JCheckBox();
        jTemReposicionaPelaAbertura = new javax.swing.JCheckBox();
        jTemSaldoDesejMinimo = new javax.swing.JCheckBox();
        jGerRisco_SaldoMinimo = new javax.swing.JFormattedTextField();
        jTemPtsCont = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de Risco");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Aplicar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Gerenciamento de Risco:"));

        jTemPrejMax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemPrejMax.setText("Prej Perm");
        jTemPrejMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });

        jTemSaldoDesej.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemSaldoDesej.setText("Saldo Desej");
        jTemSaldoDesej.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });

        jGerRisco_Saldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGerRisco_Saldo.setText("1");
        jGerRisco_Saldo.setEnabled(false);

        jGerRisco_SaldoFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGerRisco_SaldoFin.setText("1");
        jGerRisco_SaldoFin.setEnabled(false);

        jPassoGerRisco_PrejPerm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoGerRisco_PrejPerm.setText("1");
        jPassoGerRisco_PrejPerm.setToolTipText("");
        jPassoGerRisco_PrejPerm.setEnabled(false);

        jGerRisco_PtsCont.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGerRisco_PtsCont.setText("1");
        jGerRisco_PtsCont.setEnabled(false);

        jGerRisco_PtsContFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGerRisco_PtsContFin.setText("1");
        jGerRisco_PtsContFin.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Pts/Cont Fin");
        jLabel12.setEnabled(false);

        jPassoGerRisco_PtsCont.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoGerRisco_PtsCont.setText("1");
        jPassoGerRisco_PtsCont.setToolTipText("");
        jPassoGerRisco_PtsCont.setEnabled(false);

        jGerRisco_PrejPerm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGerRisco_PrejPerm.setText("1");
        jGerRisco_PrejPerm.setEnabled(false);

        jGerRisco_PrejPermFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGerRisco_PrejPermFin.setText("1");
        jGerRisco_PrejPermFin.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Prej Fin");
        jLabel10.setEnabled(false);

        jPassoGerRisco_Saldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoGerRisco_Saldo.setText("1");
        jPassoGerRisco_Saldo.setToolTipText("");
        jPassoGerRisco_Saldo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Saldo Fin");
        jLabel8.setEnabled(false);

        jTemCompensaSaldoCumulativo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemCompensaSaldoCumulativo.setText("Compensa Saldo Cumulativo");
        jTemCompensaSaldoCumulativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTemCompensaSaldoCumulativoActionPerformed(evt);
            }
        });

        jTemCompensaSaldoSimples.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemCompensaSaldoSimples.setText("Compensa Saldo Simples");
        jTemCompensaSaldoSimples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTemCompensaSaldoSimplesActionPerformed(evt);
            }
        });

        jTemReposicionaPelaAbertura.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemReposicionaPelaAbertura.setText("Reposiciona Na Abertura");
        jTemReposicionaPelaAbertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });

        jTemSaldoDesejMinimo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemSaldoDesejMinimo.setText("Saldo Série Mínimo (acionamento)");
        jTemSaldoDesejMinimo.setEnabled(false);
        jTemSaldoDesejMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTemSaldoDesejMinimoatualizaInterface(evt);
            }
        });

        jGerRisco_SaldoMinimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGerRisco_SaldoMinimo.setText("1");
        jGerRisco_SaldoMinimo.setEnabled(false);

        jTemPtsCont.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemPtsCont.setText("Pts/Cont");
        jTemPtsCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTemPrejMax)
                    .addComponent(jTemReposicionaPelaAbertura)
                    .addComponent(jTemCompensaSaldoSimples)
                    .addComponent(jTemCompensaSaldoCumulativo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTemSaldoDesej)
                        .addGap(18, 18, 18)
                        .addComponent(jGerRisco_Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTemPtsCont)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jGerRisco_PrejPerm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jGerRisco_PtsCont, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jGerRisco_PrejPermFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jGerRisco_PtsContFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jGerRisco_SaldoFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(23, 23, 23)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(31, 31, 31))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTemSaldoDesejMinimo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jGerRisco_SaldoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPassoGerRisco_PrejPerm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPassoGerRisco_PtsCont, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPassoGerRisco_Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTemSaldoDesej)
                    .addComponent(jGerRisco_Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jGerRisco_SaldoFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jPassoGerRisco_Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTemPtsCont)
                    .addComponent(jGerRisco_PtsCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jGerRisco_PtsContFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPassoGerRisco_PtsCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTemPrejMax)
                    .addComponent(jGerRisco_PrejPerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jGerRisco_PrejPermFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jPassoGerRisco_PrejPerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTemSaldoDesejMinimo)
                    .addComponent(jGerRisco_SaldoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTemCompensaSaldoSimples)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTemCompensaSaldoCumulativo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTemReposicionaPelaAbertura)
                .addContainerGap())
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
                .addGap(7, 7, 7)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean
        temGerRisco_Saldo = jTemSaldoDesej.isSelected(),
        temGerRisco_SaldoMinimo = jTemSaldoDesejMinimo.isSelected(),
        temGerRisco_PtsCont = jTemPtsCont.isSelected(),
        temGerRisco_PrejPerm = jTemPrejMax.isSelected(),
        temGerRisco_CompensaSaldoSimples = jTemCompensaSaldoSimples.isSelected(),
        temGerRisco_CompensaSaldoCumulativo = jTemCompensaSaldoCumulativo.isSelected(),
        temReposicionaBelaAbertura = jTemReposicionaPelaAbertura.isSelected();
        
        
        double
        gerRisco_Saldo = Double.parseDouble(jGerRisco_Saldo.getText().replace(",", ".")),
        gerRisco_SaldoFin = Double.parseDouble(jGerRisco_SaldoFin.getText().replace(",", ".")),
        gerRisco_Passo_Saldo = Double.parseDouble(jPassoGerRisco_Saldo.getText().replace(",", ".")),
        gerRisco_SaldoMinimo = Double.parseDouble(jGerRisco_SaldoMinimo.getText().replace(",", ".")),
        gerRisco_PtsCont = Double.parseDouble(jGerRisco_PtsCont.getText().replace(",", ".")),
        gerRisco_PtsContFin = Double.parseDouble(jGerRisco_PtsContFin.getText().replace(",", ".")),
        gerRisco_Passo_PtsCont = Double.parseDouble(jPassoGerRisco_PtsCont.getText().replace(",", ".")),
        gerRisco_PrejPerm = Double.parseDouble(jGerRisco_PrejPerm.getText().replace(",", ".")),
        gerRisco_PrejPermFin = Double.parseDouble(jGerRisco_PrejPermFin.getText().replace(",", ".")),
        gerRisco_Passo_PrejPerm = Double.parseDouble(jPassoGerRisco_PrejPerm.getText().replace(",", "."));
        
        if(temReposicionaBelaAbertura)
            if(!temGerRisco_Saldo && !temGerRisco_PtsCont){
                JOptionPane.showMessageDialog(null, "ERRO. Reposiciona Pela Abertura selecionado: Informe Saldo Desej E/OU PtsCont!");
                return;
            }
                
        
        ConfigOrdens.setBooleans(temGerRisco_Saldo, temGerRisco_SaldoMinimo, temGerRisco_PtsCont, temGerRisco_PrejPerm);
        ConfigOrdens.setGerRiscoLoop(gerRisco_Saldo, gerRisco_SaldoFin, gerRisco_SaldoMinimo,
                                    gerRisco_PtsCont, gerRisco_PtsContFin,
                                    gerRisco_PrejPerm, gerRisco_PrejPermFin);
        ConfigOrdens.setGerRisco_Passo(gerRisco_Passo_Saldo, gerRisco_Passo_PtsCont, gerRisco_Passo_PrejPerm);
        ConfigOrdens.setTemGerRisco_CompensaSaldo(temGerRisco_CompensaSaldoCumulativo,temGerRisco_CompensaSaldoSimples);
        ConfigOrdens.setReposicionaPelaAbertura(temReposicionaBelaAbertura);
        
        podeSimular = true;
        JOptionPane.showMessageDialog(null, "Valores Registrados");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void atualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizaInterface
        jGerRisco_Saldo.setEnabled(jTemSaldoDesej.isSelected());
        jGerRisco_SaldoFin.setEnabled(jTemSaldoDesej.isSelected() && relatorioCompleto);
        jPassoGerRisco_Saldo.setEnabled(jTemSaldoDesej.isSelected() && relatorioCompleto);

        jTemSaldoDesejMinimo.setEnabled(jTemSaldoDesej.isSelected());
        if(!jTemSaldoDesejMinimo.isEnabled()){
            jTemSaldoDesejMinimo.setSelected(false);
            jGerRisco_SaldoMinimo.setText("0");
            jGerRisco_SaldoMinimo.setEnabled(false);
        }
        
        jGerRisco_PtsCont.setEnabled(jTemPtsCont.isSelected());
        jGerRisco_PtsContFin.setEnabled(jTemPtsCont.isSelected() && relatorioCompleto);
        jPassoGerRisco_PtsCont.setEnabled(jTemPtsCont.isSelected() && relatorioCompleto);
        
        jGerRisco_PrejPerm.setEnabled(jTemPrejMax.isSelected());
        jGerRisco_PrejPermFin.setEnabled(jTemPrejMax.isSelected() && relatorioCompleto);
        jPassoGerRisco_PrejPerm.setEnabled(jTemPrejMax.isSelected() && relatorioCompleto);
    }//GEN-LAST:event_atualizaInterface

    private void jTemCompensaSaldoSimplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTemCompensaSaldoSimplesActionPerformed
        if(jTemCompensaSaldoSimples.isSelected())
            jTemCompensaSaldoCumulativo.setSelected(false);
    }//GEN-LAST:event_jTemCompensaSaldoSimplesActionPerformed

    private void jTemCompensaSaldoCumulativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTemCompensaSaldoCumulativoActionPerformed
        if(jTemCompensaSaldoCumulativo.isSelected())
            jTemCompensaSaldoSimples.setSelected(false);
    }//GEN-LAST:event_jTemCompensaSaldoCumulativoActionPerformed

    private void jTemSaldoDesejMinimoatualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTemSaldoDesejMinimoatualizaInterface
        if(jTemSaldoDesej.isSelected())
            jGerRisco_SaldoMinimo.setEnabled(jTemSaldoDesejMinimo.isSelected());
        else
            jTemSaldoDesejMinimo.setSelected(false);
    }//GEN-LAST:event_jTemSaldoDesejMinimoatualizaInterface

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private static javax.swing.JFormattedTextField jGerRisco_PrejPerm;
    private static javax.swing.JFormattedTextField jGerRisco_PrejPermFin;
    private static javax.swing.JFormattedTextField jGerRisco_PtsCont;
    private static javax.swing.JFormattedTextField jGerRisco_PtsContFin;
    private static javax.swing.JFormattedTextField jGerRisco_Saldo;
    private static javax.swing.JFormattedTextField jGerRisco_SaldoFin;
    private static javax.swing.JFormattedTextField jGerRisco_SaldoMinimo;
    private static javax.swing.JLabel jLabel10;
    private static javax.swing.JLabel jLabel12;
    private static javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JFormattedTextField jPassoGerRisco_PrejPerm;
    private static javax.swing.JFormattedTextField jPassoGerRisco_PtsCont;
    private static javax.swing.JFormattedTextField jPassoGerRisco_Saldo;
    private static javax.swing.JCheckBox jTemCompensaSaldoCumulativo;
    private static javax.swing.JCheckBox jTemCompensaSaldoSimples;
    private static javax.swing.JCheckBox jTemPrejMax;
    private static javax.swing.JCheckBox jTemPtsCont;
    private static javax.swing.JCheckBox jTemReposicionaPelaAbertura;
    private static javax.swing.JCheckBox jTemSaldoDesej;
    private static javax.swing.JCheckBox jTemSaldoDesejMinimo;
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
        IG_GerRisco.relatorioCompleto = relatorioCompleto.isSelected();
    }
    
    public static void verificaInterface(){
        jTemSaldoDesej.setSelected(ConfigOrdens.isTemGerRisco_Saldo());
        jGerRisco_Saldo.setEnabled(jTemSaldoDesej.isSelected());
        jGerRisco_Saldo.setText(String.valueOf(ConfigOrdens.getGerRisco_SaldoDesejIni()));
        jGerRisco_SaldoFin.setEnabled(relatorioCompleto);
        jGerRisco_SaldoFin.setText(String.valueOf(ConfigOrdens.getGerRisco_SaldoDesejFin()));
        jPassoGerRisco_Saldo.setEnabled(relatorioCompleto);
        jPassoGerRisco_Saldo.setText(String.valueOf(ConfigOrdens.getPassoGerRisco_SaldoDesej()));
        
        jTemSaldoDesejMinimo.setSelected(ConfigOrdens.isTemGerRisco_SaldoMinimo());
        jGerRisco_SaldoMinimo.setEnabled(jTemSaldoDesejMinimo.isSelected());
        jGerRisco_SaldoMinimo.setText(String.valueOf(ConfigOrdens.getGerRisco_ZeraSerie()));
        
        jTemPtsCont.setSelected(ConfigOrdens.isTemGerRisco_PtsCont());
        jGerRisco_PtsCont.setEnabled(jTemPtsCont.isSelected());
        jGerRisco_PtsCont.setText(String.valueOf(ConfigOrdens.getGerRisco_PtsContIni()));
        jGerRisco_PtsContFin.setEnabled(relatorioCompleto);
        jGerRisco_PtsContFin.setText(String.valueOf(ConfigOrdens.getGerRisco_PtsContFin()));
        jPassoGerRisco_PtsCont.setEnabled(relatorioCompleto);
        jPassoGerRisco_PtsCont.setText(String.valueOf(ConfigOrdens.getPassoGerRisco_PtsCont()));
        
        jTemPrejMax.setSelected(ConfigOrdens.isTemGerRisco_PrejPerm());
        jGerRisco_PrejPerm.setEnabled(jTemPrejMax.isSelected());
        jGerRisco_PrejPerm.setText(String.valueOf(ConfigOrdens.getGerRisco_PrejPermIni()));
        jGerRisco_PrejPermFin.setEnabled(relatorioCompleto);
        jGerRisco_PrejPermFin.setText(String.valueOf(ConfigOrdens.getGerRisco_PrejPermFin()));
        jPassoGerRisco_PrejPerm.setEnabled(relatorioCompleto);
        jPassoGerRisco_PrejPerm.setText(String.valueOf(ConfigOrdens.getPassoGerRisco_PrejMax()));
        
        jTemCompensaSaldoSimples.setSelected(ConfigOrdens.isTemGerRisco_CompensaSaldoSimples());
        jTemCompensaSaldoCumulativo.setSelected(ConfigOrdens.isTemGerRisco_CompensaSaldoCumulativo());
        jTemReposicionaPelaAbertura.setSelected(ConfigOrdens.isReposicionaPelaAbertura());
    }
}
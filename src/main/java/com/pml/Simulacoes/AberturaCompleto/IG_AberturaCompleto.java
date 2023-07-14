package com.pml.Simulacoes.AberturaCompleto;

import com.pml.infra.Candle;
import com.pml.InterfaceGrafica.IG;
import com.pml.InterfaceGrafica.IG_GerRisco;
import com.pml.Ordens.LadoOrdem;
import com.pml.Simulacoes.IG_InterfaceSimulacao;

/**
 *
 * @author fabra
 */
public class IG_AberturaCompleto extends IG_InterfaceSimulacao {

    /**
     * Creates new form IG_UE
     */
    public IG_AberturaCompleto() {
    }

    @Override
    public void abreinterface() {
        initComponents();
        super.abreinterface();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton_executar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRelatorioDetalhado = new javax.swing.JRadioButton();
        jRelatorioCompleto = new javax.swing.JRadioButton();
        jButton_abortar = new javax.swing.JButton();
        jButton_gerRisco = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPassoLimOp = new javax.swing.JFormattedTextField();
        jLimOp = new javax.swing.JFormattedTextField();
        jGain = new javax.swing.JFormattedTextField();
        jLossFin = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jGainFin = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jPassoLoss = new javax.swing.JFormattedTextField();
        jPassoGain = new javax.swing.JFormattedTextField();
        jPos = new javax.swing.JFormattedTextField();
        jOffset = new javax.swing.JFormattedTextField();
        jLoss = new javax.swing.JFormattedTextField();
        jOffsetFin = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jPassoOffset = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLimOpFin = new javax.swing.JFormattedTextField();
        jTemContMov = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jTemOffset = new javax.swing.JCheckBox();
        jTemLimOp = new javax.swing.JCheckBox();
        jTemGain = new javax.swing.JCheckBox();
        jTemLoss = new javax.swing.JCheckBox();
        jMov = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPosMax = new javax.swing.JFormattedTextField();
        jPosMaxFin = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jPassoPosMax = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DayTrade: Abertura Completo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });

        jButton_executar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_executar.setText("Executar");
        jButton_executar.setEnabled(false);
        jButton_executar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_executarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Relatórios:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        buttonGroup1.add(jRelatorioDetalhado);
        jRelatorioDetalhado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRelatorioDetalhado.setText("Detalhado");
        jRelatorioDetalhado.setEnabled(false);

        buttonGroup1.add(jRelatorioCompleto);
        jRelatorioCompleto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRelatorioCompleto.setSelected(true);
        jRelatorioCompleto.setText("Completo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRelatorioDetalhado)
                .addGap(18, 18, 18)
                .addComponent(jRelatorioCompleto)
                .addGap(205, 205, 205))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRelatorioDetalhado)
                    .addComponent(jRelatorioCompleto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_abortar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_abortar.setText("Abortar");
        jButton_abortar.setEnabled(false);
        jButton_abortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_abortarActionPerformed(evt);
            }
        });

        jButton_gerRisco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_gerRisco.setText("Ger. Risco");
        jButton_gerRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_gerRiscoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Lim Op Fin");
        jLabel13.setEnabled(false);

        jPassoLimOp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoLimOp.setText("1");
        jPassoLimOp.setToolTipText("");
        jPassoLimOp.setEnabled(false);

        jLimOp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLimOp.setText("1");
        jLimOp.setEnabled(false);

        jGain.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGain.setText("1");
        jGain.setEnabled(false);

        jLossFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLossFin.setText("1");
        jLossFin.setToolTipText("");
        jLossFin.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Loss Fin");
        jLabel7.setEnabled(false);

        jGainFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGainFin.setText("1");
        jGainFin.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Gain Fin");
        jLabel8.setEnabled(false);

        jPassoLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoLoss.setText("1");
        jPassoLoss.setToolTipText("");
        jPassoLoss.setEnabled(false);

        jPassoGain.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoGain.setText("1");
        jPassoGain.setToolTipText("");
        jPassoGain.setEnabled(false);

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");

        jOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jOffset.setText("1");
        jOffset.setEnabled(false);

        jLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLoss.setText("1");
        jLoss.setToolTipText("");
        jLoss.setEnabled(false);

        jOffsetFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jOffsetFin.setText("1");
        jOffsetFin.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Offset Fin");
        jLabel6.setEnabled(false);

        jPassoOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoOffset.setText("1");
        jPassoOffset.setToolTipText("");
        jPassoOffset.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Qtde");

        jLimOpFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLimOpFin.setText("1");
        jLimOpFin.setEnabled(false);

        jTemContMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemContMov.setText("Contra Mov.");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Passo:");

        jTemOffset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemOffset.setText("Offset");

        jTemLimOp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemLimOp.setText("Lim Op");

        jTemGain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemGain.setText("Gain");

        jTemLoss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemLoss.setText("Loss");

        jMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venda" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Qtde Max");

        jPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMax.setText("1");

        jPosMaxFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMaxFin.setText("1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Qtde Max Fin");

        jPassoPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoPosMax.setText("1");
        jPassoPosMax.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 368, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_executar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_abortar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jMov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTemContMov))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPosMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPosMaxFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5))
                                            .addComponent(jPos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTemOffset)
                                            .addComponent(jTemLimOp)
                                            .addComponent(jTemGain)
                                            .addComponent(jTemLoss))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLoss, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLossFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jOffset, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jOffsetFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jGain, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jGainFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLimOp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLimOpFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPassoLimOp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPassoOffset, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPassoGain, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPassoLoss, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jPassoPosMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton_gerRisco))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTemContMov))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPosMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jPosMaxFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jPassoPosMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jOffsetFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTemOffset)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLimOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLimOpFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTemLimOp)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jGain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jGainFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTemGain)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLoss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLossFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTemLoss)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(41, 41, 41)
                        .addComponent(jPassoOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPassoLimOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPassoGain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPassoLoss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_gerRisco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_executar)
                    .addComponent(jButton_abortar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_executarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_executarActionPerformed
        cliqueBotaoExecutar(new AberturaCompleto(), jButton_executar, jButton_abortar, jButton_gerRisco, null);
    }//GEN-LAST:event_jButton_executarActionPerformed

    private void jButton_abortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_abortarActionPerformed
        cliqueBotaoAbortar();
    }//GEN-LAST:event_jButton_abortarActionPerformed

    private void jButton_gerRiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_gerRiscoActionPerformed
        cliqueBotaoGerRisco();
    }//GEN-LAST:event_jButton_gerRiscoActionPerformed

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        cliqueBotaoFechar();
    }//GEN-LAST:event_close

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JButton jButton_abortar;
    public static javax.swing.JButton jButton_executar;
    public static javax.swing.JButton jButton_gerRisco;
    private javax.swing.JFormattedTextField jGain;
    private javax.swing.JFormattedTextField jGainFin;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JFormattedTextField jLimOp;
    private javax.swing.JFormattedTextField jLimOpFin;
    private javax.swing.JFormattedTextField jLoss;
    private javax.swing.JFormattedTextField jLossFin;
    private javax.swing.JComboBox<String> jMov;
    private javax.swing.JFormattedTextField jOffset;
    private javax.swing.JFormattedTextField jOffsetFin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoGain;
    private javax.swing.JFormattedTextField jPassoLimOp;
    private javax.swing.JFormattedTextField jPassoLoss;
    private javax.swing.JFormattedTextField jPassoOffset;
    private javax.swing.JFormattedTextField jPassoPosMax;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JFormattedTextField jPosMax;
    private javax.swing.JFormattedTextField jPosMaxFin;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JCheckBox jTemContMov;
    private javax.swing.JCheckBox jTemGain;
    private javax.swing.JCheckBox jTemLimOp;
    private javax.swing.JCheckBox jTemLoss;
    private javax.swing.JCheckBox jTemOffset;
    // End of variables declaration//GEN-END:variables


    @Override
    protected void atualizaInterface() {
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);
        
        jButton_executar.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());

        jOffset.setEnabled(jTemOffset.isSelected());
        jOffsetFin.setEnabled(jTemOffset.isSelected() && jRelatorioCompleto.isSelected());
        jPassoOffset.setEnabled(jTemOffset.isSelected() && jRelatorioCompleto.isSelected());
        jLabel6.setEnabled(jTemOffset.isSelected() && jRelatorioCompleto.isSelected());

        jLimOp.setEnabled(jTemLimOp.isSelected());
        jLimOpFin.setEnabled(jTemLimOp.isSelected() && jRelatorioCompleto.isSelected());
        jPassoLimOp.setEnabled(jTemLimOp.isSelected() && jRelatorioCompleto.isSelected());
        jLabel13.setEnabled(jTemLimOp.isSelected() && jRelatorioCompleto.isSelected());

        jGain.setEnabled(jTemGain.isSelected());
        jGainFin.setEnabled(jTemGain.isSelected() && jRelatorioCompleto.isSelected());
        jPassoGain.setEnabled(jTemGain.isSelected() && jRelatorioCompleto.isSelected());
        jLabel8.setEnabled(jTemGain.isSelected() && jRelatorioCompleto.isSelected());

        jLoss.setEnabled(jTemLoss.isSelected());
        jLossFin.setEnabled(jTemLoss.isSelected() && jRelatorioCompleto.isSelected());
        jPassoLoss.setEnabled(jTemLoss.isSelected() && jRelatorioCompleto.isSelected());
        jLabel7.setEnabled(jTemLoss.isSelected() && jRelatorioCompleto.isSelected());
    }
    
    @Override
    protected void getVariaveisDaInterfaceDaSimulacao() {
        temCompleto = jRelatorioCompleto.isSelected();

        passoOffset = Double.parseDouble(jPassoOffset.getText().replace(",", "."));
        passoLimOp = Double.parseDouble(jPassoLimOp.getText().replace(",", "."));
        passoGain = Double.parseDouble(jPassoGain.getText().replace(",", "."));
        passoLoss = Double.parseDouble(jPassoLoss.getText().replace(",", "."));

        temOffset = jTemOffset.isSelected();
        temLimOp = jTemLimOp.isSelected();
        temAlvo = jTemGain.isSelected();
        temStop = jTemLoss.isSelected();
        temContMov = jTemContMov.isSelected();

        pos = Integer.parseInt(jPos.getText().replace(",", "."));
        posMax = Integer.parseInt(jPosMax.getText().replace(",", "."));
        posMaxFin = Integer.parseInt(jPosMaxFin.getText().replace(",", "."));
        passoPos = Integer.parseInt(jPassoPosMax.getText().replace(",", "."));

        e = Double.parseDouble(jOffset.getText().replace(",", "."));
        eFin = Double.parseDouble(jOffsetFin.getText().replace(",", "."));
        lim = Double.parseDouble(jLimOp.getText().replace(",", "."));
        limFin = Double.parseDouble(jLimOpFin.getText().replace(",", "."));
        g = Double.parseDouble(jGain.getText().replace(",", "."));
        gFin = Double.parseDouble(jGainFin.getText().replace(",", "."));
        l = Double.parseDouble(jLoss.getText().replace(",", "."));
        lFin = Double.parseDouble(jLossFin.getText().replace(",", "."));

        mov = switch (jMov.getSelectedIndex()) {
            case 0 -> LadoOrdem.COMPRA;
            case 1 -> LadoOrdem.VENDA;
            default -> LadoOrdem.INDEF;
        };
    }

@Override
public String getNome() {
        return "Abertura Completo";
    }


}

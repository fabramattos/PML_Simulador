package com.pml.Simulacoes.ExtremosDiaAnterior;

import com.pml.infra.Candle;
import com.pml.InterfaceGrafica.IG;
import com.pml.InterfaceGrafica.IG_GerRisco;
import com.pml.Ordens.LadoOrdem;
import com.pml.Simulacoes.IG_InterfaceSimulacao;

public class IG_ExtremosDiaAnterior extends IG_InterfaceSimulacao {

    public IG_ExtremosDiaAnterior() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jRelatorioDetalhado = new javax.swing.JRadioButton();
        jRelatorioCompleto = new javax.swing.JRadioButton();
        botaoExecutar = new javax.swing.JButton();
        botaoAbortar = new javax.swing.JButton();
        botaoGerRisco = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jDelta = new javax.swing.JFormattedTextField();
        jTemGain = new javax.swing.JCheckBox();
        jGain = new javax.swing.JFormattedTextField();
        jDeltaFin = new javax.swing.JFormattedTextField();
        jGainFin = new javax.swing.JFormattedTextField();
        jTemLoss = new javax.swing.JCheckBox();
        jLoss = new javax.swing.JFormattedTextField();
        jLossFin = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPassoDelta = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jPassoGain = new javax.swing.JFormattedTextField();
        jPassoLoss = new javax.swing.JFormattedTextField();
        jMov = new javax.swing.JComboBox<>();
        jTemContMov = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jPos = new javax.swing.JFormattedTextField();
        jTemDelta = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DayTrade: Extremos Dia Anterior");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Relatórios:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        buttonGroup1.add(jRelatorioDetalhado);
        jRelatorioDetalhado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRelatorioDetalhado.setSelected(true);
        jRelatorioDetalhado.setText("Detalhado");

        buttonGroup1.add(jRelatorioCompleto);
        jRelatorioCompleto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRelatorioCompleto.setText("Completo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jRelatorioDetalhado)
                        .addGap(300, 300, 300))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRelatorioCompleto)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jRelatorioDetalhado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRelatorioCompleto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoExecutar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoExecutar.setText("Executar");
        botaoExecutar.setEnabled(false);
        botaoExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExecutarActionPerformed(evt);
            }
        });

        botaoAbortar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoAbortar.setText("Abortar");
        botaoAbortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbortarActionPerformed(evt);
            }
        });

        botaoGerRisco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoGerRisco.setText("Ger. Risco");
        botaoGerRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerRiscoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configurações Completo:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jDelta.setText("1");
        jPanel2.add(jDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 50, -1));

        jTemGain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemGain.setText("Gain");
        jPanel2.add(jTemGain, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jGain.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGain.setText("1");
        jPanel2.add(jGain, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 50, -1));

        jDeltaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jDeltaFin.setText("1");
        jDeltaFin.setEnabled(false);
        jPanel2.add(jDeltaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 50, -1));

        jGainFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGainFin.setText("1");
        jGainFin.setEnabled(false);
        jPanel2.add(jGainFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 50, -1));

        jTemLoss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemLoss.setText("Loss");
        jPanel2.add(jTemLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLoss.setText("1");
        jLoss.setToolTipText("");
        jPanel2.add(jLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 50, -1));

        jLossFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLossFin.setText("1");
        jLossFin.setToolTipText("");
        jLossFin.setEnabled(false);
        jPanel2.add(jLossFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 50, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Loss Fin");
        jLabel7.setEnabled(false);
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Gain Fin");
        jLabel8.setEnabled(false);
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Delta Fin");
        jLabel6.setEnabled(false);
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jPassoDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoDelta.setText("1");
        jPassoDelta.setToolTipText("");
        jPassoDelta.setEnabled(false);
        jPanel2.add(jPassoDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 50, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Passo:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jPassoGain.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoGain.setText("1");
        jPassoGain.setToolTipText("");
        jPassoGain.setEnabled(false);
        jPanel2.add(jPassoGain, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 50, -1));

        jPassoLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoLoss.setText("1");
        jPassoLoss.setToolTipText("");
        jPassoLoss.setEnabled(false);
        jPanel2.add(jPassoLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 50, -1));

        jMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venda" }));
        jPanel2.add(jMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jTemContMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemContMov.setText("Contra Mov.");
        jPanel2.add(jTemContMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Qtde");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");
        jPanel2.add(jPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 50, -1));

        jTemDelta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemDelta.setText("Delta");
        jPanel2.add(jTemDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoGerRisco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoAbortar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoGerRisco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExecutar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAbortar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExecutarActionPerformed
        cliqueBotaoExecutar(new ExtremosDiaAnterior(), botaoExecutar, botaoAbortar, botaoGerRisco, null);
    }//GEN-LAST:event_botaoExecutarActionPerformed

    private void botaoAbortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbortarActionPerformed
        cliqueBotaoAbortar();
    }//GEN-LAST:event_botaoAbortarActionPerformed

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        cliqueBotaoFechar();
    }//GEN-LAST:event_close

    private void botaoGerRiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerRiscoActionPerformed
        cliqueBotaoGerRisco();
    }//GEN-LAST:event_botaoGerRiscoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton botaoAbortar;
    public static javax.swing.JButton botaoExecutar;
    public static javax.swing.JButton botaoGerRisco;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField jDelta;
    private javax.swing.JFormattedTextField jDeltaFin;
    private javax.swing.JFormattedTextField jGain;
    private javax.swing.JFormattedTextField jGainFin;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JFormattedTextField jLoss;
    private javax.swing.JFormattedTextField jLossFin;
    private javax.swing.JComboBox<String> jMov;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoDelta;
    private javax.swing.JFormattedTextField jPassoGain;
    private javax.swing.JFormattedTextField jPassoLoss;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JCheckBox jTemContMov;
    private javax.swing.JCheckBox jTemDelta;
    private javax.swing.JCheckBox jTemGain;
    private javax.swing.JCheckBox jTemLoss;
    // End of variables declaration//GEN-END:variables

    @Override
    public void abreinterface() {
        initComponents();
        super.abreinterface();
    }

    @Override
    protected void getVariaveisDaInterfaceDaSimulacao() {
        temCompleto = jRelatorioCompleto.isSelected();
        
        passoDelta = Double.parseDouble(jPassoDelta.getText().replace(",", "."));
        passoGain = Double.parseDouble(jPassoGain.getText().replace(",", "."));
        passoLoss = Double.parseDouble(jPassoLoss.getText().replace(",", "."));
        
        temDelta = jTemDelta.isSelected();
        temAlvo = jTemGain.isSelected();
        temStop = jTemLoss.isSelected();
        temContMov = jTemContMov.isSelected();
        
        pos = Integer.parseInt(jPos.getText().replace(",", "."));
        
        delta = Double.parseDouble(jDelta.getText().replace(",", "."));
        deltaFin = Double.parseDouble(jDeltaFin.getText().replace(",", "."));
        g = Double.parseDouble(jGain.getText().replace(",", "."));
        gFin = Double.parseDouble(jGainFin.getText().replace(",", "."));
        l = Double.parseDouble(jLoss.getText().replace(",", "."));
        lFin = Double.parseDouble(jLossFin.getText().replace(",", "."));

        mov = switch (jMov.getSelectedIndex()){
            case 0 -> LadoOrdem.COMPRA;

            default -> LadoOrdem.VENDA;
        };
    }

    @Override
    protected void atualizaInterface() {
        jDelta.setEnabled(jTemDelta.isSelected());
        jDeltaFin.setEnabled(jTemDelta.isSelected() && jRelatorioCompleto.isSelected());
        jPassoDelta.setEnabled(jTemDelta.isSelected() && jRelatorioCompleto.isSelected());
        jLabel6.setEnabled(jTemDelta.isSelected() && jRelatorioCompleto.isSelected());
        
        jGain.setEnabled(jTemGain.isSelected());
        jGainFin.setEnabled(jTemGain.isSelected() && jRelatorioCompleto.isSelected());
        jPassoGain.setEnabled(jTemGain.isSelected() && jRelatorioCompleto.isSelected());
        jLabel8.setEnabled(jTemGain.isSelected() && jRelatorioCompleto.isSelected());
        
        jLoss.setEnabled(jTemLoss.isSelected());
        jLossFin.setEnabled(jTemLoss.isSelected() && jRelatorioCompleto.isSelected());
        jPassoLoss.setEnabled(jTemLoss.isSelected() && jRelatorioCompleto.isSelected());
        jLabel7.setEnabled(jTemLoss.isSelected() && jRelatorioCompleto.isSelected());
        
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);
        
        botaoExecutar.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
    }

    @Override
    public String getNome() {
        return "Extremos Dia Anterior";
    }
}

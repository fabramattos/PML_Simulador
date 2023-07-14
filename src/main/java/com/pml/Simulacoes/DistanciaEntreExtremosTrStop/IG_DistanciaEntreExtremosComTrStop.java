package com.pml.Simulacoes.DistanciaEntreExtremosTrStop;

import com.pml.infra.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.InterfaceGrafica.IG;
import com.pml.InterfaceGrafica.IG_GerRisco;
import com.pml.InterfaceGrafica.IG_TrStop;
import com.pml.Simulacoes.IG_InterfaceSimulacao;

/**
 *
 * @author fabra
 */
public class IG_DistanciaEntreExtremosComTrStop extends IG_InterfaceSimulacao {

    public IG_DistanciaEntreExtremosComTrStop() {
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        botaoExecutar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRelatorioDetalhado = new javax.swing.JRadioButton();
        jRelatorioCompleto = new javax.swing.JRadioButton();
        botaoAbortar = new javax.swing.JButton();
        jPos = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTemContMov = new javax.swing.JCheckBox();
        jLimTempo = new javax.swing.JFormattedTextField();
        jLimTempoFin = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jPassoLimTempo = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        botaoGerRisco = new javax.swing.JButton();
        botaoTrStop = new javax.swing.JButton();
        jOffset = new javax.swing.JFormattedTextField();
        jOffsetFin = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jPassoOffset = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jPosMax = new javax.swing.JFormattedTextField();
        jPosMaxFin = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jPassoPos = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DayTrade: Suporte E Resistencia TrStop");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botaoExecutar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoExecutar.setText("Executar");
        botaoExecutar.setEnabled(false);
        botaoExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExecutarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoExecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 90, -1));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRelatorioDetalhado)
                .addGap(18, 18, 18)
                .addComponent(jRelatorioCompleto)
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRelatorioDetalhado)
                    .addComponent(jRelatorioCompleto))
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 60));

        botaoAbortar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoAbortar.setText("Abortar");
        botaoAbortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbortarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoAbortar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 90, -1));

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");
        getContentPane().add(jPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 50, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Passo:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Pos Ini");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jTemContMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemContMov.setText("Contra Mov.");
        getContentPane().add(jTemContMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLimTempo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jLimTempo.setText("1");
        getContentPane().add(jLimTempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 50, -1));

        jLimTempoFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jLimTempoFin.setText("1");
        jLimTempoFin.setEnabled(false);
        getContentPane().add(jLimTempoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 50, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Lim Tempo Fin");
        jLabel12.setEnabled(false);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        jPassoLimTempo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoLimTempo.setText("1");
        jPassoLimTempo.setToolTipText("");
        jPassoLimTempo.setEnabled(false);
        getContentPane().add(jPassoLimTempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 50, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Lim Tempo");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        botaoGerRisco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoGerRisco.setText("Ger. Risco");
        botaoGerRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerRiscoActionPerformed(evt);
            }
        });
        getContentPane().add(botaoGerRisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 90, -1));

        botaoTrStop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoTrStop.setText("Tr. Stop");
        botaoTrStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTrStopActionPerformed(evt);
            }
        });
        getContentPane().add(botaoTrStop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 90, -1));

        jOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jOffset.setText("1");
        getContentPane().add(jOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 50, -1));

        jOffsetFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jOffsetFin.setText("1");
        jOffsetFin.setEnabled(false);
        getContentPane().add(jOffsetFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 50, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Offset Fin");
        jLabel14.setEnabled(false);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

        jPassoOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoOffset.setText("1");
        jPassoOffset.setToolTipText("");
        jPassoOffset.setEnabled(false);
        getContentPane().add(jPassoOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 50, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Offset");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMax.setText("1");
        getContentPane().add(jPosMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 50, -1));

        jPosMaxFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMaxFin.setText("1");
        jPosMaxFin.setEnabled(false);
        getContentPane().add(jPosMaxFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 50, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Pos Max Fin");
        jLabel16.setEnabled(false);
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jPassoPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoPos.setText("1");
        jPassoPos.setToolTipText("");
        jPassoPos.setEnabled(false);
        getContentPane().add(jPassoPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 50, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Pos Max");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void abreinterface() {
        initComponents();
        super.abreinterface();
    }
    
    
    private void botaoExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExecutarActionPerformed
        cliqueBotaoExecutar(new DistanciaEntreExtremosComTrStop(), botaoExecutar, botaoAbortar, botaoGerRisco, botaoTrStop);
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

    private void botaoTrStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTrStopActionPerformed
        cliqueBotaoTrStop();
    }//GEN-LAST:event_botaoTrStopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton botaoAbortar;
    public static javax.swing.JButton botaoExecutar;
    public static javax.swing.JButton botaoGerRisco;
    public static javax.swing.JButton botaoTrStop;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JFormattedTextField jLimTempo;
    private javax.swing.JFormattedTextField jLimTempoFin;
    private javax.swing.JFormattedTextField jOffset;
    private javax.swing.JFormattedTextField jOffsetFin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoLimTempo;
    private javax.swing.JFormattedTextField jPassoOffset;
    private javax.swing.JFormattedTextField jPassoPos;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JFormattedTextField jPosMax;
    private javax.swing.JFormattedTextField jPosMaxFin;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JCheckBox jTemContMov;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void getVariaveisDaInterfaceDaSimulacao() {
        temCompleto = jRelatorioCompleto.isSelected();
        
        passoDelta = Double.parseDouble(jPassoLimTempo.getText().replace(",", "."));
        passoOffset = Double.parseDouble(jPassoOffset.getText().replace(",", "."));
        
        atualQtde = true;
        temDelta = true;
        temOffset = true;
        
        temContMov = jTemContMov.isSelected();
        
        pos = Integer.parseInt(jPos.getText().replace(",", "."));
        posMax = Integer.parseInt(jPosMax.getText().replace(",", "."));
        posMaxFin = Integer.parseInt(jPosMaxFin.getText().replace(",", "."));
        passoPos = Integer.parseInt(jPassoPos.getText().replace(",", "."));
        
        delta = Double.parseDouble(jLimTempo.getText().replace(",", "."));
        deltaFin = Double.parseDouble(jLimTempoFin.getText().replace(",", "."));
        e = Double.parseDouble(jOffset.getText().replace(",", "."));
        eFin = Double.parseDouble(jOffsetFin.getText().replace(",", "."));
        
        
        ConfigOrdens.setObservacoes("""
                                    DELTA representa o limite de candles analisados
                                    para a variação da cotação atual se distanciar de
                                    algum extremo local. Caso não se distancie o bastante,
                                    as referencias são reiniciadas.
                                    """);
    }

    @Override
    protected void atualizaInterface() {
        jPassoPos.setEnabled(jRelatorioCompleto.isSelected());
        jPosMaxFin.setEnabled(jRelatorioCompleto.isSelected());
        jLabel16.setEnabled(jRelatorioCompleto.isSelected());
        
        jPassoLimTempo.setEnabled(jRelatorioCompleto.isSelected());
        jLimTempoFin.setEnabled(jRelatorioCompleto.isSelected());
        jLabel12.setEnabled(jRelatorioCompleto.isSelected());
        
        jPassoOffset.setEnabled(jRelatorioCompleto.isSelected());
        jOffsetFin.setEnabled(jRelatorioCompleto.isSelected());
        jLabel14.setEnabled(jRelatorioCompleto.isSelected());
        
        botaoExecutar.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular() && IG_TrStop.isPodeSimular());
        
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);
        IG_TrStop.recebeTipoRelatorio(jRelatorioCompleto);
    }

    @Override
    public String getNome() {
        return "Distancia Entre Extremos Com Tr.Stop";
    }
}

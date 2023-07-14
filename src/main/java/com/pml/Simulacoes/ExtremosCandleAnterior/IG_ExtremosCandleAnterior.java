package com.pml.Simulacoes.ExtremosCandleAnterior;

import com.pml.infra.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.InterfaceGrafica.IG;
import com.pml.InterfaceGrafica.IG_GerRisco;
import com.pml.Ordens.OrdemSimples;
import com.pml.Simulacoes.IG_InterfaceSimulacao;

/**
 *
 * @author fabra
 */
public class IG_ExtremosCandleAnterior extends IG_InterfaceSimulacao {

    public IG_ExtremosCandleAnterior() {
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
        jDelta = new javax.swing.JFormattedTextField();
        jDeltaFin = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jPassoDelta = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jMin = new javax.swing.JComboBox<>();
        jPosMaxFin = new javax.swing.JFormattedTextField();
        jPassoPos = new javax.swing.JFormattedTextField();
        jPosMaxIni = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        botaoGerRisco = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DayTrade: Extremos Candle Anterior");
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
        getContentPane().add(botaoExecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, -1));

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
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRelatorioDetalhado)
                    .addComponent(jRelatorioCompleto))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 60));

        botaoAbortar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoAbortar.setText("Abortar");
        botaoAbortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbortarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoAbortar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");
        getContentPane().add(jPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 50, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Passo:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Pos Max");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jTemContMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemContMov.setText("Contra Mov.");
        getContentPane().add(jTemContMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jDelta.setText("1");
        getContentPane().add(jDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 50, -1));

        jDeltaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jDeltaFin.setText("1");
        jDeltaFin.setEnabled(false);
        getContentPane().add(jDeltaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 50, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Pos Max Fin");
        jLabel12.setEnabled(false);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jPassoDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoDelta.setText("1");
        jPassoDelta.setToolTipText("");
        jPassoDelta.setEnabled(false);
        getContentPane().add(jPassoDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 50, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Delta");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jMin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "05", "10", "30", "60" }));
        getContentPane().add(jMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        jPosMaxFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMaxFin.setText("1");
        jPosMaxFin.setEnabled(false);
        getContentPane().add(jPosMaxFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 50, -1));

        jPassoPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPassoPos.setText("1");
        jPassoPos.setEnabled(false);
        getContentPane().add(jPassoPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 50, -1));

        jPosMaxIni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMaxIni.setText("1");
        getContentPane().add(jPosMaxIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 50, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Minutos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Pos");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Atualizar extremos a cada:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Delta Fin");
        jLabel15.setEnabled(false);
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        botaoGerRisco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoGerRisco.setText("Ger. Risco");
        botaoGerRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerRiscoActionPerformed(evt);
            }
        });
        getContentPane().add(botaoGerRisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void abreinterface() {
        initComponents();
        super.abreinterface();
    }
    
    
    private void botaoExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExecutarActionPerformed
        cliqueBotaoExecutar(new ExtremosCandleAnterior(), botaoExecutar, botaoAbortar, botaoGerRisco, null);
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    static javax.swing.JComboBox<String> jMin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoDelta;
    private javax.swing.JFormattedTextField jPassoPos;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JFormattedTextField jPosMaxFin;
    private javax.swing.JFormattedTextField jPosMaxIni;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JCheckBox jTemContMov;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void getVariaveisDaInterfaceDaSimulacao() {
        temCompleto = jRelatorioCompleto.isSelected();
        
        atualQtde = true;
        temDelta = true;
        temOffset = true;
        temContMov = jTemContMov.isSelected();
        
        pos = Integer.parseInt(jPos.getText().replace(",", "."));
        posMax = Integer.parseInt(jPosMaxIni.getText().replace(",", "."));
        posMaxFin = Integer.parseInt(jPosMaxFin.getText().replace(",", "."));
        passoPos = Integer.parseInt(jPassoPos.getText().replace(",", "."));
        
        delta = Double.parseDouble(jDelta.getText().replace(",", "."));
        deltaFin = Double.parseDouble(jDeltaFin.getText().replace(",", "."));
        passoDelta = Double.parseDouble(jPassoDelta.getText().replace(",", "."));

        ConfigOrdens.adicionaNaListaDeOrdensFixas_OCO(new OrdemSimples());
        ConfigOrdens.setAguardaFormacaoCandle(Integer.parseInt(jMin.getSelectedItem().toString()));
    }

    @Override
    protected void atualizaInterface() {
        jPassoPos.setEnabled(jRelatorioCompleto.isSelected());
        jPassoDelta.setEnabled(jRelatorioCompleto.isSelected());
        
        jDeltaFin.setEnabled(jRelatorioCompleto.isSelected());
        jPosMaxFin.setEnabled(jRelatorioCompleto.isSelected());
        
        jLabel12.setEnabled(jRelatorioCompleto.isSelected());
        jLabel15.setEnabled(jRelatorioCompleto.isSelected());
        
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);
        
        botaoExecutar.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
    }

    @Override
    public String getNome() {
        return "Extremos Candle Anterior";
    }
}

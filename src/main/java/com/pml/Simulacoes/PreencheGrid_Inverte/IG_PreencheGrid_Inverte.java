package com.pml.Simulacoes.PreencheGrid_Inverte;

import com.pml.Simulacoes.AberturaCompleto.*;
import com.pml.InterfaceGrafica.IG;
import com.pml.InterfaceGrafica.IG_GerRisco;
import com.pml.Ordens.LadoOrdem;
import com.pml.Simulacoes.IG_InterfaceSimulacao;

/**
 *
 * @author fabra
 */
public class IG_PreencheGrid_Inverte extends IG_InterfaceSimulacao {

    public IG_PreencheGrid_Inverte() {
    }

    @Override
    public void abreinterface() {
        initComponents();
        super.abreinterface();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        botaoExecutar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRelatorioDetalhado = new javax.swing.JRadioButton();
        jRelatorioCompleto = new javax.swing.JRadioButton();
        botaoAbortar = new javax.swing.JButton();
        botaoGerRisco = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPos = new javax.swing.JFormattedTextField();
        jMov = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPosMax = new javax.swing.JFormattedTextField();
        jPosMaxFin = new javax.swing.JFormattedTextField();
        jDeltaFin = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPassoPosMax = new javax.swing.JFormattedTextField();
        jPassoDelta = new javax.swing.JFormattedTextField();
        jPassoOffset = new javax.swing.JFormattedTextField();
        jPosInverte = new javax.swing.JFormattedTextField();
        jPosInverteFin = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jPassoLoss = new javax.swing.JFormattedTextField();
        jOffsetFin = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jOffset = new javax.swing.JFormattedTextField();
        jDelta = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        getContentPane().add(botaoExecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 102, -1));

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
                    .addComponent(jRelatorioDetalhado)
                    .addComponent(jRelatorioCompleto))
                .addContainerGap(12, Short.MAX_VALUE))
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

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        botaoAbortar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoAbortar.setText("Abortar");
        botaoAbortar.setEnabled(false);
        botaoAbortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbortarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoAbortar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 102, -1));

        botaoGerRisco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoGerRisco.setText("Ger. Risco");
        botaoGerRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerRiscoActionPerformed(evt);
            }
        });
        getContentPane().add(botaoGerRisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 102, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configurações", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Ordem Inicial:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, -1, -1));

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");
        jPanel4.add(jPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 23, 50, -1));

        jMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venda" }));
        jPanel4.add(jMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 24, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Pos Max:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 77, -1, -1));

        jPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMax.setText("1");
        jPanel4.add(jPosMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 73, 50, -1));

        jPosMaxFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMaxFin.setText("1");
        jPosMaxFin.setEnabled(false);
        jPanel4.add(jPosMaxFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 73, 50, -1));

        jDeltaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jDeltaFin.setText("1");
        jDeltaFin.setEnabled(false);
        jPanel4.add(jDeltaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 157, 50, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Pos Max Fin");
        jLabel5.setEnabled(false);
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 77, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Gain Fin");
        jLabel8.setEnabled(false);
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 161, -1, -1));

        jPassoPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoPosMax.setText("1");
        jPassoPosMax.setToolTipText("");
        jPassoPosMax.setEnabled(false);
        jPanel4.add(jPassoPosMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 50, -1));

        jPassoDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoDelta.setText("1");
        jPassoDelta.setToolTipText("");
        jPassoDelta.setEnabled(false);
        jPanel4.add(jPassoDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 50, 20));

        jPassoOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoOffset.setText("1");
        jPassoOffset.setToolTipText("");
        jPassoOffset.setEnabled(false);
        jPanel4.add(jPassoOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 50, 20));

        jPosInverte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosInverte.setText("1");
        jPanel4.add(jPosInverte, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 101, 50, -1));

        jPosInverteFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosInverteFin.setText("1");
        jPosInverteFin.setEnabled(false);
        jPanel4.add(jPosInverteFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 101, 50, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Pós Inv. Fin");
        jLabel6.setEnabled(false);
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 105, -1, -1));

        jPassoLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoLoss.setText("1");
        jPassoLoss.setToolTipText("");
        jPassoLoss.setEnabled(false);
        jPanel4.add(jPassoLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 50, 20));

        jOffsetFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffsetFin.setText("1");
        jOffsetFin.setEnabled(false);
        jPanel4.add(jOffsetFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 129, 50, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Passo:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Lim Op Fin");
        jLabel13.setEnabled(false);
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 133, -1, -1));

        jOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffset.setText("1");
        jPanel4.add(jOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 129, 50, -1));

        jDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jDelta.setText("1");
        jPanel4.add(jDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 157, 50, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Pós Inversao:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 105, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Offset:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 133, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Delta:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 161, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 380, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExecutarActionPerformed
        cliqueBotaoExecutar(new AberturaCompleto(), botaoExecutar, botaoAbortar, botaoGerRisco, null);
    }//GEN-LAST:event_botaoExecutarActionPerformed

    private void botaoAbortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbortarActionPerformed
        cliqueBotaoAbortar();
    }//GEN-LAST:event_botaoAbortarActionPerformed

    private void botaoGerRiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerRiscoActionPerformed
        cliqueBotaoGerRisco();
    }//GEN-LAST:event_botaoGerRiscoActionPerformed

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        cliqueBotaoFechar();
    }//GEN-LAST:event_close

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton botaoAbortar;
    public static javax.swing.JButton botaoExecutar;
    public static javax.swing.JButton botaoGerRisco;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField jDelta;
    private javax.swing.JFormattedTextField jDeltaFin;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> jMov;
    private javax.swing.JFormattedTextField jOffset;
    private javax.swing.JFormattedTextField jOffsetFin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JFormattedTextField jPassoDelta;
    private javax.swing.JFormattedTextField jPassoLoss;
    private javax.swing.JFormattedTextField jPassoOffset;
    private javax.swing.JFormattedTextField jPassoPosMax;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JFormattedTextField jPosInverte;
    private javax.swing.JFormattedTextField jPosInverteFin;
    private javax.swing.JFormattedTextField jPosMax;
    private javax.swing.JFormattedTextField jPosMaxFin;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    // End of variables declaration//GEN-END:variables


    @Override
    protected void atualizaInterface() {
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);
        
        jPosMaxFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoPosMax.setEnabled(jRelatorioCompleto.isSelected());
        jLabel13.setEnabled(jRelatorioCompleto.isSelected());
        
        jOffsetFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoOffset.setEnabled(jRelatorioCompleto.isSelected());
        jLabel8.setEnabled(jRelatorioCompleto.isSelected());
        
        jPosInverteFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoLoss.setEnabled(jRelatorioCompleto.isSelected());
        jLabel6.setEnabled(jRelatorioCompleto.isSelected());
        
        jDeltaFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoDelta.setEnabled(jRelatorioCompleto.isSelected());
        jLabel9.setEnabled(jRelatorioCompleto.isSelected());
    }
    
    @Override
    protected void getVariaveisDaInterfaceDaSimulacao() {
         // LOSS É A POSIÇÃO APOS INVERSAO
        temCompleto = jRelatorioCompleto.isSelected();
        
        passoDelta = Double.parseDouble(jPassoDelta.getText().replace(",", "."));
        passoOffset = Double.parseDouble(jPassoLoss.getText().replace(",", "."));
        passoLoss = Double.parseDouble(jPassoLoss.getText().replace(",", "."));
        
        atualQtde = true;
        temDelta = true;
        temOffset = true;
        temStop = true; //RETIRAR A VERIFICAÇÃO NA SIMULAÇÃO, USAR APENAS PARA TER OS DADOS REGISTRADOS
        
        pos = Integer.parseInt(jPos.getText().replace(",", "."));
        posMax = Integer.parseInt(jPosMax.getText().replace(",", "."));
        posMaxFin = Integer.parseInt(jPosMaxFin.getText().replace(",", "."));
        passoPos = Integer.parseInt(jPassoPosMax.getText().replace(",", "."));
        
        delta = Double.parseDouble(jDelta.getText().replace(",", "."));
        deltaFin = Double.parseDouble(jDeltaFin.getText().replace(",", "."));
        e = Double.parseDouble(jOffset.getText().replace(",", "."));
        eFin = Double.parseDouble(jOffsetFin.getText().replace(",", "."));
        l =  Double.parseDouble(jPosInverte.getText().replace(",", "."));
        lFin =  Double.parseDouble(jPosInverteFin.getText().replace(",", "."));

        mov = switch (jMov.getSelectedIndex()){
            default -> LadoOrdem.COMPRA;
            case 1 -> LadoOrdem.VENDA;
        };
    }
    
    

@Override
public String getNome() {
        return "Preenche Grid Inverte";
    }


}

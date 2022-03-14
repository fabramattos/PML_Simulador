package com.pml.InterfaceGrafica;

import javax.swing.JOptionPane;
import com.pml.simulacao.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Simulacoes.Sim_ExtremosDiaAnterior;
import com.pml.Resumos.Relatorios;

public class IG_Sim_ExtremosDiaAnterior extends javax.swing.JFrame {

    Thread t1, threadTimer;
    
    public IG_Sim_ExtremosDiaAnterior() {
        initComponents();
        IG.setPodeAbrirSimulacao(false);
        IG_GerRisco.resetPodeSimular();
        threadTimer = new Thread(() -> {
            while(this.isVisible()){
                try {
                    verificaDados();
                    verificaConfigRelatorios();
                    verificaConfigValores();
                    Thread.sleep(500);
                } catch (InterruptedException ex) {}
            }
        });
        threadTimer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jRelatorioDetalhado = new javax.swing.JRadioButton();
        jRelatorioCompleto = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mouse(evt);
            }
        });
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
        jRelatorioDetalhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });

        buttonGroup1.add(jRelatorioCompleto);
        jRelatorioCompleto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRelatorioCompleto.setText("Completo");
        jRelatorioCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });

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

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Executar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Abortar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Ger. Risco");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
        jTemGain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
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
        jTemLoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
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
        jMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMovatualizaInterface(evt);
            }
        });
        jPanel2.add(jMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jTemContMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemContMov.setText("Contra Mov.");
        jTemContMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTemContMovatualizaInterface(evt);
            }
        });
        jPanel2.add(jTemContMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Qtde");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");
        jPanel2.add(jPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 50, -1));

        jTemDelta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemDelta.setText("Delta");
        jTemDelta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
        jPanel2.add(jTemDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            IG.textoLimpa();
            IG.progressoCompletoReseta();
            IG.configuraGerais();
            IG.setNomeSimulacao("Extremos Dia Anterior");
            configuraEstrategia();
            t1 = new Thread(() -> {
                jButton1.setEnabled(false);
                executaSimulacao();
                jButton1.setEnabled(true);
            });
              t1.start();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Campo de texto contém erro");
            IG.textoAdd("erro: " + e.getMessage() + "\n");
            IG.textoAdd("Verifique os valores e tente novamente");
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void atualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizaInterface
        verificaConfigRelatorios();
        verificaConfigValores();
        verificaDados();
        try{
            IG_GerRisco.verificaInterface();
        }catch(NullPointerException e){
            System.out.println("nao iniciado");
        }
    }//GEN-LAST:event_atualizaInterface

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        IG.aborta(jButton1, t1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        threadTimer.stop();
        IG.aborta(jButton1, t1);
        IG.setPodeAbrirSimulacao(true);
    }//GEN-LAST:event_close

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new IG_GerRisco().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMovatualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMovatualizaInterface
        verificaConfigRelatorios();
        verificaDados();
        try{
            IG_GerRisco.verificaInterface();
        }catch(NullPointerException e){
            System.out.println("nao iniciado");
        }
    }//GEN-LAST:event_jMovatualizaInterface

    private void jTemContMovatualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTemContMovatualizaInterface
        verificaConfigRelatorios();
        verificaDados();
        try{
            IG_GerRisco.verificaInterface();
        }catch(NullPointerException e){
            System.out.println("nao iniciado");
        }
    }//GEN-LAST:event_jTemContMovatualizaInterface

    private void mouse(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouse
        verificaConfigValores();
    }//GEN-LAST:event_mouse

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
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

    private void verificaConfigRelatorios() {                                
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);
    }                               
        
    public static void botaoExec(boolean clicavel){
        jButton1.setEnabled(clicavel);
    }
    
    /**
     * VERIFICA SE ESTRATEGIA ESTÁ EM USO (COL 0)
     */
    private void verificaConfigValores(){
        verificaDados();
        
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
    }
    
    /**
     * Prepara a planilha colocando os valores FALSE em todos checkbox.
     * Rodar uma unica vez
     */
    
    private void verificaDados(){
        jButton1.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
    }
    
    private void executaSimulacao(){
        Sim_ExtremosDiaAnterior simulacao = new Sim_ExtremosDiaAnterior(false);
        if(jRelatorioDetalhado.isSelected())
            new Relatorios().detalhado(simulacao);
        else
            new Relatorios().completo(simulacao);
    }
    
    private void configuraEstrategia() throws NumberFormatException {
        boolean
        temCompleto = jRelatorioCompleto.isSelected();
        
        double
        passoDelta = Double.parseDouble(jPassoDelta.getText().replace(",", ".")),
        passoOffset = 1,
        passoLimOp = 1,
        passoGain = Double.parseDouble(jPassoGain.getText().replace(",", ".")),
        passoLoss = Double.parseDouble(jPassoLoss.getText().replace(",", "."));
        
        ConfigOrdens.configuraCompleto(temCompleto);
        
        //INICIA VARREDURA DA LISTA
        boolean 
        atualQtde = false,
        temDelta = jTemDelta.isSelected(),
        temOffset = false,
        temLimOp = false,
        temAlvo = jTemGain.isSelected(),
        temStop = jTemLoss.isSelected(),
        temContMov = jTemContMov.isSelected();
        
        int 
        pos = Integer.parseInt(jPos.getText().replace(",", ".")),
        posMax = 1,
        posMaxFin = 1,
        passoPos = 1;
        
        double
        delta = Double.parseDouble(jDelta.getText().replace(",", ".")),
        deltaFin = Double.parseDouble(jDeltaFin.getText().replace(",", ".")),
        e = 0, eFin = 0,
        lim = 0, limFin = 0,
        g = Double.parseDouble(jGain.getText().replace(",", ".")),
        gFin = Double.parseDouble(jGainFin.getText().replace(",", ".")),
        l = Double.parseDouble(jLoss.getText().replace(",", ".")),
        lFin = Double.parseDouble(jLossFin.getText().replace(",", "."));

        LadoOrdem mov;
        switch (jMov.getSelectedIndex()){
            case 0:
                mov = LadoOrdem.COMPRA;
                break;
    
            case 1: 
                mov = LadoOrdem.VENDA;
                break;
                
            default:
                mov = LadoOrdem.INDEF;
                break;
        }
        
        ConfigOrdens.setBooleans(temDelta, temLimOp, temOffset, temAlvo, temStop, temContMov, atualQtde);
        ConfigOrdens.setBase(mov, pos, posMax, posMaxFin);
        ConfigOrdens.setEstrategiaLoop(delta, deltaFin, lim, limFin, e, eFin, g, gFin, l, lFin);
        ConfigOrdens.setPasso(passoPos, passoDelta, passoOffset, passoLimOp, passoGain, passoLoss);
        ConfigOrdens.limpaConfigTrStop();
    }
}

package com.pml.InterfaceGrafica;

import javax.swing.JOptionPane;
import com.pml.simulacao.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Resumos.Relatorios;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemOCO;
import com.pml.Simulacoes.Sim_MontaPos_MaisMenosOCOPrecos;

/**
 *
 * @author fabra
 */
public class IG_Sim_MontaPos_MaisMenosOCOPrecos extends javax.swing.JFrame {

    Thread t1, threadTimer;
    String nomeArq;
    private static int aguardaMinuto;
    
    /**
     * Creates new form IG_UE
     */
    public IG_Sim_MontaPos_MaisMenosOCOPrecos() {
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
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRelatorioDetalhado = new javax.swing.JRadioButton();
        jRelatorioCompleto = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jPos = new javax.swing.JFormattedTextField();
        jPosMax = new javax.swing.JFormattedTextField();
        jGainFin = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jPosMaxFin = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPassoGain = new javax.swing.JFormattedTextField();
        jPassoPos = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jGain = new javax.swing.JFormattedTextField();
        jOffsetFin = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jPassoOffset = new javax.swing.JFormattedTextField();
        jOffset = new javax.swing.JFormattedTextField();
        jLossFin = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jPassoLoss = new javax.swing.JFormattedTextField();
        jLoss = new javax.swing.JFormattedTextField();
        jTemOffset = new javax.swing.JCheckBox();
        jTemGain = new javax.swing.JCheckBox();
        jTemLoss = new javax.swing.JCheckBox();
        jDeltaFin = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jPassoDelta = new javax.swing.JFormattedTextField();
        jDelta = new javax.swing.JFormattedTextField();
        jTemDelta = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Monta Pos: +/- OCO Preços");
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                teste(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Executar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 90, -1));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRelatorioDetalhado)
                .addGap(32, 32, 32)
                .addComponent(jRelatorioCompleto)
                .addContainerGap(132, Short.MAX_VALUE))
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

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Abortar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 90, -1));

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");
        getContentPane().add(jPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 50, -1));

        jPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMax.setText("1");
        getContentPane().add(jPosMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 50, -1));

        jGainFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGainFin.setText("1");
        jGainFin.setEnabled(false);
        getContentPane().add(jGainFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 50, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Gain Fin");
        jLabel7.setEnabled(false);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, -1, -1));

        jPosMaxFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMaxFin.setText("1");
        jPosMaxFin.setEnabled(false);
        getContentPane().add(jPosMaxFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 50, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Pos Max Fin");
        jLabel6.setEnabled(false);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Passo:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        jPassoGain.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoGain.setText("1");
        jPassoGain.setToolTipText("");
        jPassoGain.setEnabled(false);
        getContentPane().add(jPassoGain, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 50, -1));

        jPassoPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoPos.setText("1");
        jPassoPos.setToolTipText("");
        jPassoPos.setEnabled(false);
        getContentPane().add(jPassoPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 50, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Pos Ini");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Pos Max");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jGain.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jGain.setText("1");
        jGain.setEnabled(false);
        getContentPane().add(jGain, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 50, -1));

        jOffsetFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffsetFin.setText("1");
        jOffsetFin.setEnabled(false);
        getContentPane().add(jOffsetFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 50, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Offset Fin");
        jLabel12.setEnabled(false);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jPassoOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoOffset.setText("1");
        jPassoOffset.setToolTipText("");
        jPassoOffset.setEnabled(false);
        getContentPane().add(jPassoOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 50, -1));

        jOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffset.setText("1");
        jOffset.setEnabled(false);
        getContentPane().add(jOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 50, -1));

        jLossFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLossFin.setText("1");
        jLossFin.setEnabled(false);
        getContentPane().add(jLossFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 50, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Loss Fin");
        jLabel14.setEnabled(false);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        jPassoLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoLoss.setText("1");
        jPassoLoss.setToolTipText("");
        jPassoLoss.setEnabled(false);
        getContentPane().add(jPassoLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 50, -1));

        jLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLoss.setText("1");
        jLoss.setEnabled(false);
        getContentPane().add(jLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 50, -1));

        jTemOffset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemOffset.setText("Offset");
        jTemOffset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
        getContentPane().add(jTemOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jTemGain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemGain.setText("Gain");
        jTemGain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
        getContentPane().add(jTemGain, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jTemLoss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemLoss.setText("Stop");
        jTemLoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
        getContentPane().add(jTemLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jDeltaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jDeltaFin.setText("1");
        jDeltaFin.setEnabled(false);
        getContentPane().add(jDeltaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 50, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Delta Fin");
        jLabel13.setEnabled(false);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, -1));

        jPassoDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoDelta.setText("1");
        jPassoDelta.setToolTipText("");
        jPassoDelta.setEnabled(false);
        getContentPane().add(jPassoDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 50, -1));

        jDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jDelta.setText("1");
        getContentPane().add(jDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 50, -1));

        jTemDelta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemDelta.setSelected(true);
        jTemDelta.setText("Delta");
        jTemDelta.setEnabled(false);
        jTemDelta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
        getContentPane().add(jTemDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Ger. Risco");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            IG.textoLimpa();
            IG.progressoCompletoReseta();
            IG.configuraGerais();
            IG.setNomeSimulacao("Monta Pos: +/- OCO Preços");
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
            IG.textoAdd("Verifique os valores e tente novamente\n");
            jButton1.setEnabled(true);
        }catch(OutOfMemoryError e){
                IG.textoAdd("Sem memória o suficiente. Processo cancelado.\n");
                botaoExec(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void atualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizaInterface
        verificaConfigRelatorios();
        verificaConfigValores();
        try{
            IG_GerRisco.verificaInterface();
        }catch(NullPointerException e){
            System.out.println("nao iniciado");
        }
    }//GEN-LAST:event_atualizaInterface

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        IG.aborta(jButton1, t1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void teste(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teste
        verificaConfigRelatorios();
        verificaConfigValores();
    }//GEN-LAST:event_teste

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        threadTimer.stop();
        IG.aborta(jButton1, t1);
        IG.setPodeAbrirSimulacao(true);
    }//GEN-LAST:event_close

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new IG_GerRisco().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JFormattedTextField jLoss;
    private javax.swing.JFormattedTextField jLossFin;
    private javax.swing.JFormattedTextField jOffset;
    private javax.swing.JFormattedTextField jOffsetFin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoDelta;
    private javax.swing.JFormattedTextField jPassoGain;
    private javax.swing.JFormattedTextField jPassoLoss;
    private javax.swing.JFormattedTextField jPassoOffset;
    private javax.swing.JFormattedTextField jPassoPos;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JFormattedTextField jPosMax;
    private javax.swing.JFormattedTextField jPosMaxFin;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JCheckBox jTemDelta;
    private javax.swing.JCheckBox jTemGain;
    private javax.swing.JCheckBox jTemLoss;
    private javax.swing.JCheckBox jTemOffset;
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
        jPosMaxFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoPos.setEnabled(jRelatorioCompleto.isSelected());
        jPassoOffset.setEnabled(jRelatorioCompleto.isSelected() && jTemOffset.isSelected());
        jPassoGain.setEnabled(jRelatorioCompleto.isSelected() && jTemGain.isSelected());
        jPassoLoss.setEnabled(jRelatorioCompleto.isSelected() && jTemLoss.isSelected());
        
        jOffset.setEnabled(jTemOffset.isSelected());
        jGain.setEnabled(jTemGain.isSelected());
        jLoss.setEnabled(jTemLoss.isSelected());
        
        jPosMaxFin.setEnabled(jRelatorioCompleto.isSelected());
        jOffsetFin.setEnabled(jRelatorioCompleto.isSelected() && jTemOffset.isSelected());
        jDeltaFin.setEnabled(jRelatorioCompleto.isSelected() && jTemDelta.isSelected());
        jGainFin.setEnabled(jRelatorioCompleto.isSelected()&& jTemGain.isSelected());
        jLossFin.setEnabled(jRelatorioCompleto.isSelected() && jTemLoss.isSelected());
        
        jLabel6.setEnabled(jRelatorioCompleto.isSelected());
        jLabel12.setEnabled(jRelatorioCompleto.isSelected() && jTemOffset.isSelected());
        jLabel13.setEnabled(jRelatorioCompleto.isSelected() && jTemDelta.isSelected());
        jLabel7.setEnabled(jRelatorioCompleto.isSelected() && jTemGain.isSelected());
        jLabel14.setEnabled(jRelatorioCompleto.isSelected() && jTemLoss.isSelected());
    }
    
    /**
     * Prepara a planilha colocando os valores FALSE em todos checkbox.
     * Rodar uma unica vez
     */
    
    private void verificaDados(){
        jButton1.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
    }
    
    private void executaSimulacao(){
        Sim_MontaPos_MaisMenosOCOPrecos simulacao = new Sim_MontaPos_MaisMenosOCOPrecos(false);
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
        passoOffset = Double.parseDouble(jPassoOffset.getText().replace(",", ".")),
        passoLimOp = 1,
        passoGain = Double.parseDouble(jPassoGain.getText().replace(",", ".")),
        passoLoss = Double.parseDouble(jPassoLoss.getText().replace(",", "."));
        
        ConfigOrdens.configuraCompleto(temCompleto);
        
        //INICIA VARREDURA DA LISTA
        boolean 
        atualQtde = true,
        temDelta = true,
        temOffset = jTemOffset.isSelected(),
        temLimOp = false,
        temAlvo = jTemGain.isSelected(),
        temStop = jTemLoss.isSelected(),
        temContMov = false;
        
        int 
        pos = Integer.parseInt(jPos.getText().replace(",", ".")),
        posMax = Integer.parseInt(jPosMax.getText().replace(",", ".")),
        posMaxFin = Integer.parseInt(jPosMaxFin.getText().replace(",", ".")),
        passoPos = Integer.parseInt(jPassoPos.getText().replace(",", "."));
        
        double
        delta = Double.parseDouble(jDelta.getText().replace(",", ".")),
        deltaFin = Double.parseDouble(jDeltaFin.getText().replace(",", ".")),
        e = Double.parseDouble(jOffset.getText().replace(",", ".")),
        eFin = Double.parseDouble(jOffsetFin.getText().replace(",", ".")),
        g = Double.parseDouble(jGain.getText().replace(",", ".")),
        gFin = Double.parseDouble(jGainFin.getText().replace(",", ".")),
        l = Double.parseDouble(jLoss.getText().replace(",", ".")),
        lFin = Double.parseDouble(jLossFin.getText().replace(",", ".")),
        lim = 0, limFin = 0;

        LadoOrdem mov = LadoOrdem.INDEF;
        
        ConfigOrdens.setBooleans(temDelta, temLimOp, temOffset, temAlvo, temStop, temContMov, atualQtde);
        ConfigOrdens.setBase(mov, pos, posMax, posMaxFin);
        ConfigOrdens.setEstrategiaLoop(delta, deltaFin, lim, limFin, e, eFin, g, gFin, l, lFin);
        ConfigOrdens.setPasso(passoPos, passoDelta, passoOffset, passoLimOp, passoGain, passoLoss);
        ConfigOrdens.adicionaNaListaDeOrdensFixas_OCO(new OrdemOCO());
        ConfigOrdens.limpaConfigTrStop();
    }

    public static int getAguardaMinuto() {
        return IG_Sim_MontaPos_MaisMenosOCOPrecos.aguardaMinuto;
    }
    
    
}

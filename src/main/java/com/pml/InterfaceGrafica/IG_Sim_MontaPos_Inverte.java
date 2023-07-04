package com.pml.InterfaceGrafica;

import javax.swing.JOptionPane;
import com.pml.simulacao.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Resumos.Relatorios;
import com.pml.Simulacoes.Sim_MontaPos_Inverte;

public class IG_Sim_MontaPos_Inverte extends javax.swing.JFrame {

    Thread t1, threadTimer;
    
    public IG_Sim_MontaPos_Inverte() {
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
        jOffset = new javax.swing.JFormattedTextField();
        jDeltaFin = new javax.swing.JFormattedTextField();
        jOffsetFin = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jPassoDelta = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jPassoOffset = new javax.swing.JFormattedTextField();
        jMov = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPosInv = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jPosInvFin = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jPassoPosInv = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPosMax = new javax.swing.JFormattedTextField();
        jPosMaxFin = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jPassoPosMax = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jPosIni = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Monta Pos: Inverte");
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configurações:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jDelta.setText("1");
        jPanel2.add(jDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 50, -1));

        jOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffset.setText("1");
        jPanel2.add(jOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 50, -1));

        jDeltaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jDeltaFin.setText("1");
        jDeltaFin.setEnabled(false);
        jPanel2.add(jDeltaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 50, -1));

        jOffsetFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffsetFin.setText("1");
        jOffsetFin.setEnabled(false);
        jPanel2.add(jOffsetFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 50, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Offset Fin");
        jLabel6.setEnabled(false);
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        jPassoDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoDelta.setText("1");
        jPassoDelta.setToolTipText("");
        jPassoDelta.setEnabled(false);
        jPanel2.add(jPassoDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 50, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Passo:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jPassoOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoOffset.setText("1");
        jPassoOffset.setToolTipText("");
        jPassoOffset.setEnabled(false);
        jPanel2.add(jPassoOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 50, -1));

        jMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venda" }));
        jMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMovatualizaInterface(evt);
            }
        });
        jPanel2.add(jMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 80, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Delta:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        jPosInv.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosInv.setText("1");
        jPanel2.add(jPosInv, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 50, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Ordem inicial:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));

        jPosInvFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosInvFin.setText("1");
        jPosInvFin.setEnabled(false);
        jPanel2.add(jPosInvFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 50, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Delta Fin");
        jLabel9.setEnabled(false);
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        jPassoPosInv.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoPosInv.setText("1");
        jPassoPosInv.setToolTipText("");
        jPassoPosInv.setEnabled(false);
        jPanel2.add(jPassoPosInv, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 50, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Pos Inversão:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Offset:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        jPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMax.setText("1");
        jPanel2.add(jPosMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 50, -1));

        jPosMaxFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosMaxFin.setText("1");
        jPosMaxFin.setEnabled(false);
        jPanel2.add(jPosMaxFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 50, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Pos Max Fin");
        jLabel13.setEnabled(false);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        jPassoPosMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPassoPosMax.setText("1");
        jPassoPosMax.setToolTipText("");
        jPassoPosMax.setEnabled(false);
        jPanel2.add(jPassoPosMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 50, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Pos Max:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        jPosIni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPosIni.setText("1");
        jPanel2.add(jPosIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 50, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Pós inv. Fin");
        jLabel7.setEnabled(false);
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            IG.textoLimpa();
            IG.progressoCompletoReseta();
            IG.configuraGerais();
            IG.setNomeSimulacao("Monta Pos: Inverte");
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> jMov;
    private javax.swing.JFormattedTextField jOffset;
    private javax.swing.JFormattedTextField jOffsetFin;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoDelta;
    private javax.swing.JFormattedTextField jPassoOffset;
    private javax.swing.JFormattedTextField jPassoPosInv;
    private javax.swing.JFormattedTextField jPassoPosMax;
    private javax.swing.JFormattedTextField jPosIni;
    private javax.swing.JFormattedTextField jPosInv;
    private javax.swing.JFormattedTextField jPosInvFin;
    private javax.swing.JFormattedTextField jPosMax;
    private javax.swing.JFormattedTextField jPosMaxFin;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
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
        jPassoPosMax.setEnabled(jRelatorioCompleto.isSelected());
        jLabel13.setEnabled(jRelatorioCompleto.isSelected());
        
        jPosInvFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoPosInv.setEnabled(jRelatorioCompleto.isSelected());
        jLabel7.setEnabled(jRelatorioCompleto.isSelected());
        
        jOffsetFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoOffset.setEnabled(jRelatorioCompleto.isSelected());
        jLabel6.setEnabled(jRelatorioCompleto.isSelected());
        
        jDeltaFin.setEnabled(jRelatorioCompleto.isSelected());
        jPassoDelta.setEnabled(jRelatorioCompleto.isSelected());
        jLabel9.setEnabled(jRelatorioCompleto.isSelected());
        
    }
    
    /**
     * Prepara a planilha colocando os valores FALSE em todos checkbox.
     * Rodar uma unica vez
     */
    
    private void verificaDados(){
        jButton1.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
    }
    
    private void executaSimulacao(){
        Sim_MontaPos_Inverte simulacao = new Sim_MontaPos_Inverte(false);
        if(jRelatorioDetalhado.isSelected())
            new Relatorios().detalhado(simulacao);
        else
            new Relatorios().completo(simulacao);
    }
    
    private void configuraEstrategia() throws NumberFormatException {
        // LOSS É A POSIÇÃO APOS INVERSAO
        
        boolean
        temCompleto = jRelatorioCompleto.isSelected();
        
        double
        passoDelta = Double.parseDouble(jPassoDelta.getText().replace(",", ".")),
        passoOffset = Double.parseDouble(jPassoOffset.getText().replace(",", ".")),
        passoLimOp = 0,
        passoGain = 0,
        passoLoss = Double.parseDouble(jPassoPosInv.getText().replace(",", "."));
        
        ConfigOrdens.configuraCompleto(temCompleto);
        
        //INICIA VARREDURA DA LISTA
        boolean 
        atualQtde = true,
        temDelta = true,
        temOffset = true,
        temLimOp = false,
        temAlvo = false,
        temStop = true, //RETIRAR A VERIFICAÇÃO NA SIMULAÇÃO, USAR APENAS PARA TER OS DADOS REGISTRADOS
        temContMov = false;
        
        int 
        pos = Integer.parseInt(jPosIni.getText().replace(",", ".")),
        posMax = Integer.parseInt(jPosMax.getText().replace(",", ".")),
        posMaxFin = Integer.parseInt(jPosMaxFin.getText().replace(",", ".")),
        passoPos = Integer.parseInt(jPassoPosMax.getText().replace(",", "."));
        
        double
        delta = Double.parseDouble(jDelta.getText().replace(",", ".")),
        deltaFin = Double.parseDouble(jDeltaFin.getText().replace(",", ".")),
        e = Double.parseDouble(jOffset.getText().replace(",", ".")),
        eFin = Double.parseDouble(jOffsetFin.getText().replace(",", ".")),
        lim = 0, limFin = 0,
        g = 0, gFin = 0,
        l =  Double.parseDouble(jPosInv.getText().replace(",", ".")),
        lFin =  Double.parseDouble(jPosInvFin.getText().replace(",", "."));

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

package com.pml.InterfaceGrafica;

import javax.swing.JOptionPane;
import com.pml.simulacao.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Resumos.Relatorios;
import com.pml.Ordens.LadoOrdem;
import com.pml.Simulacoes.Sim_SuporteEResistenciaTrStop;

/**
 *
 * @author fabra
 */
public class IG_Sim_SuporteEResistenciaTrStop extends javax.swing.JFrame {

    Thread t1, threadTimer;
    String nomeArq;
    
    /**
     * Creates new form IG_UE
     */
    public IG_Sim_SuporteEResistenciaTrStop() {
        initComponents();
        IG.setPodeAbrirSimulacao(false);
        IG_GerRisco.resetPodeSimular();
        IG_TrStop.resetPodeSimular();
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
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTemContMov = new javax.swing.JCheckBox();
        jDelta = new javax.swing.JFormattedTextField();
        jDeltaFin = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jPassoDelta = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 90, -1));

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

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Abortar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 90, -1));

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

        jDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jDelta.setText("1");
        getContentPane().add(jDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 50, -1));

        jDeltaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jDeltaFin.setText("1");
        jDeltaFin.setEnabled(false);
        getContentPane().add(jDeltaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 50, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Lim Tempo Fin");
        jLabel12.setEnabled(false);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        jPassoDelta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPassoDelta.setText("1");
        jPassoDelta.setToolTipText("");
        jPassoDelta.setEnabled(false);
        getContentPane().add(jPassoDelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 50, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Lim Tempo");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Ger. Risco");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 90, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Tr. Stop");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 90, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            IG.textoLimpa();
            IG.progressoCompletoReseta();
            IG.configuraGerais();
            IG.setNomeSimulacao("Suporte e Resistencia Tr.Stop");
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new IG_TrStop().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JButton jButton4;
    private javax.swing.JFormattedTextField jDelta;
    private javax.swing.JFormattedTextField jDeltaFin;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JFormattedTextField jOffset;
    private javax.swing.JFormattedTextField jOffsetFin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoDelta;
    private javax.swing.JFormattedTextField jPassoOffset;
    private javax.swing.JFormattedTextField jPassoPos;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JFormattedTextField jPosMax;
    private javax.swing.JFormattedTextField jPosMaxFin;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JCheckBox jTemContMov;
    // End of variables declaration//GEN-END:variables

    private void verificaConfigRelatorios() {                                
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);
        IG_TrStop.recebeTipoRelatorio(jRelatorioCompleto);
    }                               
        
    public static void botaoExec(boolean clicavel){
        jButton1.setEnabled(clicavel);
    }
    
    /**
     * VERIFICA SE ESTRATEGIA ESTÁ EM USO (COL 0)
     */
    private void verificaConfigValores(){
        verificaDados();
        
        jPassoPos.setEnabled(jRelatorioCompleto.isSelected());
        jPosMaxFin.setEnabled(jRelatorioCompleto.isSelected());
        jLabel16.setEnabled(jRelatorioCompleto.isSelected());
        
        jPassoDelta.setEnabled(jRelatorioCompleto.isSelected());
        jDeltaFin.setEnabled(jRelatorioCompleto.isSelected());
        jLabel12.setEnabled(jRelatorioCompleto.isSelected());
        
        jPassoOffset.setEnabled(jRelatorioCompleto.isSelected());
        jOffsetFin.setEnabled(jRelatorioCompleto.isSelected());
        jLabel14.setEnabled(jRelatorioCompleto.isSelected());
    }
    
    /**
     * Prepara a planilha colocando os valores FALSE em todos checkbox.
     * Rodar uma unica vez
     */
    
    private void verificaDados(){
        jButton1.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular() && IG_TrStop.isPodeSimular());
    }
    
    private void executaSimulacao(){
        Sim_SuporteEResistenciaTrStop simulacao = new Sim_SuporteEResistenciaTrStop(false);
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
        passoGain = 1,
        passoLoss = 1;
        
        ConfigOrdens.configuraCompleto(temCompleto);
        
        //INICIA VARREDURA DA LISTA
        boolean 
        atualQtde = true,
        temDelta = true,
        temOffset = true,
        temLimOp = false,
        temAlvo = false,
        temStop = false,
        temContMov = jTemContMov.isSelected();
        
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
        lim = 0, limFin = 0, 
        g = 0, gFin = 0,
        l =  0, lFin = 0;

        LadoOrdem mov = LadoOrdem.INDEF;
        
        ConfigOrdens.setBooleans(temDelta, temLimOp, temOffset, temAlvo, temStop, temContMov, atualQtde);
        ConfigOrdens.setBase(mov, pos, posMax, posMaxFin);
        ConfigOrdens.setEstrategiaLoop(delta, deltaFin, lim, limFin, e, eFin, g, gFin, l, lFin);
        ConfigOrdens.setPasso(passoPos, passoDelta, passoOffset, passoLimOp, passoGain, passoLoss);
        String quebraLinha = System.lineSeparator();
        ConfigOrdens.setObservacoes("DELTA representa o TEMPO LIMITE (em minutos)" + quebraLinha
                                    + "para a variação da cotação atual superar" + quebraLinha
                                    + "a distancia das linhas de Suporte e Resistencia." + quebraLinha
                                    + "Caso não atinja tal valor, reinicia as referencias.");
    }
}

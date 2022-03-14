package com.pml.InterfaceGrafica;

import javax.swing.JOptionPane;
import com.pml.simulacao.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Simulacoes.Sim_AberturaMercFut;
import com.pml.Resumos.Relatorios;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemSimples;

/**
 *
 * @author fabra
 */
public class IG_Sim_AberturaMercFut extends javax.swing.JFrame {

    Thread t1, threadTimer;
    String nomeArq;
    
    /**
     * Creates new form IG_UE
     */
    public IG_Sim_AberturaMercFut() {
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
        jOffsetFin = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jPassoOffset = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jTemContMov = new javax.swing.JCheckBox();
        jOffset = new javax.swing.JFormattedTextField();
        jTemOffset = new javax.swing.JCheckBox();
        jTemLimOp = new javax.swing.JCheckBox();
        jLimOp = new javax.swing.JFormattedTextField();
        jLimOpFin = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jPassoLimOp = new javax.swing.JFormattedTextField();
        jMov = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Day Trade: Abertura Merc Futuro");
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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, -1));

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
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRelatorioDetalhado)
                    .addComponent(jRelatorioCompleto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 60));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Abortar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        jPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPos.setText("1");
        getContentPane().add(jPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 50, -1));

        jOffsetFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffsetFin.setText("1");
        jOffsetFin.setEnabled(false);
        getContentPane().add(jOffsetFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 50, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Lim Op Final");
        jLabel7.setEnabled(false);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        jPassoOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoOffset.setText("1");
        jPassoOffset.setToolTipText("");
        jPassoOffset.setEnabled(false);
        getContentPane().add(jPassoOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 50, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Qtde");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jTemContMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemContMov.setText("Contra Mov.");
        getContentPane().add(jTemContMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jOffset.setText("1");
        jOffset.setEnabled(false);
        getContentPane().add(jOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 50, -1));

        jTemOffset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemOffset.setText("Offset");
        jTemOffset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
        getContentPane().add(jTemOffset, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jTemLimOp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTemLimOp.setText("Lim Op");
        jTemLimOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaInterface(evt);
            }
        });
        getContentPane().add(jTemLimOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLimOp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLimOp.setText("1");
        jLimOp.setEnabled(false);
        getContentPane().add(jLimOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 50, -1));

        jLimOpFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jLimOpFin.setText("1");
        jLimOpFin.setEnabled(false);
        getContentPane().add(jLimOpFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 50, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Offset Fin");
        jLabel8.setEnabled(false);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));

        jPassoLimOp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jPassoLimOp.setText("1");
        jPassoLimOp.setToolTipText("");
        jPassoLimOp.setEnabled(false);
        jPassoLimOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPassoLimOpActionPerformed(evt);
            }
        });
        getContentPane().add(jPassoLimOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 50, -1));

        jMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venda" }));
        getContentPane().add(jMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Passo:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Ger. Risco");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            IG.textoLimpa();
            IG.progressoCompletoReseta();
            IG.configuraGerais();
            IG.setNomeSimulacao("Abertura Mercado Futuro");
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

    private void jPassoLimOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPassoLimOpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPassoLimOpActionPerformed

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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JFormattedTextField jLimOp;
    private javax.swing.JFormattedTextField jLimOpFin;
    private javax.swing.JComboBox<String> jMov;
    private javax.swing.JFormattedTextField jOffset;
    private javax.swing.JFormattedTextField jOffsetFin;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField jPassoLimOp;
    private javax.swing.JFormattedTextField jPassoOffset;
    private javax.swing.JFormattedTextField jPos;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JCheckBox jTemContMov;
    private javax.swing.JCheckBox jTemLimOp;
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
        jPassoLimOp.setEnabled(jRelatorioCompleto.isSelected() && jTemLimOp.isSelected());
        jPassoOffset.setEnabled(jRelatorioCompleto.isSelected() && jTemOffset.isSelected());
        
        jLimOp.setEnabled(jTemLimOp.isSelected());
        jOffset.setEnabled(jTemOffset.isSelected());
        
        jLimOpFin.setEnabled(jRelatorioCompleto.isSelected() && jTemLimOp.isSelected());
        jOffsetFin.setEnabled(jRelatorioCompleto.isSelected() && jTemOffset.isSelected());
        
        jLabel7.setEnabled(jRelatorioCompleto.isSelected() && jTemLimOp.isSelected());
        jLabel8.setEnabled(jRelatorioCompleto.isSelected() && jTemOffset.isSelected());
    }
    
    /**
     * Prepara a planilha colocando os valores FALSE em todos checkbox.
     * Rodar uma unica vez
     */
    
    private void verificaDados(){
        jButton1.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
    }
    
    private void executaSimulacao(){
        Sim_AberturaMercFut simulacao = new Sim_AberturaMercFut(false);
        if(jRelatorioDetalhado.isSelected())
            new Relatorios().detalhado(simulacao);
        else
            new Relatorios().completo(simulacao);
    }
    
    private void configuraEstrategia() throws NumberFormatException {
        boolean
        temCompleto = jRelatorioCompleto.isSelected();
        
        double
        passoDelta = 1,
        passoLimOp = Double.parseDouble(jPassoLimOp.getText().replace(",", ".")),
        passoOffset = Double.parseDouble(jPassoOffset.getText().replace(",", ".")),
        passoGain = 1,
        passoLoss = 1;
        
        ConfigOrdens.configuraCompleto(temCompleto);
        
        //INICIA VARREDURA DA LISTA
        boolean 
        atualQtde = false,
        temDelta = false,
        temOffset = jTemOffset.isSelected(),
        temLimOp = jTemLimOp.isSelected(),
        temAlvo = false,
        temStop = false,
        temContMov = jTemContMov.isSelected();

        int 
        pos = Integer.parseInt(jPos.getText().replace(",", ".")),
        posMax = 1,
        posMaxFin = 1,
        passoPos = 1;
        
        double
        delta = 0, deltaFin = 0,
        e = Double.parseDouble(jOffset.getText().replace(",", ".")),
        eFin = Double.parseDouble(jOffsetFin.getText().replace(",", ".")),
        lim = Double.parseDouble(jLimOp.getText().replace(",", ".")),
        limFin = Double.parseDouble(jLimOpFin.getText().replace(",", ".")),
        g = 0, gFin = 0,
        l = 0, lFin = 0;

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
        ConfigOrdens.adicionaNaListaDeOrdensFixas_OCO(new OrdemSimples());
        ConfigOrdens.limpaConfigTrStop();
    }
}

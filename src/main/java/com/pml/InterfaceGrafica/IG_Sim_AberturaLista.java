package com.pml.InterfaceGrafica;

import javax.swing.JOptionPane;
import com.pml.simulacao.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Resumos.Relatorios;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemOCO;
import com.pml.Ordens.OrdemSimples;
import com.pml.Simulacoes.Sim_AberturaLista;
import static com.pml.InterfaceGrafica.IG.setPodeAbrirSimulacao;

/**
 *
 * @author fabra
 */
public class IG_Sim_AberturaLista extends javax.swing.JFrame {

    Thread threadSimulacao, threadTimer;
    double maxContador = 1;
    volatile boolean exit = false;
    /**
     * Creates new form IG_UE
     */
    public IG_Sim_AberturaLista() {
        initComponents();
        IG_GerRisco.resetPodeSimular();
        IG.setPodeAbrirSimulacao(false);
        preparaPlanilha();
        threadTimer = new Thread(() -> {
            while(true){
               // System.out.println("Thread ativo!");
                try {
                    verificaDados();
                    verificaConfigRelatorios();
                    threadTimer.sleep(500);
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListaEstrat = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DayTrade: Abertura Lista");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Executar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                    .addComponent(jRelatorioCompleto)
                    .addComponent(jRelatorioDetalhado))
                .addGap(0, 8, Short.MAX_VALUE))
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configurações:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jListaEstrat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jListaEstrat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mov", "ContMov", "Qtde", "E", "E Ini", "LimOp", "LimOp Ini", "G", "G Ini", "L", "L Ini"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jListaEstrat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jListaEstrat.setRowSelectionAllowed(false);
        jListaEstrat.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jListaEstrat);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            IG.textoLimpa();
            IG.progressoCompletoReseta();
            IG.configuraGerais();
            IG.setNomeSimulacao("Abertura Lista");
            configuraEstrategia();
            threadSimulacao = new Thread(() -> {
                jButton1.setEnabled(false);
                executaSimulacao();
                jButton1.setEnabled(true);
            });
            threadSimulacao.start();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Campo de texto contém erro");
            IG.textoAdd("erro: " + e.getMessage() + "\n");
            IG.textoAdd("Verifique os valores e tente novamente\n");
            jButton1.setEnabled(true);
        }catch(OutOfMemoryError e){
            IG.textoAdd("Sem memória o suficiente. Processo cancelado.\n");
            botaoExec(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Campo de texto contém erro");
            botaoExec(true);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void atualizaInterface(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizaInterface
        verificaConfigRelatorios();
        try{
            IG_GerRisco.verificaInterface();
        }catch(NullPointerException e){
            System.out.println("nao iniciado");
        }
    }//GEN-LAST:event_atualizaInterface

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        IG.aborta(jButton1, threadSimulacao);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new IG_GerRisco().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        threadTimer.stop();
        IG.aborta(jButton1, threadSimulacao);
        setPodeAbrirSimulacao(true);
    }//GEN-LAST:event_close

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    private javax.swing.JTable jListaEstrat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void verificaConfigRelatorios() {                                
        IG.setTemRelCompletoDiario(jRelatorioCompleto.isSelected());
        IG_GerRisco.recebeTipoRelatorio(jRelatorioCompleto);

        if(jRelatorioDetalhado.isSelected()){
            for (int C = 0; C<jListaEstrat.getColumnCount(); C++){
                String nome = jListaEstrat.getColumnName(C).toLowerCase();
                if("e fin".equals(nome) || "limop fin".equals(nome)
                || "g fin".equals(nome) || "l fin".equals(nome)){
                    for(int L = 0; L<jListaEstrat.getRowCount(); L++){
                        jListaEstrat.setValueAt(null, L, C);
                    }
                }
            }
        }
    }                               
        
    public static void botaoExec(boolean clicavel){
        jButton1.setEnabled(clicavel);
    }
    
    /**
     * Prepara a planilha colocando os valores FALSE em todos checkbox.
     * Rodar uma unica vez
     */
    private void preparaPlanilha(){
        for(int L = 0; L < jListaEstrat.getRowCount(); L++){
            for (int C = 0; C<jListaEstrat.getColumnCount(); C++){
                String nome = jListaEstrat.getColumnName(C).toLowerCase();
                if("em uso".equals(nome) || "e".equals(nome)
                || "limop".equals(nome) || "g".equals(nome) || "l".equals(nome))
                    jListaEstrat.setValueAt(false, L, C);
            }
        }
    }
    
    private void verificaDados(){
        jButton1.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
    }
    
    private void executaSimulacao(){
        if(jRelatorioDetalhado.isSelected())
            new Relatorios().detalhado(new Sim_AberturaLista(false));
        else
            new Relatorios().completo(new Sim_AberturaLista(false));
    }
    
    private void configuraEstrategia() throws NumberFormatException {
        boolean
        temCompleto = jRelatorioCompleto.isSelected(),
        atualQtde = false,
        temContMov = false;
        
        double
        passo = 1;
        
        ConfigOrdens.configuraCompleto(temCompleto);
        
        //INICIA VARREDURA DA LISTA
        for(int L = 0; L < jListaEstrat.getRowCount(); L++){
            LadoOrdem ladoOrdem = LadoOrdem.INDEF;

            int 
            posIni = 0, posMaxIni = 0, posMaxFin = 0;
            
            double
            delta = 0, deltaFin = 0,
            lim = 0, limFin = 0,
            e = 0, eFin = 0,
            g = 0, gFin = 0,
            l = 0, lFin = 0;
            
            boolean 
            temDelta = false,
            temOffset = false,
            temLimOp = false,
            temAlvo = false,
            temStop = false;
            
            //VERIFICA SE ESTRATEGIA ESTÁ EM USO
            if(jListaEstrat.getValueAt(L, 0) != null &&  jListaEstrat.getValueAt(L, 0).toString().toLowerCase().contains("c")
            || jListaEstrat.getValueAt(L, 0) != null &&  jListaEstrat.getValueAt(L, 0).toString().toLowerCase().contains("v")){
                //INICIA LOOP PARA MONTAR ESTRATEGIA
                for (int C = 0; C<jListaEstrat.getColumnCount(); C++){
                    String valor, nome;
                    nome = jListaEstrat.getColumnName(C).toLowerCase();
                    //COLUNAS CHECKBOX
                    if("contmov".equals(nome) || "e".equals(nome) || "limop".equals(nome) || "g".equals(nome) || "l".equals(nome)){
                        if(jListaEstrat.getValueAt(L, C) == null)
                            valor = "false";
                        else
                            valor = jListaEstrat.getValueAt(L, C).toString();
                    } else{
                        if(jListaEstrat.getValueAt(L, C) == null)
                            valor = "0";
                        else
                            valor = jListaEstrat.getValueAt(L, C).toString().toLowerCase().replace(",", ".");
                    }
                    
                    switch (nome){
                        case "mov":
                            if(valor.contains("c"))
                                ladoOrdem = LadoOrdem.COMPRA;
                            else
                                ladoOrdem = LadoOrdem.VENDA;
                            break;
                            
                        case "contmov":
                            temContMov = Boolean.parseBoolean(valor);
                            break;
                            
                        case "qtde":
                            posIni = Integer.parseInt(valor);
                            break;

                        case "e":
                            temOffset = Boolean.parseBoolean(valor);
                            break;

                        case "e ini":
                            e = Double.parseDouble(valor);
                            break;

                        case "e fin":
                            eFin = Double.parseDouble(valor);
                            break;

                        case "limop":
                            temLimOp = Boolean.parseBoolean(valor);
                            break;

                        case "limop ini":
                            lim = Double.parseDouble(valor);
                            break;

                        case "limop fin":
                            limFin = Double.parseDouble(valor);
                            break;

                        case "g":
                            temAlvo = Boolean.parseBoolean(valor);
                            break;

                        case "g ini":
                            g = Double.parseDouble(valor);
                            break;

                        case "g fin":
                            gFin = Double.parseDouble(valor);
                            break;

                        case "l":
                            temStop = Boolean.parseBoolean(valor);
                            break;

                        case "l ini":
                            l = Double.parseDouble(valor);
                            break;

                        case "l fin":
                            lFin = Double.parseDouble(valor);
                            break;
                    }
                }
                
                // PARA ARRUMAR O FINAL SER MENOR Q O INICIAL NO LOOP:
                eFin = e;
                limFin = lim;
                deltaFin = delta;
                gFin = g;
                lFin = l;
                
                posMaxIni = 9999;
                posMaxFin = 9999;
                
                ConfigOrdens.setBooleans(temDelta, temLimOp, temOffset, temAlvo, temStop, temContMov, atualQtde);
                ConfigOrdens.setBase(ladoOrdem, posIni, posMaxIni, posMaxFin);
                ConfigOrdens.setEstrategiaLoop(delta, deltaFin, lim, limFin, e, eFin, g, gFin, l, lFin);
                ConfigOrdens.setPasso(passo);
                if(temAlvo || temStop)
                    ConfigOrdens.adicionaNaListaDeOrdensFixas_OCO(new OrdemOCO());
                else
                    ConfigOrdens.adicionaNaListaDeOrdensFixas_OCO(new OrdemSimples());
                ConfigOrdens.limpaConfigTrStop();
            }// FIM IF VERIFICAÇÃO EST EM USO
        }
    }
}

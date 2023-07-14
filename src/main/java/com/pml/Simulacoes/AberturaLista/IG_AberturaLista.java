package com.pml.Simulacoes.AberturaLista;

import com.pml.infra.Candle;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.InterfaceGrafica.IG;
import com.pml.InterfaceGrafica.IG_GerRisco;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemOCO;
import com.pml.Ordens.OrdemSimples;
import com.pml.Simulacoes.IG_InterfaceSimulacao;

/**
 *
 * @author fabra
 */
public class IG_AberturaLista extends IG_InterfaceSimulacao{
    
    public IG_AberturaLista() {}
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        botaoExecutar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRelatorioDetalhado = new javax.swing.JRadioButton();
        jRelatorioCompleto = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListaEstrat = new javax.swing.JTable();
        botaoAbortar = new javax.swing.JButton();
        botaoGerRisco = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DayTrade: Abertura Lista");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });

        botaoExecutar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoExecutar.setText("Executar");
        botaoExecutar.setEnabled(false);
        botaoExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExecutarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAbortar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoGerRisco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(botaoGerRisco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExecutar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAbortar)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExecutarActionPerformed
        cliqueBotaoExecutar(new AberturaLista(), botaoExecutar, botaoAbortar, botaoGerRisco, null);
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
    private javax.swing.JTable jListaEstrat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRelatorioCompleto;
    private javax.swing.JRadioButton jRelatorioDetalhado;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

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
    
    @Override
    public String getNome() {
        return "Abertura Lista";
    }

    @Override
    public void abreinterface() {
        initComponents();
        preparaPlanilha();
        super.abreinterface();
    }

    @Override
    protected void getVariaveisDaInterfaceDaSimulacao() {
        temCompleto = jRelatorioCompleto.isSelected();
        posMax = 9999;
        posMaxFin = posMax;
        
        for(int L = 0; L < jListaEstrat.getRowCount(); L++){
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
                                mov = LadoOrdem.COMPRA;
                            else
                                mov = LadoOrdem.VENDA;
                            break;
                            
                        case "contmov":
                            temContMov = Boolean.parseBoolean(valor);
                            break;
                            
                        case "qtde":
                            pos = Integer.parseInt(valor);
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
                
                configuraOrdens();
                
                if(temAlvo || temStop)
                    ConfigOrdens.adicionaNaListaDeOrdensFixas_OCO(new OrdemOCO());
                else
                    ConfigOrdens.adicionaNaListaDeOrdensFixas_OCO(new OrdemSimples());
            }// FIM IF VERIFICAÇÃO EST EM USO
        }
    }

    @Override
    protected void atualizaInterface() {
        botaoExecutar.setEnabled(!Candle.getListaCandleMinuto().isEmpty() && IG_GerRisco.isPodeSimular());
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
    
}

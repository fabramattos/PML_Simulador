package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.InterfaceGrafica.IG;
import com.pml.InterfaceGrafica.IG_GerRisco;
import com.pml.InterfaceGrafica.IG_TrStop;
import com.pml.Ordens.LadoOrdem;
import com.pml.Resumos.Relatorios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author fabra
 */
public abstract class IG_InterfaceSimulacao extends JFrame{
    
    protected SwingWorker<Void, Void> worker;
    protected Timer timer;
    
    protected boolean temCompleto, atualQtde,
        temDelta, temOffset, temLimOp,
        temAlvo, temStop, temContMov;
    
    protected double passoDelta, passoOffset, passoLimOp, passoGain, passoLoss,
            delta, deltaFin, e, eFin, lim, limFin, g, gFin, l, lFin;

    protected int pos, posMax, posMaxFin, passoPos;
    
    protected LadoOrdem mov = LadoOrdem.INDEF;
    
    private JButton botaoExecutar, botaoAbortar, botaoGerRisco, botaoTrStop;
    
    /**
     * deve ser chamado sempre após os valores baseados na interface serem setados
     */
    protected void configuraOrdens(){
        ConfigOrdens.limpaConfigTrStop();
        ConfigOrdens.configuraCompleto(temCompleto);
        ConfigOrdens.setBooleans(temDelta, temLimOp, temOffset, temAlvo, temStop, temContMov, atualQtde);
        ConfigOrdens.setBase(mov, pos, posMax, posMaxFin);
        ConfigOrdens.setEstrategiaLoop(delta, deltaFin, lim, limFin, e, eFin, g, gFin, l, lFin);
        ConfigOrdens.setPasso(passoPos, passoDelta, passoOffset, passoLimOp, passoGain, passoLoss);
    }
    
    protected abstract void getVariaveisDaInterfaceDaSimulacao();
    
    protected abstract void atualizaInterface();
    
    protected void cliqueBotaoAbortar() {
        if (worker == null || worker.isDone())
            return;
        
        worker.cancel(true);
        IG.textoAdd("\nSIMULAÇÃO CANCELADA PELO USUÁRIO!\n");
    }
    
    protected void cliqueBotaoFechar(){
        cliqueBotaoAbortar();
        timer.stop();
        IG.setPodeAbrirSimulacao(true);
    }
    
    public abstract String getNome();
    
    public void abreinterface(){
        IG_GerRisco.resetPodeSimular();
        IG.setPodeAbrirSimulacao(false);
        setVisible(true);
        setTitle(getNome());
        timer = new Timer(200, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               atualizaInterface();
           }
       });
       timer.start();
    }

    protected void cliqueBotaoGerRisco() {
        new IG_GerRisco().setVisible(true);
    }
    
    protected void cliqueBotaoTrStop(){
        new IG_TrStop().setVisible(true);
    }

    protected void cliqueBotaoExecutar(SimulacaoBase simulacao, JButton botaoExecutar, JButton botaoAbortar, JButton botaoGerRisco, JButton botaoTrStop) {
        this.botaoExecutar = botaoExecutar;
        this.botaoAbortar = botaoAbortar;
        this.botaoGerRisco = botaoGerRisco;
        this.botaoTrStop = botaoTrStop;
        
        try {
            IG.textoLimpa();
            IG.configuraGerais();
            getVariaveisDaInterfaceDaSimulacao();
            configuraOrdens();
            IG.textoAdd("\nSIMULAÇÃO ESCOLHIDA: " + getNome() + "\n");
            IG.textoAdd(simulacao.getDatasSelecionadas());
            IG.setNomeSimulacao(getNome());
            IG.setPodeSelecionarRelatorios(false);
            
           
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    setBotoes(true);
                    executaSimulacao(simulacao);
                    return null;
                }

                @Override
                protected void done() {
                    setBotoes(false);
                }
            };
            worker.execute();
            
        } catch (Exception e ) {
            IG.textoAdd(e.toString());
            JOptionPane.showMessageDialog(this, "Campo com valor inválido! Verifique painel de texto!");
            setBotoes(false);
        }
    }
    
    private void executaSimulacao(SimulacaoBase simulacao) {
        if (temCompleto)
            new Relatorios().completo(simulacao, worker);
        else 
            new Relatorios().detalhado(simulacao, worker);
    }
    
    private void setBotoes(boolean simulacaoEmExecucao){
        botaoExecutar.setEnabled(!simulacaoEmExecucao);
        botaoGerRisco.setEnabled(!simulacaoEmExecucao);
        botaoAbortar.setEnabled(simulacaoEmExecucao);
        
        if(botaoTrStop != null)
            botaoTrStop.setEnabled(!simulacaoEmExecucao);
    }
}

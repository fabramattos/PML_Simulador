/**
 *  INVERSÃO/REENTRADAS/AUTO_GERRISCO
 *      • Parâmetros: Mov, PosMax, Delta, Offset, Loss
 *      • Referência para offset: Abertura do dia
 *      • Lógica da montagem da pos:
 *          o SE for início de uma nova série (novo dia, sendo que o saldo do dia anterior foi positivo, OU, nova série dentro do mesmo dia,
 *              após der saído pelo Saldo), aguarda a formação do candle de Y minutos informado na interface.
 *               Entra a favor da tendência em Delta pontos de um dos extremos
 *               No primeiro loss, inverte a posição.
 *               Depois, a cada “Loss pontos”, opera contra tendência aumentando posição (reentrada) em 1 contrato
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Gerenciamento de risco varia de acordo com cada entrada, sendo:
 *               Excel*  Ger. Risco Saldo  5  2  ...  2
 *               O alvo inicial para o início da série será sempre menor que a diferença entre o extremo do dia e a entrada da série
 *          o Saída pelo fechamento
 *      • *Obs: Excel será o Ger.Risco inicial calculado e disponibilizado na coluna INDICADOR
 * 
 **/

package Simulacoes;

import Configuracoes.ConfigOrdens;
import Ordens.LadoOrdem;
import Ordens.OrdemOCO;
import simulacao.Candle;
import Ordens.OrdemSimples;
import Resumos.ResumoDia;

public class Sim_AutoGerRiscoInverteReentrada extends Simulacao {
    
    public Sim_AutoGerRiscoInverteReentrada(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial,

        //PARA CONTROLAR QUAL ESTADO ESTÁ, E CONFIGURAR O GER. RISCO. 
        //[AUTO, GER.RISCO.ORIGINAL, 5, 2]
        caso = 0; 
        
        boolean
        gravouDia = false,
        podeAtualizarExtremoAnterior = true,
        podeIniciarSerie = false,
        serieIniciada = false,
        gerRisco_compensaSaldoSimplesBackUp = ConfigOrdens.isTemGerRisco_CompensaSaldoSimples(),
        gerRisco_compensaSaldoCumulativoBackUp = ConfigOrdens.isTemGerRisco_CompensaSaldoCumulativo();
        
        
        //CONFIGURA ESTRATEGIAS
        OrdemOCO ord = new OrdemOCO();
        OrdemSimples ordCont = new OrdemSimples();
        
        // DEPENDENDO DO FINAL DO DIA, A QUANTIDADE PARA O DIA SEGUNITE ALTERA
        int qtdeDiaNeg = ord.getQtde();
        
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        //PREPARA CANDLES PARA ANALISE DE EXTREMOS
        double
        delta = ConfigOrdens.getDelta(),
        loss = ConfigOrdens.getLoss(),
        ultimaEntrada = 0,
        maxCandleAnterior = 0,
        minCandleAnterior = 0;
        
        
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                //VERIFICANDO QUAL GER.RISCO UTILIZAR
                switch (caso){
                    case 0:
                        ConfigOrdens.setGerRisco_SaldoDesej(Candle.getListaCandleMinuto().get(i).getIndicadorExtra());
                        break;
                        
                    case 1:
                        ConfigOrdens.setGerRisco_SaldoDesej(ConfigOrdens.getGerRisco_SaldoDesej_Original());
                        break;
                        
                    case 2:
                        ConfigOrdens.setGerRisco_SaldoDesej(5);
                        break;
                    
                    case 3:
                        ConfigOrdens.setGerRisco_SaldoDesej(2);
                        break;
                        
                    default:
                        ConfigOrdens.setGerRisco_SaldoDesej(0);
                        break;
                }
                // PARA TESTAR STOP DA PRIMEIRA ORDEM
                if(serieIniciada)
                    controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                
                gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
                
                //PRIMEIRA ENTRADA DA SÉRIE, ACIMA DA MAX
                if(podeIniciarSerie && Candle.getListaCandleMinuto().get(i).getMaxima() >= maxCandleAnterior + ConfigOrdens.getDelta()){
                    ord = new OrdemOCO();
                    ord.setTemOffset(true);
                    ord.setTemContMov(false);
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                    ord.setOffset(maxCandleAnterior + delta - rDia.getAbertura());
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());

                    serieIniciada = true;
                    podeIniciarSerie = false;
                    podeAtualizarExtremoAnterior = false;

                    if(caso > 3){
                        ord.setQtde(qtdeDiaNeg);
                    }else{
                        //VERIFICA SE JA PASSOU PELO GAIN PREVISTO
                        if(rDia.getMaxLocal() - ord.getLinhaCompra() <= Candle.getListaCandleMinuto().get(i).getIndicadorExtra())
                            caso = 0;
                        else
                            caso = 1;
                    }
                    controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                    ultimaEntrada = (ord.getLinhaReferencia() + ord.getOffset());
                    gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
                }

                //PRIMEIRA ENTRADA DA SÉRIE, ABAIXO DA MIN
                if(podeIniciarSerie && Candle.getListaCandleMinuto().get(i).getMinima() <= minCandleAnterior - ConfigOrdens.getDelta()){
                    ord = new OrdemOCO();
                    ord.setTemOffset(true);
                    ord.setTemContMov(false);
                    ord.setLadoOrdem(LadoOrdem.VENDA);
                    ord.setOffset(minCandleAnterior - delta - rDia.getAbertura());
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());

                    serieIniciada = true;
                    podeIniciarSerie = false;
                    podeAtualizarExtremoAnterior = false;

                    if(caso > 3){
                        ord.setQtde(qtdeDiaNeg);
                    }else{
                        //VERIFICA SE JA PASSOU PELO GAIN PREVISTO
                        if(rDia.getMinLocal() - ord.getLinhaVenda() >= -1*Candle.getListaCandleMinuto().get(i).getIndicadorExtra())
                            caso = 0;
                        else
                            caso = 1;
                    }
                    controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                    ultimaEntrada = (ord.getLinhaReferencia() + ord.getOffset());
                    gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
                }
                
                // VERIFICA PRIMEIRO STOP E FAZ INVERSÃO. PODE SER APOS O CANDLE DA PRIMEIRA ORDEM
                if(serieIniciada && ord.isStopExecutado()){
                    LadoOrdem mov = ord.getLadoOrdem();
                    double linhaStop = ord.getLinhaStop();
                    ord = new OrdemOCO(); //APENAS PARA NAO ENTRAR NOVAMENTE NESSA MESMA VERIFICAÇÃO
                    
                    ordCont = new OrdemSimples();
                    ordCont.setTemOffset(true);
                    ordCont.setTemContMov(true);
                    ordCont.setOffset(linhaStop - rDia.getAbertura());
                    if(mov == LadoOrdem.COMPRA)
                        ordCont.setLadoOrdem(LadoOrdem.VENDA);
                    else
                        ordCont.setLadoOrdem(LadoOrdem.COMPRA);

                    if(caso > 3)
                        ord.setQtde(qtdeDiaNeg);
                    
                    ordCont.configuraLinhasEntradaESaidas(rDia.getAbertura());
                    controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ordCont);

                    caso++;
                    ultimaEntrada = (ordCont.getLinhaReferencia() + ordCont.getOffset());
                }
                
                // AUMENTANDO AS POSIÇÕES
                if(serieIniciada && ordCont.isIniciada()){
                    if(ordCont.getLadoOrdem() == LadoOrdem.COMPRA){
                        if(Candle.getListaCandleMinuto().get(i).getMinima() <= ultimaEntrada - loss){
                            ordCont = new OrdemSimples();
                            ordCont.setTemOffset(true);
                            ordCont.setTemContMov(true);
                            ordCont.setLadoOrdem(LadoOrdem.COMPRA);
                            ordCont.setOffset(ultimaEntrada - loss - rDia.getAbertura());
                            ordCont.configuraLinhasEntradaESaidas(rDia.getAbertura());
                            
                            if(caso > 3)
                                ord.setQtde(qtdeDiaNeg);
                            caso++;
                            
                            controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ordCont);
                            ultimaEntrada = (ordCont.getLinhaReferencia() + ordCont.getOffset());    
                        }
                    } else{
                        if(Candle.getListaCandleMinuto().get(i).getMaxima() >= ultimaEntrada + loss){
                            ordCont = new OrdemSimples();
                            ordCont.setTemOffset(true);
                            ordCont.setTemContMov(true);
                            ordCont.setLadoOrdem(LadoOrdem.VENDA);
                            ordCont.setOffset(ultimaEntrada + loss - rDia.getAbertura());
                            ordCont.configuraLinhasEntradaESaidas(rDia.getAbertura());
                            
                            if(caso > 3)
                                ord.setQtde(qtdeDiaNeg);
                            caso++;
                            
                            controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ordCont);
                            ultimaEntrada = (ordCont.getLinhaReferencia() + ordCont.getOffset());    
                        }
                    }
                }
            
                //AGUARDA FORMAÇÃO DO CANDLE
                rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
                if(Candle.getListaCandleMinuto().get(i).criaCandleIntermediario(ConfigOrdens.getAguardaFormacaoCandle())){
                    if(podeAtualizarExtremoAnterior){
                        maxCandleAnterior = Candle.getMaxCandleYMinAnterior();
                        minCandleAnterior = Candle.getMinCandleYMinAnterior();
                        podeIniciarSerie = true;
                    }
                }
                
                // SE ATINGIU A META, COMEÇA NOVA SÉRIE
                if(rDia.isGerRisco()){
                    rDia.iniciaSerie();
                    podeAtualizarExtremoAnterior = true;
                    podeIniciarSerie = false;
                    serieIniciada = false;
                    caso = 0;
                }
            }
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
            
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                if(!gravouDia){
                    gravouDia = true;
                    diaOperando = false;

                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                }
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size()){
                        diaAtual++;
                        
                        caso = 0;
                        qtdeDiaNeg = ConfigOrdens.getQtde();
                        ConfigOrdens.setTemGerRisco_CompensaSaldo(false, false);
                        
                        if(rDia.getSaldo() < 0 && Math.abs(ResumoDia.getUltimaPos()) > 1){
                            caso = 4;
                            qtdeDiaNeg = Math.abs(ResumoDia.getUltimaPos());
                            ConfigOrdens.setTemGerRisco_CompensaSaldo(gerRisco_compensaSaldoCumulativoBackUp, gerRisco_compensaSaldoSimplesBackUp);
                        }
                        
                        
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                        
                        gravouDia = false;
                        
                        podeAtualizarExtremoAnterior = true;
                        podeIniciarSerie = false;
                        serieIniciada = false;
                        
                            
                        
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                    }   
                }
            }
        }// Fim da simulação
    }
}

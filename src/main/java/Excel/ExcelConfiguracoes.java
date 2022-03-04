/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Configuracoes.ConfigBase;
import Configuracoes.ConfigIndicadores;
import Configuracoes.ConfigOrdens;
import InterfaceGrafica.IG;
import Ordens.Ordem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelConfiguracoes implements AcoesExcel{

    private static String nomePlanilha = "Configurações";
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(workbookGravacao.getSheet(nomePlanilha) != null)
            workbookGravacao.removeSheetAt(workbookGravacao.getSheetIndex(nomePlanilha));
        
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);

        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        
        int linha = 0;
        Row row = sheet.createRow(linha++);
        Cell cell = row.createCell(0);
        cell.setCellValue("SIMULAÇÃO");
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(IG.printVersao());
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(IG.getNomeSimulacao());
        
        linha++;
        
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue("HORÁRIOS");
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigBase.printHorarioInicial());
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigBase.printHorarioLimiteEntrada());
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigBase.printHorarioFinal());

        linha++;
        
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue("GERENCIAMENTO DE RISCO");
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigOrdens.printGerRisco(1));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigOrdens.printGerRisco(2));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigOrdens.printGerRisco(3));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigOrdens.printGerRisco(4));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigOrdens.printGerRisco(5));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigOrdens.printGerRisco(6));
        
        linha++;
        
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue("INDICADORES DIÁRIOS");
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigIndicadores.printIndicadorDiario(1));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigIndicadores.printIndicadorDiario(2));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigIndicadores.printIndicadorDiario(3));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigIndicadores.printIndicadorDiario(4));
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue(ConfigIndicadores.printIndicadorDiario(5));
        
        linha++;
        
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue("INDICADORES MINUTO");
        row = sheet.createRow(linha++);
        cell = row.createCell(0);
        cell.setCellValue("Ainda não implementado!");
                
        
        linha++;  
        //LISTA DE ORDENS OCO FIXAS
        if(ConfigOrdens.getListaOrdensFixas() != null && !ConfigOrdens.getListaOrdensFixas().isEmpty()){
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue("LISTA DE ORDENS FIXAS:");
            for (Ordem ord : ConfigOrdens.getListaOrdensFixas()) {
                row = sheet.createRow(linha++);
                cell = row.createCell(0);
                cell.setCellValue(ord.printConfigOrdem());
            }
        }else{
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue("VARIÁVEIS DA SIMULAÇÃO");
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigAguardaCandle());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigMov());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigContMov());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigPos());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigOffset());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigLimOp());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigDelta());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigGain());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigLoss());
            row = sheet.createRow(linha++);
            cell = row.createCell(0);
            cell.setCellValue(ConfigOrdens.getConfigTrStop());
        }
    
        //OBSERVAÇÕES
        linha = 0;
        row = sheet.getRow(linha++);
        int col = 6;
        cell = row.createCell(col);
        cell.setCellValue("OBSERVAÇÕES:");
        String[] observacao = ConfigOrdens.getObservacoes().split(System.lineSeparator());
        for (String item : observacao) {
            row = sheet.getRow(linha);
            if(row == null)
                row = sheet.createRow(linha);
            cell = row.createCell(col);
            cell.setCellValue(item);
            linha++;
        }
    }
    
    
    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

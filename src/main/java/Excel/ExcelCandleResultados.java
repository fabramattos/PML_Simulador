/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import InterfaceGrafica.IG;
import java.sql.Timestamp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import simulacao.Candle;
import Resumos.Relatorios;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelCandleResultados implements AcoesExcel{
    
    private static String nomePlanilha = "Rel. Candles";
    private static CellStyle styleNumerico, styleDataCompleta;
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(!IG.isTemRelCandles())
            return;
        
        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);
        configuraFormatacao(workbookGravacao);
        
        // TITULO RELATORIO CANDLES
        int linha = 0;
        int coluna = 0;
        Row row = sheet.createRow(linha++);
        Cell cell = row.createCell(coluna++);
        cell.setCellValue("Data");
        cell = row.createCell(coluna++);
        cell.setCellValue("Abertura");
        cell = row.createCell(coluna++);
        cell.setCellValue("Maxima");
        cell = row.createCell(coluna++);
        cell.setCellValue("Minima");
        cell = row.createCell(coluna++);
        cell.setCellValue("Fechamento");
        cell = row.createCell(coluna++);
        cell.setCellValue("Média");
        cell = row.createCell(coluna++);
        cell.setCellValue("Indicador");
        cell = row.createCell(coluna++);
        cell.setCellValue("");
        cell = row.createCell(coluna++);
        cell.setCellValue("Pos");
        cell = row.createCell(coluna++);
        cell.setCellValue("Valor Pos");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saldo Aberto");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saldo Realizado");
       
        for(Candle candle : Relatorios.getRelatorioCandleMinutoOperado()){
            row = sheet.createRow(linha++);
            coluna = 0;
            cell = row.createCell(coluna++);
            cell.setCellValue(Timestamp.valueOf(candle.getData())); cell.setCellStyle(styleDataCompleta);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getAbertura()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getMaxima()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getMinima()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getFechamento()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getMedia()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getIndicadorExtra()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue("");
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getPos());
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getPosValMed()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getSaldoAbertoFechamento()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(candle.getSaldoRealizado()); cell.setCellStyle(styleNumerico);
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleNumerico = workbookGravacao.createCellStyle();
        styleNumerico.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));

        styleDataCompleta = workbookGravacao.createCellStyle();
        styleDataCompleta.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy HH:MM"));
    }
    
}

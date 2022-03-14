/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.Excel;

import com.pml.InterfaceGrafica.IG;
import java.sql.Timestamp;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.pml.Resumos.ResumoPeriodoRelatorio;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelCompletoSeparadoPorPeriodos implements AcoesExcel{

    private static String nomePlanilha = "Completo Periodos";
    private static CellStyle styleAN, styleANPorc, styleAP, styleAPPorc, styleANData, styleAPData;
    
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(ResumoPeriodoRelatorio.relatorioEstrategiasPer1 == null
        || ResumoPeriodoRelatorio.relatorioEstrategiasPer1.isEmpty())
            return;
        
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);
        
        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        // Titulos dos dados
        int linha = 0;
        int coluna = 0;

        //LINHA INFORMANDO 'CONFIG', 'ANALISE' E 'APLICADO'
        Row row = sheet.createRow(linha++);
        //LINHA INFORMANDO OS TÍTULOS DOS VALORES
        IG.progressoCompletoAtualiza(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.size(), 0);
        coluna = 0;
        Cell cell = row.createCell(coluna++);
        cell.setCellValue("D");
        cell = row.createCell(coluna++);
        cell.setCellValue("Lim");
        cell = row.createCell(coluna++);
        cell.setCellValue("E");
        cell = row.createCell(coluna++);
        cell.setCellValue("G");
        cell = row.createCell(coluna++);
        cell.setCellValue("L");
        cell = row.createCell(coluna++);
        cell.setCellValue("G.R. Saldo");
        cell = row.createCell(coluna++);
        cell.setCellValue("G.R. Pts/Cont");
        cell = row.createCell(coluna++);
        cell.setCellValue("G.R PrejPerm");
        int maxPer = 0;
        for(List<ResumoPeriodoRelatorio> est: ResumoPeriodoRelatorio.relatorioEstrategiasPer1){
            maxPer = Integer.max(maxPer, est.size()-1);
        }
        
        configuraFormatacao(workbookGravacao);
        for(int i = 0; i <= maxPer; i++){
            cell = row.createCell(coluna++);
            cell.setCellValue("AN Data"); cell.setCellStyle(styleAN);
            cell = row.createCell(coluna++);
            cell.setCellValue("Dias"); cell.setCellStyle(styleAN);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN prejAcumMax"); cell.setCellStyle(styleAN);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN SaldoMax"); cell.setCellStyle(styleAN);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN SaldoAcum"); cell.setCellStyle(styleAN);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN Saldo/Contrato"); cell.setCellStyle(styleAN);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN Saldo/PrejMax"); cell.setCellStyle(styleANPorc);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN Saldo/DrawnDownMax"); cell.setCellStyle(styleANPorc);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN DataFinal"); cell.setCellStyle(styleAN);
            if(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.isEmpty())
                continue;
            cell = row.createCell(coluna++);
            cell.setCellValue("AP Data"); cell.setCellStyle(styleAP);
            cell = row.createCell(coluna++);
            cell.setCellValue("Dias"); cell.setCellStyle(styleAP);
            cell = row.createCell(coluna++);
            cell.setCellValue("AP prejAcumMax"); cell.setCellStyle(styleAP);
            cell = row.createCell(coluna++);
            cell.setCellValue("AP SaldoMax"); cell.setCellStyle(styleAP);
            cell = row.createCell(coluna++);
            cell.setCellValue("AP SaldoAcum"); cell.setCellStyle(styleAP);
            cell = row.createCell(coluna++);
            cell.setCellValue("AP Saldo/contrato"); cell.setCellStyle(styleAP);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN Saldo/PrejMax"); cell.setCellStyle(styleAPPorc);
            cell = row.createCell(coluna++);
            cell.setCellValue("AN Saldo/DrawnDownMax"); cell.setCellStyle(styleAPPorc);
            cell = row.createCell(coluna++);
            cell.setCellValue("AP DataFinal"); cell.setCellStyle(styleAP);
        }
        
        //PREENCHE VALORES
        for(int i = 0; i < ResumoPeriodoRelatorio.relatorioEstrategiasPer1.size(); i++){
            coluna = 0;
            row = sheet.createRow(linha++);
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getDelta());
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getLimOposto());
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getOffset());
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getGain());
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getLoss());
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getGerRisco_Saldo());
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getGerRisco_PtsCont());
            cell = row.createCell(coluna++);
            cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(0).getGerRisco_PrejPerm());
            
            for(int j = 0; j < ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).size(); j++){
                cell = row.createCell(coluna++);
                cell.setCellValue(Timestamp.valueOf(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getData())); cell.setCellStyle(styleANData);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getDiasTotais()); cell.setCellStyle(styleAN);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getPrejAcumMax()); cell.setCellStyle(styleAN);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getSaldoMax());  cell.setCellStyle(styleAN);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getSaldoAcumulado()); cell.setCellStyle(styleAN);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getSaldoContrato()); cell.setCellStyle(styleAN);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getSaldoPrejMax()); cell.setCellStyle(styleANPorc);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getSaldoDrawDown()); cell.setCellStyle(styleANPorc);
                cell = row.createCell(coluna++);
                cell.setCellValue(Timestamp.valueOf(ResumoPeriodoRelatorio.relatorioEstrategiasPer1.get(i).get(j).getDataFin())); cell.setCellStyle(styleANData);
                if(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.isEmpty())
                    continue;
                
                cell = row.createCell(coluna++);
                cell.setCellValue(Timestamp.valueOf(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getData())); cell.setCellStyle(styleAPData);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getDiasTotais()); cell.setCellStyle(styleAP);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getPrejAcumMax()); cell.setCellStyle(styleAP);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getSaldoMax()); cell.setCellStyle(styleAP);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getSaldoAcumulado()); cell.setCellStyle(styleAP);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getSaldoContrato()); cell.setCellStyle(styleAP);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getSaldoPrejMax()); cell.setCellStyle(styleAPPorc);
                cell = row.createCell(coluna++);
                cell.setCellValue(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getSaldoDrawDown()); cell.setCellStyle(styleAPPorc);
                cell = row.createCell(coluna++);
                cell.setCellValue(Timestamp.valueOf(ResumoPeriodoRelatorio.relatorioEstrategiasPer2.get(i).get(j).getDataFin())); cell.setCellStyle(styleAPData);
            }   
            IG.progressoCompletoAtualiza(i);
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleANPorc = workbookGravacao.createCellStyle();
        styleANPorc.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        styleANPorc.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleANPorc.setDataFormat(createHelper.createDataFormat().getFormat("0.00%"));
        
        //relatorios por periodo de analise, cores distintas
        styleAN = workbookGravacao.createCellStyle();
        styleAN.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        styleAN.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        styleAP = workbookGravacao.createCellStyle();
        styleAP.setFillForegroundColor(IndexedColors.TAN.getIndex());
        styleAP.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        styleANData = workbookGravacao.createCellStyle();
        styleANData.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        styleANData.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleANData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        styleAPData = workbookGravacao.createCellStyle();  
        styleAPData.setFillForegroundColor(IndexedColors.TAN.getIndex());
        styleAPData.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleAPData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
        
        styleANPorc = workbookGravacao.createCellStyle();
        styleANPorc.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        styleANPorc.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleANPorc.setDataFormat(createHelper.createDataFormat().getFormat("0.00%"));
        
        styleAPPorc = workbookGravacao.createCellStyle();
        styleAPPorc.setFillForegroundColor(IndexedColors.TAN.getIndex());
        styleAPPorc.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleAPPorc.setDataFormat(createHelper.createDataFormat().getFormat("0.00%"));
    }
    
}

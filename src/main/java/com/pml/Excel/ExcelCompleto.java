/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.Excel;

import com.pml.InterfaceGrafica.IG;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.pml.Resumos.Relatorios;
import com.pml.Resumos.Resumos;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelCompleto implements AcoesExcel{

    private static String nomePlanilha = "Completo";
    private static CellStyle styleNumerico, stylePorc;
    
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(Relatorios.getRelatorioCompleto().isEmpty())
            return;
        
        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        
        SXSSFSheet sheet;
        Row row;
        Cell cell;
        int linha;
        int coluna;
        if(null == workbookGravacao.getSheet(nomePlanilha)){
            sheet = workbookGravacao.createSheet(nomePlanilha);
            // TITULOS
            linha = 0;
            coluna = 0;
            row = sheet.createRow(linha++);
            cell = row.createCell(coluna++);
            cell.setCellValue("Q Max");
            cell = row.createCell(coluna++);
            cell.setCellValue("Del");
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
            cell = row.createCell(coluna++);
            cell.setCellValue("TrStop E");
            cell = row.createCell(coluna++);
            cell.setCellValue("TrStop G");
            cell = row.createCell(coluna++);
            cell.setCellValue("Pos Max");
            cell = row.createCell(coluna++);
            cell.setCellValue("Entradas");
            cell = row.createCell(coluna++);
            cell.setCellValue("Saidas");
            cell = row.createCell(coluna++);
            cell.setCellValue("TrStops");
            cell = row.createCell(coluna++);
            cell.setCellValue("Stops");
            cell = row.createCell(coluna++);
            cell.setCellValue("Sim");
            cell = row.createCell(coluna++);
            cell.setCellValue("Dias +");
            cell = row.createCell(coluna++);
            cell.setCellValue("Dias -");
            cell = row.createCell(coluna++);
            cell.setCellValue("prejAcumMax");
            cell = row.createCell(coluna++);
            cell.setCellValue("SaldoMin(Dia)");
            cell = row.createCell(coluna++);
            cell.setCellValue("SaldoMax");
            cell = row.createCell(coluna++);
            cell.setCellValue("SaldoAcum");
            cell = row.createCell(coluna++);
            cell.setCellValue("SaldoMax/Contrato");
            cell = row.createCell(coluna++);
            cell.setCellValue("SaldoMax/PrejMax");
            cell = row.createCell(coluna++);
            cell.setCellValue("SaldoMax/DrawDownMax");
        }
        
        sheet = workbookGravacao.getSheet(nomePlanilha);
        configuraFormatacao(workbookGravacao);
        for (Resumos resumo : Relatorios.getRelatorioCompleto()) {
            row = sheet.createRow(sheet.getLastRowNum() + 1);
            coluna = 0;
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getPosMaxPerm());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getDelta());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getLimOposto());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getOffset());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getGain());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getLoss());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getGerRisco_Saldo());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getGerRisco_PtsCont());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getGerRisco_PrejPerm());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getTrStopAcionamento());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getTrStopPtsMin());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getPosMax());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getEntradas());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSaidas());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getTrStops());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getStops());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSimultaneo());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getDiasPos());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getDiasNeg());
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getPrejAcumMax()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSaldoMin()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSaldoMax()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSaldo()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSaldoContrato()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSaldoPrejMax()); cell.setCellStyle(stylePorc);
            cell = row.createCell(coluna++);
            cell.setCellValue(resumo.getSaldoDrawDown()); cell.setCellStyle(stylePorc);
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleNumerico = workbookGravacao.createCellStyle();
        styleNumerico.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));

        stylePorc = workbookGravacao.createCellStyle();
        stylePorc.setDataFormat(createHelper.createDataFormat().getFormat("0.00%"));
    }
    
}

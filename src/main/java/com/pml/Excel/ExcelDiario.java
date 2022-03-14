/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.Excel;

import com.pml.InterfaceGrafica.IG;
import java.sql.Timestamp;
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
public class ExcelDiario implements AcoesExcel{

    private static String nomePlanilha = "Relatório";
    private static CellStyle styleNumerico, styleData;
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(!IG.isTemRelDiario())
            return;
        
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);

        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        // TITULOS CONFIG
        int linha = 0;
        int coluna = 0;

        // TITULOS DADOS
        Row row = sheet.createRow(linha++);
        coluna = 0;
        Cell cell = row.createCell(coluna++);
        cell.setCellValue("Data");
        cell = row.createCell(coluna++);
        cell.setCellValue("Abe");
        cell = row.createCell(coluna++);
        cell.setCellValue("Max");
        cell = row.createCell(coluna++);
        cell.setCellValue("Min");
        cell = row.createCell(coluna++);
        cell.setCellValue("Fech");
        cell = row.createCell(coluna++);
        cell.setCellValue("ind. Extra");
        cell = row.createCell(coluna++);
        cell.setCellValue("media");
        cell = row.createCell(coluna++);
        cell.setCellValue("PosMax");
        cell = row.createCell(coluna++);
        cell.setCellValue("PosFin");
        cell = row.createCell(coluna++);
        cell.setCellValue("PosValMed");
        cell = row.createCell(coluna++);
        cell.setCellValue("QtdeMax");
        cell = row.createCell(coluna++);
        cell.setCellValue("Entradas");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saídas");
        cell = row.createCell(coluna++);
        cell.setCellValue("Tr. Stops");
        cell = row.createCell(coluna++);
        cell.setCellValue("Stops");
        cell = row.createCell(coluna++);
        cell.setCellValue("Simultaneo");
        cell = row.createCell(coluna++);
        cell.setCellValue("DrawDown");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saldo/Contrato");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saldo Min");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saldo");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saldo Acum.");
        cell = row.createCell(coluna++);
        cell.setCellValue("Prej. Acum.");
        
        configuraFormatacao(workbookGravacao);
        int i = 0;
        IG.progressoCompletoAtualiza(Relatorios.getRelatorioDiario().size(), 0);
        for (Resumos dia : Relatorios.getRelatorioDiario()) {
            coluna = 0;
            row = sheet.createRow(linha++);
            cell = row.createCell(coluna++);
            cell.setCellValue(Timestamp.valueOf(dia.getData())); cell.setCellStyle(styleData);
            cell = row.createCell(coluna++); 
            cell.setCellValue(dia.getAbertura()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getMaxima()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getMinima()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getFechamento()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getIndicadorExtra()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getMedia()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getPosMax());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getPos());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getPosValMed());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getQtdeMax());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getEntradas());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaidas());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getTrStops());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getStops());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSimultaneo());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldoAbertoNeg()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldoContrato()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldoMin()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldo()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldoAcumulado()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getPrejAcum()); cell.setCellStyle(styleNumerico);
            IG.progressoCompletoAtualiza(i++);
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleNumerico = workbookGravacao.createCellStyle();
        styleNumerico.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));

        styleData = workbookGravacao.createCellStyle();
        styleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    }
    
}

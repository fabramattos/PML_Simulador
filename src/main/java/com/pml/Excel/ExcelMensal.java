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
public class ExcelMensal implements AcoesExcel{

    private static String nomePlanilha = "Rel. Mensal";
    private static CellStyle styleNumerico;
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(Relatorios.getRelatorioMensal().isEmpty())
            return;
        
        configuraFormatacao(workbookGravacao);
        
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);
        
        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        // TITULO RELATORIO MENSAL
        int linha = 0;
        int coluna = 0;
        Row row = sheet.createRow(linha++);
        Cell cell = row.createCell(coluna++);
        cell.setCellValue("Data");
        cell = row.createCell(coluna++);
        cell.setCellValue("PosMax");
        cell = row.createCell(coluna++);
        cell.setCellValue("Entradas");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saidas");
        cell = row.createCell(coluna++);
        cell.setCellValue("Tr. Stops");
        cell = row.createCell(coluna++);
        cell.setCellValue("Stops");
        cell = row.createCell(coluna++);
        cell.setCellValue("dias Pos");
        cell = row.createCell(coluna++);
        cell.setCellValue("dias Neg");
        cell = row.createCell(coluna++);
        cell.setCellValue("Simultaneo");
        cell = row.createCell(coluna++);
        cell.setCellValue("prejAcumMax");
        cell = row.createCell(coluna++);
        cell.setCellValue("saldoMax");
        cell = row.createCell(coluna++);
        cell.setCellValue("saldoAcum");

        IG.progressoCompletoAtualiza(Relatorios.getRelatorioMensal().size(), 0);
        int i = 0;
        for(Resumos res : Relatorios.getRelatorioMensal()){
            row = sheet.createRow(linha++);
            coluna = 0;
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getData().getMonthValue() + "/" + res.getData().getYear());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getQtdeMax());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getEntradas());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getSaidas());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getTrStops());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getStops());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getDiasPos());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getDiasNeg());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getSimultaneo());
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getPrejAcumMax()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getSaldoMax()); cell.setCellStyle(styleNumerico);
            cell = row.createCell(coluna++);
            cell.setCellValue(res.getSaldo()); cell.setCellStyle(styleNumerico);
            IG.progressoCompletoAtualiza(i++);
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleNumerico = workbookGravacao.createCellStyle();
        styleNumerico.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));
    }
}

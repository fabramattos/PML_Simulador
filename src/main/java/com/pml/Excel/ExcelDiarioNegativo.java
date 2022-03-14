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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.pml.Resumos.Relatorios;
import com.pml.Resumos.Resumos;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelDiarioNegativo implements AcoesExcel{

    private static String nomePlanilha = "Dias Neg.";
    private static CellStyle styleANData;
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(Relatorios.getRelatorioDiario().isEmpty())
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
        cell.setCellValue("D");
        cell = row.createCell(coluna++);
        cell.setCellValue("E");
        cell = row.createCell(coluna++);
        cell.setCellValue("Lim");
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
        cell.setCellValue("Tr.Stop.E");
        cell = row.createCell(coluna++);
        cell.setCellValue("Tr.Stop.G");
        cell = row.createCell(coluna++);
        cell.setCellValue("PosMax");
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
        cell.setCellValue("Saldo");
        cell = row.createCell(coluna++);
        cell.setCellValue("Saldo Acum.");
        cell = row.createCell(coluna++);
        cell.setCellValue("Prej. Acum.");

        configuraFormatacao(workbookGravacao);
        
        int i = 0;
        IG.progressoCompletoAtualiza(Relatorios.getRelatorioDiario().size(), 0);
        for (Resumos dia : Relatorios.getRelatorioDiario()){
            if(Relatorios.getRelatorioDiario().get(i).getSaldo()>=0)
                continue;
            coluna = 0;
            row = sheet.createRow(linha++);
            cell = row.createCell(coluna++);
            cell.setCellValue(Timestamp.valueOf(dia.getData())); cell.setCellStyle(styleANData);
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getAbertura());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getMaxima());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getMinima());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getFechamento());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getIndicadorExtra());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getMedia());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getDelta());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getOffset());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getLimOposto());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getGain());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getLoss());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getGerRisco_Saldo());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getGerRisco_PtsCont());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getGerRisco_PrejPerm());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getTrStopAcionamento());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getTrStopPtsMin());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getPosMax());
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
            cell.setCellValue(dia.getSaldoAbertoNeg());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldoContrato());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldo());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getSaldoAcumulado());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getPrejAcum());
            IG.progressoCompletoAtualiza(i++);
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleANData = workbookGravacao.createCellStyle();
        styleANData.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        styleANData.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleANData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    }    
}

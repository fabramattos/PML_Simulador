/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.Excel;

import com.pml.InterfaceGrafica.IG;
import com.pml.Ordens.Ordem;
import java.sql.Timestamp;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.pml.Resumos.Relatorios;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelOrdensSugeridas implements AcoesExcel{

    private static String nomePlanilha = "Sugeridas";
    private static CellStyle styleData;
    
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(Relatorios.getRelatorioOrdensSugeridas().isEmpty())
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

        configuraFormatacao(workbookGravacao);
        int i = 0;
        IG.progressoCompletoAtualiza(Relatorios.getRelatorioOrdensSugeridas().size(), 0);
        for (List<Ordem> lista : Relatorios.getRelatorioOrdensSugeridas()) {
            for(Ordem est : lista){
                coluna = 0;
                row = sheet.createRow(linha++);
                cell = row.createCell(coluna++);
                cell.setCellValue(Timestamp.valueOf(est.getData())); cell.setCellStyle(styleData);
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getDelta());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getOffset());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getLimOp());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getGain());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getLoss());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getSaldoDesej());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getPtsContDesej());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getPrejPerm());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getTrStopPtsAcionamento());
                cell = row.createCell(coluna++);
                cell.setCellValue(est.getTrStopGainMin());
                IG.progressoCompletoAtualiza(i++);
            }
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleData = workbookGravacao.createCellStyle();
        styleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    }
    
}

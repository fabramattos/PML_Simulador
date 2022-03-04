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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import Resumos.Relatorios;
import Resumos.Resumos;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelPosStop implements AcoesExcel{

    private static String nomePlanilha = "Rel. Stops";
    private static CellStyle styleANData;
    
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(Relatorios.getRelatorioDiasPosStop().isEmpty())
            return;
        
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);
        
        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        // TITULOS CONFIG
        int linha = 0;
        int coluna = 0;

        // TITULOS DADOS
        Row row = sheet.createRow(linha++);
        Cell cell = row.createCell(coluna++);
        cell.setCellValue("Data");
        cell = row.createCell(coluna++);
        cell.setCellValue("Linha Stop");
        cell = row.createCell(coluna++);
        cell.setCellValue("Max");
        cell = row.createCell(coluna++);
        cell.setCellValue("Min");
        cell = row.createCell(coluna++);
        cell.setCellValue("Fech");
        cell = row.createCell(coluna++);
        cell.setCellValue("G");
        cell = row.createCell(coluna++);
        cell.setCellValue("Tr.Stop");
        cell = row.createCell(coluna++);
        cell.setCellValue("L");

        configuraFormatacao(workbookGravacao);
        IG.progressoCompletoAtualiza(Relatorios.getRelatorioDiasPosStop().size(), 0);
        int i = 0;
        for (Resumos dia : Relatorios.getRelatorioDiasPosStop()) {
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
            cell.setCellValue(dia.getGain());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getTrStops());
            cell = row.createCell(coluna++);
            cell.setCellValue(dia.getLoss());
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.Excel;

import com.pml.ArquivosTemporarios.ArquivoTemp;
import com.pml.InterfaceGrafica.IG;
import java.sql.Timestamp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.pml.Resumos.Resumos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelCompletoDiario implements AcoesExcel{

    private static String nomePlanilha = "Completo Diario";
    private static CellStyle styleData, styleNumerico;
    private static boolean criouCabecalho;
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        ExcelCompletoDiario.criouCabecalho = false;
        File file = (ArquivoTemp.getArquivoTemp()); 
        if(file == null)
            return;
        
        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);
        configuraFormatacao(workbookGravacao);
        
        try(InputStream is = new FileInputStream(file)){
            while(is.available() != 0){
                ObjectInputStream ois = new ObjectInputStream(is);
                List<Resumos> listaDiario = (List<Resumos>) ois.readObject();
                geraExcel(sheet, listaDiario);
            }
        }catch (IOException | ClassNotFoundException ex) {}
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleNumerico = workbookGravacao.createCellStyle();
        styleNumerico.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));

        styleData = workbookGravacao.createCellStyle();
        styleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    }


    private void geraExcel(SXSSFSheet sheet, List<Resumos> listaDiario) {
        preparaCabecalho(sheet, listaDiario);
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell cell;
        int col = 0;
        for(Resumos rDia : listaDiario){
            if(col == 0){
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getPosMaxPerm());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getDelta());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getLimOposto());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getOffset());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getGain());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getLoss());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getGerRisco_Saldo());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getGerRisco_PtsCont());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getGerRisco_PrejPerm());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getTrStopAcionamento());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getTrStopPtsMin());
                cell = row.createCell(col++);
                cell.setCellValue(rDia.getTrStopFreqAtualizacao());
                cell = row.createCell(col++);
                cell.setCellValue("");
            }
            cell = row.createCell(col++);
            cell.setCellValue(rDia.getSaldo()); cell.setCellStyle(styleNumerico);
        }
    }

    private void preparaCabecalho(SXSSFSheet sheet, List<Resumos> listaDiario) {
        if(criouCabecalho)
            return;

        //PosMaxPerm, delta, lim, off, g, l, g2, l2 -> COMEÇAR COLUNA 9
        int linha = 0;
        int colCabecalho = 12;
        int col = colCabecalho;
        
        Row row = sheet.createRow(linha++);
        Cell cell = row.createCell(col++);
        
        cell.setCellValue("Data:"); 
        for(Resumos rDia : listaDiario){
            cell = row.createCell(col++);
            cell.setCellValue(Timestamp.valueOf(rDia.getData())); cell.setCellStyle(styleData);
        }            

        row = sheet.createRow(linha++);
        col = colCabecalho;
        cell = row.createCell(col++);
        cell.setCellValue("Aber:"); 
        for(Resumos rDia : listaDiario){
            cell = row.createCell(col++);
            cell.setCellValue(rDia.getAbertura());
        }           

        row = sheet.createRow(linha++);
        col = colCabecalho;
        cell = row.createCell(col++);
        cell.setCellValue("Máx:"); 
        for(Resumos rDia : listaDiario){
            cell = row.createCell(col++);
            cell.setCellValue(rDia.getMaxima());
        }

        row = sheet.createRow(linha++);
        col = colCabecalho;
        cell = row.createCell(col++);
        cell.setCellValue("Mín:"); 
        for(Resumos rDia : listaDiario){
            cell = row.createCell(col++);
            cell.setCellValue(rDia.getMinima());
        }

        row = sheet.createRow(linha++);
        col = colCabecalho;
        cell = row.createCell(col++);
        cell.setCellValue("Fech:"); 
        for(Resumos rDia : listaDiario){
            cell = row.createCell(col++);
            cell.setCellValue(rDia.getFechamento());
        }

        row = sheet.createRow(linha++);
        col = 0;
        cell = row.createCell(col++);
        cell.setCellValue("PosMaxPerm");
        cell = row.createCell(col++);
        cell.setCellValue("D");
        cell = row.createCell(col++);
        cell.setCellValue("Lim");
        cell = row.createCell(col++);
        cell.setCellValue("E");
        cell = row.createCell(col++);
        cell.setCellValue("G");
        cell = row.createCell(col++);
        cell.setCellValue("L");
        cell = row.createCell(col++);
        cell.setCellValue("G.R. Saldo");
        cell = row.createCell(col++);
        cell.setCellValue("G.R. Pts/Cont");
        cell = row.createCell(col++);
        cell.setCellValue("G.R PrejPerm");
        cell = row.createCell(col++);
        cell.setCellValue("TrStop Acion:");
        cell = row.createCell(col++);
        cell.setCellValue("TrStop PtsMin:");
        cell = row.createCell(col++);
        cell.setCellValue("TrStop Freq:");
        cell = row.createCell(col++);
        cell.setCellValue("Indicador:");

        for(Resumos rDia : listaDiario){
            cell = row.createCell(col++);
            cell.setCellValue(rDia.getIndicadorExtra());
        }
        criouCabecalho = true;
    }
    
}

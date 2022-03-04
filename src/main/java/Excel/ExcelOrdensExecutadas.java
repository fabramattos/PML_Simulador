/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import InterfaceGrafica.IG;
import Ordens.Ordem;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import Resumos.Relatorios;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public class ExcelOrdensExecutadas implements AcoesExcel{

    private static String nomePlanilha = "Ordens";
    private static CellStyle styleData, styleHora;
    
    
    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        if(Relatorios.getRelatorioOrdensExecutadas().isEmpty())
            return;
        
        List<Ordem> lista = new ArrayList(Relatorios.getRelatorioOrdensExecutadas());
        Collections.sort(lista); 
        SXSSFSheet sheet = workbookGravacao.createSheet(nomePlanilha);
        
        IG.textoAdd("Criando planilha: " + nomePlanilha + "\n");
        // TITULO RELATORIO TRANSAÇÕES
        int linha = 0;
        int coluna = 0;
        Row row = sheet.createRow(linha++);
        Cell cell = row.createCell(coluna++);
        cell.setCellValue("Data");
        cell = row.createCell(coluna++);
        cell.setCellValue("Hora Executada");
        cell = row.createCell(coluna++);
        cell.setCellValue("qtde");
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
        cell.setCellValue("Tr. Stop Acion");
        cell = row.createCell(coluna++);
        cell.setCellValue("Tr. Stop Pts Min");
        cell = row.createCell(coluna++);
         cell.setCellValue("Tr. Stop Freq");
        cell = row.createCell(coluna++);
        cell.setCellValue("linha C");
        cell = row.createCell(coluna++);
        cell.setCellValue("linha V");
        cell = row.createCell(coluna++);
        cell.setCellValue("linha TStop");
        cell = row.createCell(coluna++);
        cell.setCellValue("lin Stop");
        cell = row.createCell(coluna++);
        cell.setCellValue("alvo");
        cell = row.createCell(coluna++);
        cell.setCellValue("Tr.Stop");
        cell = row.createCell(coluna++);
        cell.setCellValue("stop");
        cell = row.createCell(coluna++);
        cell.setCellValue("Simul");
        cell = row.createCell(coluna++);
        cell.setCellValue("Nome Estrat.");

        configuraFormatacao(workbookGravacao);
        int i = 0;
        IG.progressoCompletoAtualiza(lista.size(), 0);
        for (Ordem ord : lista) {
            row = sheet.createRow(linha++);
            coluna = 0;
            cell = row.createCell(coluna++);
            cell.setCellValue(Timestamp.valueOf(ord.getData())); cell.setCellStyle(styleData);
            cell = row.createCell(coluna++);
            if(ord.getDataEntrada() == null)
                cell.setCellValue("");
            else
                cell.setCellValue(Timestamp.valueOf(ord.getDataEntrada())); cell.setCellStyle(styleHora);
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getQtde());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getDelta());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getOffset());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getLimOp());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getGain());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getLoss());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getSaldoDesej()); 
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getPtsContDesej());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getPrejPerm());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getTrStopPtsAcionamento());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getTrStopGainMin());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getTrStopFrequenciaAtualizacao());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getLinhaCompra());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getLinhaVenda());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getLinhaTrStop());
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getLinhaStop());
            cell = row.createCell(coluna++);
            if (ord.isAlvoExecutado())
                cell.setCellValue(1);
            else
                cell.setCellValue(0);
            cell = row.createCell(coluna++);
            if (ord.isTrStopExecutado())
                cell.setCellValue(1);
            else
                cell.setCellValue(0);
            cell = row.createCell(coluna++);
            if (ord.isStopExecutado())
                cell.setCellValue(1);
            else
                cell.setCellValue(0);
            cell = row.createCell(coluna++);
            if(ord.isSimultaneo())
                cell.setCellValue(1);
            else
                cell.setCellValue(0);
            cell = row.createCell(coluna++);
            cell.setCellValue(ord.getNome());
            IG.progressoCompletoAtualiza(i++);
        }
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        CreationHelper createHelper = workbookGravacao.getCreationHelper();
        styleData = workbookGravacao.createCellStyle();
        styleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        styleHora = workbookGravacao.createCellStyle();
        styleHora.setDataFormat(createHelper.createDataFormat().getFormat("HH:MM"));
    }
    
}

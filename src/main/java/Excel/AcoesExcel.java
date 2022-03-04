/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Felipe Mattos
 */
public interface AcoesExcel {
    
    void executa(SXSSFWorkbook workbookGravacao);
    
    void configuraFormatacao(SXSSFWorkbook workbookGravacao);
}

/**
 * Manipula todos os dados em planilhas. Leitura e Gravação
 * */
package Excel;

import java.util.List;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author Felipe Mattos
 *
 */
public class ExcelHandler {
    
    private SXSSFWorkbook workbookGravacao;
    /**
     * Ao instanciar a classe Excel, prepara todos relatorios para serem gravados
     * @param listaRelatorios 
     */
    public ExcelHandler(List<AcoesExcel> listaRelatorios) {
        workbookGravacao = new SXSSFWorkbook();
        listaRelatorios.forEach(v -> v.executa(workbookGravacao));
    }
    
    
    /**
     * Para gravar o arquivo quando chamado o método.
     * Caso queira, pode instanciar o 'ExcelGravaArquivo' e passar por ultimo 
     * diretamente no construtor do Handler
     */
    public void gravaArquivo(){
        new ExcelGravaArquivo().executa(workbookGravacao);
    }
    
}



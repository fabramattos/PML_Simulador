/**
 * Manipula todos os dados em planilhas. Leitura e Gravação
 * */
package com.pml.Excel;

import com.pml.InterfaceGrafica.IG;
import com.monitorjbl.xlsx.StreamingReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.pml.simulacao.Candle;

/**
 * @author Felipe Mattos
 *
 */
public class ExcelAbreBancoDeDados implements AcoesExcel{
    
    private String indicador;
    
    private HashSet<Candle> abreBancoDados_Minuto() throws Exception {
        HashSet<Candle> hashSetLeitura = new HashSet();
        try{
            String origem = escolheOrigem();
            File file = new File(origem);
            IG.progressoCompletoIndeterminado(true);
            try (
                InputStream is = new FileInputStream(file);
                Workbook workbook = StreamingReader.builder()
                        .rowCacheSize(100)
                        .bufferSize(4096)
                        .open(is)) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    Candle candle = new Candle();
                    LocalDateTime data;
                    for (Cell cell : row) {
                        if(row.getRowNum() == 0){
                            if(cell.getColumnIndex() == 5)
                                indicador = cell.getStringCellValue();
                        }else{
                            switch (cell.getColumnIndex()) {
                                case 0:
                                    data = new Timestamp(cell.getDateCellValue().getTime()).toLocalDateTime();
                                    candle.setData(data);
                                    break;
                                case 1:
                                    candle.setAbertura(cell.getNumericCellValue());
                                    break;
                                case 2:
                                    candle.setMaxima(cell.getNumericCellValue());
                                    break;
                                case 3:
                                    candle.setMinima(cell.getNumericCellValue());
                                    break;

                                case 4:
                                    candle.setFechamento(cell.getNumericCellValue());
                                    break;

                                case 5:
                                    candle.setIndicadorExtra(cell.getNumericCellValue());
                                    candle.setIndicador(indicador);
                                    break;
                            }
                        }
                    }
                    if(candle.getData()!= null)
                        hashSetLeitura.add(candle);
                }
                IG.progressoCompletoIndeterminado(false);
                IG.textoAdd("Operação concluída com sucesso\n") ;
                return hashSetLeitura;
            } catch (FileNotFoundException e) {
                IG.textoAdd("Arquivo Excel não encontrado!\n");
            } catch (IOException ex) {
                IG.textoAdd("erro... ");
            }
        }catch(Exception e){
            IG.textoAdd("Leitura cancelada \n");
        }
        return null;
    }
    
    private String escolheOrigem() {
        IG.textoAdd("\nCarregando planilha\n") ;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel '.xlsx'", "xlsx");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        String origem = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            com.pml.InterfaceGrafica.IG.textoAdd("Local arquivo: " + selectedFile.getAbsolutePath() + "\n");
            origem = selectedFile.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "Operação abortada!");
        }
        return origem;
    }

    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        HashSet retorno = new HashSet();
        int escolha;
        do{
            try {
                abreBancoDados_Minuto().forEach(candle -> retorno.add(candle));
            } catch (Exception ex) {
                Logger.getLogger(ExcelAbreBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
            escolha = JOptionPane.showConfirmDialog(null, "Abrir mais arquivos?", "Pergunta:", JOptionPane.YES_NO_OPTION, 0, null);
        } while(escolha == JOptionPane.YES_OPTION);
        
        new Candle().coletaDados(retorno);
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



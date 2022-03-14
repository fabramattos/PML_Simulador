/**
 * Manipula todos os dados em planilhas. Leitura e Gravação
 * */
package com.pml.Excel;

import com.pml.InterfaceGrafica.IG;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author Felipe Mattos
 *
 */
public class ExcelGravaArquivo implements AcoesExcel{
    
    private String escolheDestino() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel ''.xlsx' ", "xlsx");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        String destino = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            com.pml.InterfaceGrafica.IG.textoAdd("Salvando arquivo em: " + selectedFile.getAbsolutePath() + "\n");
            destino = selectedFile.getAbsolutePath() + ".xlsx";
        } else {
            JOptionPane.showMessageDialog(null, "Operação abortada!");
        }
        
        return destino;
    }

    @Override
    public void configuraFormatacao(SXSSFWorkbook workbookGravacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executa(SXSSFWorkbook workbookGravacao) {
        IG.progressoCompletoAtualiza(100,99);
        String destino = escolheDestino();
        try{
            FileOutputStream fos = new FileOutputStream(new File(destino));
            workbookGravacao.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            com.pml.InterfaceGrafica.IG.textoAdd("Arquivo Excel não encontrado! \n");
        } catch (IOException e) {
            e.printStackTrace();
            com.pml.InterfaceGrafica.IG.textoAdd("Erro na edição do arquivo!");
        } catch(NullPointerException e) {
            e.printStackTrace();
            IG.textoAdd("Gravação abortada! \n");
        } finally{
            IG.progressoCompletoAtualiza(1, 1);
            IG.textoAdd("=====CONCLUÍDO===== \n");
        }
    }
}



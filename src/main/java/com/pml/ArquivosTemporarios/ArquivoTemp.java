/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.ArquivosTemporarios;

import com.pml.Resumos.Resumos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabra
 */
public class ArquivoTemp {
    
    private static final String fileName = "ListaResumoDiario.temp";
    private static File newTempDir = new File(System.getProperty("java.io.tmpdir"), "Simulador");
    private static final String newFile = newTempDir.getAbsolutePath() + File.separator + fileName;
    
    public static void gravaArqTemp(List<Resumos> listaResumo){
        newTempDir.mkdir();
        newTempDir.deleteOnExit();
        //System.out.println("Pasta: " + newTempDir.toPath());
        
        File file = new File(newFile);
        file.deleteOnExit();
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaResumo);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ArquivoTemp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static File getArquivoTemp(){
        return new File(newFile);
    }
    
    public static void apagaArquivosTemp(){
        File file = new File(newFile);
        if(file.exists()){
            file.delete();
            System.out.println("arquivo apagado");
        }
    }
    
}

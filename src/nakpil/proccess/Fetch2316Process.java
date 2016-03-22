/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import com.smartxls.WorkBook;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import nakpil.core.ui.ProgressDialog;

/**
 *
 * @author HERU
 */
public class Fetch2316Process implements Runnable{
    
    private static File SrcFile;
    private static WorkBook Source;
    private final ProgressDialog PDialog;
    private final JProgressBar Bar;
    
    public Fetch2316Process(File src,ProgressDialog diag){
        SrcFile = src;
        this.PDialog = diag;
        this.Bar = PDialog.getProgressBar();
    }
    

    @Override
    public void run() {
        Bar.setMaximum(100);
        for(int i = 0;i < 100;i++){
            Bar.setValue(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fetch2316Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.ui;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 *
 * @author HERU
 */
public class ProgressDialog {
    
    private static JDialog myDialog;
    private static ProgressPanel PrPanel;
    private static Thread TRD;
    private static Object ProcessObj;
    
    public ProgressDialog(Object proc){
        ProcessObj = proc;
    }
    
    
    public JProgressBar getProgressBar(){
        return PrPanel.getProgressBar();
    }
    
    public void start(){
        
    }
    
    public void stop(){
        
    }
    
}

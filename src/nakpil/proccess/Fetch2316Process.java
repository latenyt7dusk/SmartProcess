/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import com.smartxls.WorkBook;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nakpil.core.ui.EntryUI;

/**
 * @author HERU
 */
public class Fetch2316Process implements Runnable{
    
    private final File SrcFile;
    private WorkBook Source;
    private EntryUI UI;
    
    public Fetch2316Process(String f,EntryUI ui){
        this.SrcFile = new File(f);
        UI = ui;
    }
    

    @Override
    public void run() {
        try{
            if(SrcFile.exists()){
                InputStream iStream = new FileInputStream(SrcFile.getAbsolutePath());
                Source = new WorkBook();
                Source.readXLSB(iStream);
                int Entry75a;
                int Entry75i;
                int Entry73;
                int Entry71;
                
                String Entries[] = {"7.5 Active","7.5 Inactive","7.3","7.1"};
                Source.setSheet(Source.findSheetByName(Entries[0]));
                Entry75a = Source.getLastRow();
                Source.setSheet(Source.findSheetByName(Entries[1]));
                Entry75i = Source.getLastRow();
                Source.setSheet(Source.findSheetByName(Entries[2]));
                Entry73 = Source.getLastRow();
                Source.setSheet(Source.findSheetByName(Entries[3]));
                Entry71 = Source.getLastRow();
                
               
                int Count = 0;
                UI.ProgressBar.setValue(0);
                UI.ProgressBar.setMaximum(Entry71+Entry73+Entry75a+Entry75i-5);
                DefaultTableModel TBModel = (DefaultTableModel) UI.EntryTable.getModel();
                TBModel.setRowCount(0);
                for(int sheet = 0;sheet < 4;sheet++){
                    Source.setSheet(Source.findSheetByName(Entries[sheet]));
                    for(int row = 1;row < Source.getLastRow();row++){
                        TBModel.addRow(new Object[]{Entries[sheet],Source.getText(row+1, 0),Source.getText(row+1, 1),Source.getText(row+1, 2),Source.getText(row+1, 3),Source.getText(row+1, 4),((Source.getText(row+1, 5) == null)? "":(Source.getText(row+1, 5)))});
                        UI.ProgressBar.setValue(Count);
                        Count++;
                    }
                }
                Source.dispose();
                Source = null;
                iStream.close();
                iStream = null;
                EntryUI.TRD.interrupt();
                EntryUI.TRD = null;
                System.gc();
            }else{
                JOptionPane.showMessageDialog(UI, "File not found");
            }
        }catch(Exception er){
            er.printStackTrace();
            JOptionPane.showMessageDialog(UI, "Error Fetching Alphalist Entry");
        }
    }
    
    
    
}

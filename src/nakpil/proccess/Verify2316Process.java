/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.table.DefaultTableModel;
import nakpil.algorithm.JaroWinkler;
import nakpil.core.ui.EntryUI;

/**
 *
 * @author HERU
 */
public class Verify2316Process implements Runnable {

    private final EntryUI UI;
    private DefaultTableModel TB;
    private static File Errlog;
    private static int Errcount;

    public Verify2316Process(EntryUI ui) {
        this.UI = ui;
        TB = (DefaultTableModel) UI.EntryAll_Table.getModel();
    }

    private String hasDuplicateTIN(int row) {
        String Sch = TB.getValueAt(row, 0).toString();
        String iRow = TB.getValueAt(row, 1).toString();
        String TIN = TB.getValueAt(row, 2).toString();
        String Sname= TB.getValueAt(row, 4).toString();
        String Fname= TB.getValueAt(row, 5).toString();
        String Mname= TB.getValueAt(row, 6).toString();
        String s = "";
        int clicks = 0;
        for (int i = 0; i < TB.getRowCount(); i++) {
            if (i != row) {
                if(TIN.equals(TB.getValueAt(i, 2))){
                    clicks++;
                    Errcount++;
                }
            }
        }
        if(clicks > 0){
            s = Sch+" "+iRow+" "+Sname+", "+Fname+" "+Mname+" has "+String.valueOf(clicks)+" duplicate TIN numbers";
        }
        
        return s;
    }
    private String hasDuplicateSSS(int row) {
        String Sch = TB.getValueAt(row, 0).toString();
        String iRow = TB.getValueAt(row, 1).toString();
        String SSS= TB.getValueAt(row, 3).toString();
        String Sname= TB.getValueAt(row, 4).toString();
        String Fname= TB.getValueAt(row, 5).toString();
        String Mname= TB.getValueAt(row, 6).toString();
        String s = "";
        int clicks = 0;
        for (int i = 0; i < TB.getRowCount(); i++) {
            if (i != row) {
                if(SSS.equals(TB.getValueAt(i, 3))){
                    Errcount++;
                    clicks++;
                }
            }
        }
        if(clicks > 0){
            s = Sch+" "+iRow+" "+Sname+", "+Fname+" "+Mname+" has "+String.valueOf(clicks)+" duplicate SSS numbers";
        }
        return s;
    }
    private String hasDuplicateName(int row) {
        String Sch = TB.getValueAt(row, 0).toString();
        String iRow = TB.getValueAt(row, 1).toString();
        String Sname= TB.getValueAt(row, 4).toString();
        String Fname= TB.getValueAt(row, 5).toString();
        String Mname= TB.getValueAt(row, 6).toString();
        
        String s = "";
        String Comp;
        String Fullname = "";
        JaroWinkler Matcher = new JaroWinkler();
        for (int i = 0; i < TB.getRowCount(); i++) {
            if (i != row) {
                double perc = 0;
                Comp = TB.getValueAt(i, 4).toString()+", "+TB.getValueAt(i, 5).toString()+((TB.getValueAt(i, 6).toString().isEmpty())? "":" "+TB.getValueAt(i, 6).toString());
                Fullname = Sname+", "+Fname+((Mname.isEmpty())? "":" "+Mname);
                perc = Matcher.similarity(Fullname, Comp);
                perc = perc*100;
                if(perc >= 98){
                    Errcount++;
                    s = s.concat("["+TB.getValueAt(i, 4).toString()+", "+TB.getValueAt(i, 5).toString()+" "+TB.getValueAt(i, 6).toString()+"]");
                }
            }
        }
        if(!s.isEmpty()){
            s = Sch+" "+iRow+" "+Fullname+" has similarity "+s;
        }
        return s;
    }
   

    @Override
    public void run() {
        try{
            Errlog = File.createTempFile("verify2316_", ".erlog");
            BufferedWriter BW = new BufferedWriter(new FileWriter(Errlog));
            int DataCount = TB.getRowCount();
            UI.ProgressBar.setValue(0);
            UI.ProgressBar.setMaximum(DataCount-1);
            Errcount = 0;
            for(int cRow = 0;cRow < DataCount;cRow++){
                String err;
                UI.jLabel1.setText("Verifiying "+String.valueOf(cRow+1)+" of "+DataCount);
                TB.setValueAt("Passed", cRow, 7);
                if(!(err = hasDuplicateTIN(cRow)).isEmpty()){
                    BW.write(err);
                    BW.newLine();
                    TB.setValueAt("Failed", cRow, 7);
                }
                if(!(err = hasDuplicateSSS(cRow)).isEmpty()){
                    BW.write(err);
                    BW.newLine();
                    TB.setValueAt("Failed", cRow, 7);
                }
                if(!(err = hasDuplicateName(cRow)).isEmpty()){
                    BW.write(err);
                    BW.newLine();
                    TB.setValueAt("Failed", cRow, 7);
                }
                UI.ProgressBar.setValue(cRow);
            }
            UI.jLabel1.setText("Verified : "+DataCount+" | Possible Duplicates : "+Errcount);
            BW.flush();
            BW.close();
            BW = null;
            EntryUI.TRD.interrupt();
            EntryUI.TRD = null;
            System.gc();
        }catch(Exception er){
            er.printStackTrace();
        }
    }

}

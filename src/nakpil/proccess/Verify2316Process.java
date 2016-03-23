/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import info.debatty.java.stringsimilarity.JaroWinkler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import nakpil.core.ui.EntryUI;

/**
 *
 * @author HERU
 */
public class Verify2316Process implements Runnable {

    private final EntryUI UI;
    private DefaultTableModel TB;
    private List<VerifyError> Errors = new ArrayList();
    private static int VERIFY1 = 101;
    private static int VERIFY2 = 102;
    private static int VERIFY3 = 103;

    public Verify2316Process(EntryUI ui) {
        this.UI = ui;
        TB = (DefaultTableModel) UI.EntryTable.getModel();
    }

    private String hasDuplicateTIN(int row) {
        String TIN = TB.getValueAt(row, 2).toString();
        String s = "";
        for (int i = 0; i < TB.getRowCount(); i++) {
            if (i != row) {
                if(TIN.equals(TB.getValueAt(i, 2))){
                    s = s.concat("["+TB.getValueAt(i, 2)+"]");
                }
            }
        }
        return s;
    }
    private String hasDuplicateSSS(int row) {
        String SSS= TB.getValueAt(row, 3).toString();
        String s = "";
        for (int i = 0; i < TB.getRowCount(); i++) {
            if (i != row) {
                if(SSS.equals(TB.getValueAt(i, 3))){
                    s = s.concat("["+TB.getValueAt(i, 3)+"]");
                }
            }
        }
        return s;
    }
    private String hasDuplicateName(int row) {
        String Sname= TB.getValueAt(row, 4).toString();
        String Fname= TB.getValueAt(row, 5).toString();
        String Mname= TB.getValueAt(row, 6).toString();
        
        String s = "";
        JaroWinkler Matcher = new JaroWinkler();
        for (int i = 0; i < TB.getRowCount(); i++) {
            if (i != row) {
                double perc = 0;
                perc = Matcher.distance(Sname, TB.getValueAt(i, 4).toString())+Matcher.distance(Fname, TB.getValueAt(i, 5).toString())+Matcher.distance(Mname, TB.getValueAt(i, 6).toString());
                perc = perc*100;
                if(perc >= 98){
                    s = s.concat("["+TB.getValueAt(i, 4).toString()+", "+TB.getValueAt(i, 5).toString()+" "+TB.getValueAt(i, 6).toString()+"]");
                }
            }
        }
        return s;
    }

    private void addVerifyError(int row, int verpass, String er) {
        if (Errors.size() > 0) {
            boolean found = false;
            int index = 0;
            String Sch = TB.getValueAt(row, 0).toString();
            String iRow = TB.getValueAt(row, 1).toString();
            for (int i = 0; i < Errors.size(); i++) {
                if (Errors.get(i).getSchedule().equals(Sch) && Errors.get(i).getRow().equals(iRow)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                VerifyError Ve = Errors.get(index);
                if (verpass == VERIFY1) {
                    Ve.addVerificationError_1(er);
                } else if (verpass == VERIFY2) {
                    Ve.addVerificationError_2(er);
                } else if (verpass == VERIFY3) {
                    Ve.addVerificationError_3(er);
                }
            } else {
                VerifyError Ve = Errors.get(index);
                Ve.setSchedule(TB.getValueAt(row, 0).toString());
                Ve.setRowIndex(TB.getValueAt(row, 1).toString());
                Ve.setTin(TB.getValueAt(row, 2).toString());
                Ve.setSSS(TB.getValueAt(row, 3).toString());
                Ve.setSurname(TB.getValueAt(row, 4).toString());
                Ve.setFirstname(TB.getValueAt(row, 5).toString());
                Ve.setMidlename(TB.getValueAt(row, 6).toString());

                if (verpass == VERIFY1) {
                    Ve.addVerificationError_1(er);
                } else if (verpass == VERIFY2) {
                    Ve.addVerificationError_2(er);
                } else if (verpass == VERIFY3) {
                    Ve.addVerificationError_3(er);
                }
            }
        } else {
            VerifyError Ve = new VerifyError();
            Ve.setSchedule(TB.getValueAt(row, 0).toString());
            Ve.setRowIndex(TB.getValueAt(row, 1).toString());
            Ve.setTin(TB.getValueAt(row, 2).toString());
            Ve.setSSS(TB.getValueAt(row, 3).toString());
            Ve.setSurname(TB.getValueAt(row, 4).toString());
            Ve.setFirstname(TB.getValueAt(row, 5).toString());
            Ve.setMidlename(TB.getValueAt(row, 6).toString());

            if (verpass == VERIFY1) {
                Ve.addVerificationError_1(er);
            } else if (verpass == VERIFY2) {
                Ve.addVerificationError_2(er);
            } else if (verpass == VERIFY3) {
                Ve.addVerificationError_3(er);
            }
        }
    }

    @Override
    public void run() {
        try{
            int DataCount = TB.getRowCount();
            UI.ProgressBar.setValue(0);
            UI.ProgressBar.setMaximum(DataCount-1);
            for(int cRow = 0;cRow < DataCount;cRow++){
                System.out.println("Test 1");
                String err;
                if(!(err = hasDuplicateTIN(cRow)).isEmpty()){
                    addVerifyError(cRow,VERIFY1,"{Duplicate TIN:"+err+"}");
                }
                System.out.println("Test 2");
                if(!(err = hasDuplicateSSS(cRow)).isEmpty()){
                    addVerifyError(cRow,VERIFY1,"{Duplicate SSS:"+err+"}");
                }
                System.out.println("Test 3");
                if(!(err = hasDuplicateName(cRow)).isEmpty()){
                    addVerifyError(cRow,VERIFY1,"{Similarity Names:"+err+"}");
                }
                UI.ProgressBar.setValue(cRow);
            }
            for(int i = 0 ;i < Errors.size();i++){
                System.out.println(Errors.get(i).getVerificationError_1());
            }
            EntryUI.TRD.interrupt();
            EntryUI.TRD = null;
            System.gc();
        }catch(Exception er){
            er.printStackTrace();
        }
    }

}

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
public class Fetch2316Process implements Runnable {

    private final File SrcFile;
    private WorkBook Source;
    private EntryUI UI;
    public static int INDEX = 0;
    public static int SITE = 1;
    public static int TIN = 2;
    public static int SSS = 3;
    public static int SURNAME = 4;
    public static int FIRSTNAME = 5;
    public static int MIDDLENAME = 6;
    public static int DATEHIRED = 7;
    public static int DATESEPARATED = 8;
    public static int NONTAXBASIC = 9;
    public static int NONTAXOVERTIME = 10;
    public static int NONTAXNIGHTDIFF = 11;
    public static int NONTAXHOLIDAY = 12;
    public static int NONTAX13MONTH = 13;
    public static int NONTAXGOVCONTRI = 14;
    public static int NONTAXOTHERSALARIES = 15;
    public static int NONTAXDEMINIMIS = 16;
    public static int NONTAXGROSS = 17;

    public Fetch2316Process(String f, EntryUI ui) {
        this.SrcFile = new File(f);
        UI = ui;
        
    }

    @Override
    public void run() {
        try {
            if (SrcFile.exists()) {
                UI.jLabel1.setText("Starting Import Service");
                InputStream iStream = new FileInputStream(SrcFile.getAbsolutePath());
                Source = new WorkBook();
                Source.readXLSB(iStream);
                int Entry75a;
                int Entry75i;
                int Entry73;
                int Entry71;

                String Entries[] = {"7.5 Active", "7.5 Inactive", "7.3", "7.1"};
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
                UI.ProgressBar.setMaximum(Entry71 + Entry73 + Entry75a + Entry75i - 5);
                //Remake
                DefaultTableModel TB_75a = (DefaultTableModel) UI.Entry75a_Table.getModel();
                DefaultTableModel TB_75i = (DefaultTableModel) UI.Entry75i_Table.getModel();
                DefaultTableModel TB_73 = (DefaultTableModel) UI.Entry73_Table.getModel();
                DefaultTableModel TB_71 = (DefaultTableModel) UI.Entry71_Table.getModel();
                DefaultTableModel TB_All = (DefaultTableModel) UI.EntryAll_Table.getModel();
                TB_All.setRowCount(0);
                int entries = 0;
                double basic,ot,nd,hol,_13th,govt,othersal,demini,gross;
               
                for (int sheet = 0; sheet < Source.getNumSheets(); sheet++) {
                    Source.setSheet(sheet);
                    if (Source.getSheetName(sheet).equals(Entries[0])) {//7.5 Active
                        TB_75a.setRowCount(0);
                        for (int row = 1; row <= Source.getLastRow(); row++) {
                            Thread.sleep((long) 0.85);
                            TB_75a.addRow(new Object[]{Source.getText(row, INDEX), Source.getText(row, SITE), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            TB_All.addRow(new Object[]{Entries[0], Source.getText(row, INDEX), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            
                            UI.ProgressBar.setValue(Count);
                            UI.jLabel1.setText("Importing "+Entries[0]+" "+ String.valueOf(row) + " of " + String.valueOf(Source.getLastRow()-1));
                            Count++;
                        }
                        entries = Count;
                    }else if (Source.getSheetName(sheet).equals(Entries[1])) {//7.5 Inactive
                        TB_75i.setRowCount(0);
                        for (int row = 1; row <= Source.getLastRow(); row++) {
                            Thread.sleep((long) 0.85);
                            TB_75i.addRow(new Object[]{Source.getText(row, INDEX), Source.getText(row, SITE), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            TB_All.addRow(new Object[]{Entries[1], Source.getText(row, INDEX), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            
                            UI.ProgressBar.setValue(Count);
                            UI.jLabel1.setText("Importing "+Entries[1]+" "+ String.valueOf(row) + " of " + String.valueOf(Source.getLastRow()-1));
                            Count++;
                        }
                    }else if (Source.getSheetName(sheet).equals(Entries[2])) {//7.3
                        TB_73.setRowCount(0);
                        for (int row = 1; row <= Source.getLastRow(); row++) {
                            Thread.sleep((long) 0.85);
                            TB_73.addRow(new Object[]{Source.getText(row, INDEX), Source.getText(row, SITE), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            TB_All.addRow(new Object[]{Entries[2], Source.getText(row, INDEX), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            
                            UI.ProgressBar.setValue(Count);
                            UI.jLabel1.setText("Importing "+Entries[2]+" "+ String.valueOf(row) + " of " + String.valueOf(Source.getLastRow()-1));
                            Count++;
                        }
                    }
                    else if (Source.getSheetName(sheet).equals(Entries[3])) {//7.1
                        TB_71.setRowCount(0);
                        for (int row = 1; row <= Source.getLastRow(); row++) {
                            Thread.sleep((long) 0.85);
                            TB_71.addRow(new Object[]{Source.getText(row, INDEX), Source.getText(row, SITE), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            TB_All.addRow(new Object[]{Entries[3], Source.getText(row, INDEX), Source.getText(row, TIN), Source.getText(row, SSS), Source.getText(row, SURNAME),
                                Source.getText(row, FIRSTNAME),((Source.getText(row, MIDDLENAME) == null) ? "" : (Source.getText(row, MIDDLENAME)))});
                            
                            UI.ProgressBar.setValue(Count);
                            UI.jLabel1.setText("Importing "+Entries[3]+" "+ String.valueOf(row) + " of " + String.valueOf(Source.getLastRow()-1));
                            Count++;
                        }
                    }
                }
                UI.jLabel1.setText("Imported " + String.valueOf(Entry71 + Entry73 + Entry75a + Entry75i) + " Entries");
                Source.dispose();
                Source = null;
                iStream.close();
                iStream = null;
                EntryUI.TRD.interrupt();
                EntryUI.TRD = null;
                System.gc();

                

            } else {
                JOptionPane.showMessageDialog(UI, "File not found");
            }
        } catch (Exception er) {
            er.printStackTrace();
            JOptionPane.showMessageDialog(UI, "Error Fetching Alphalist Entry");
        }
    }

}

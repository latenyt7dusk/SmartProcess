/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.smartxls.WorkBook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import nakpil.core.cl.BIRAccount;
import nakpil.core.cl.Data2316;
import nakpil.core.ui.ProgressDialog;

/**
 *
 * @author HERU
 */
public final class Form2316Process implements Runnable {

    private static List<BIRAccount> Datas;
    private static String Personel;
    private static WorkBook Template;
    private static String Output;
    private static String Output_batch;
    private static String Source;
    private static JProgressBar Bar;
    private static JButton Button;
    private static InputStream iStream;
    private static Data2316 BIR_Form;
    private static OutputStream oStream;
    private static OutputStream oStream_bulk;
    private static boolean Print;
    private static int Copy;
    private static PdfReader PDFreader;
    private static PdfCopy PDFcopy;
    private static com.itextpdf.text.Document document;
    private static String printCMD;
    private static String printPreARG;
    private static String printPostARG;
    private static boolean doPrint;
    private static Process PrintProgram;
    

    public Form2316Process(final List<BIRAccount> acc, String perso, JButton b) {
        Datas = acc;
        Personel = perso;
        Button = b;
    }
    
    public void setPrint(boolean b){
        Print =b;
    }
    
    public void setPrintCopy(int cp){
        Copy = cp;
    }
    
    public void setProgressDialog(ProgressDialog jpb){
        Bar = jpb.getProgressBar();
    }

    public final void setTemplate(String src) {
        Source = src;
    }

    public final void setOutput(String out) {
        Output = out;
        Output_batch = out;
    }
    
    public final void setPrintCommanlineArguement(String cmd,String prearg,String postarg){
        printCMD = cmd;
        printPreARG = prearg;
        printPostARG = postarg;
    }
    

    @Override
    public void run() {
        try {

            reset();
            if(Button != null){
                Button.setText("Stop");
            }
            if (Datas.size() > 1) {
                Bar.setMaximum(Datas.size());
                int i = 0;
                document = new com.itextpdf.text.Document();
                oStream_bulk = new FileOutputStream(Output_batch+File.separator+Datas.get(0).getSequenceID()+"-"+Datas.get(Datas.size()-1).getSequenceID()+".pdf");
                PDFcopy = new PdfCopy(document,oStream_bulk);
                document.open();
                for (BIRAccount Temp : Datas) {
                    i++;
                    Bar.setString("Encoding " + Temp.getFullname());
                    
                    //Open Template
                    Template = new WorkBook();
                    iStream = new FileInputStream(Source);
                    Template.readXLSB(iStream);
                    
                    //Forming Data Encode
                    BIR_Form = new Data2316();
                    BIR_Form.setBIRAccount(Temp, Personel);
                    BIR_Form.encodeTo(Template);
                    
                    //Prepare Pdf Output
                    Output = ((Output.isEmpty()) ? System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "BIR2316" + File.separator + Temp.getTinNumber()
                            + ".pdf" : Output + File.separator + Temp.getTinNumber() + ".pdf");
                    oStream = new FileOutputStream(Output);
                    Bar.setString("Exporting PDF : " + Output);
                    Template.exportPDF(oStream);
                    
                    
                    //Close Exported PDF
                    oStream.flush();
                    oStream.close();
                    oStream = null;
                    
                    //Patch Document
                    PDFreader = new PdfReader(Output);
                    PDFcopy.addDocument(PDFreader);
                    PDFcopy.freeReader(PDFreader);
                    PDFcopy.flush();
                    
                    //Close Copied PDF
                    PDFreader.close();
                    PDFreader = null;
                    
                    //Close Template
                    Template.dispose();
                    Template = null;
                    iStream.close();
                    iStream = null;
                    
                    Bar.setValue(i);
                    System.gc();
                }
                PDFcopy.close();
                document.close();
                oStream_bulk.flush();
                oStream_bulk.close();
                PDFcopy = null;
                oStream = null;
                if(Print){
                    if(Copy == 1){
                        PrintProgram = Runtime.getRuntime().exec(printCMD+((printPreARG.isEmpty())? "":" "+printPreARG+" ")+
                                "\""+Output_batch+File.separator+Datas.get(0).getSequenceID()+"-"+Datas.get(Datas.size()-1).getSequenceID()+".pdf\""+
                                ((printPostARG.isEmpty())? "":" "+printPostARG));
                        Bar.setString("Print Job Sent.");
                    }else if(Copy > 1){
                        for(int c = 1;c <= Copy;c++){
                            PrintProgram = Runtime.getRuntime().exec(printCMD+((printPreARG.isEmpty())? "":" "+printPreARG+" ")+
                                "\""+Output_batch+File.separator+Datas.get(0).getSequenceID()+"-"+Datas.get(Datas.size()-1).getSequenceID()+".pdf\""+
                                ((printPostARG.isEmpty())? "":" "+printPostARG));
                            Bar.setString("Print Job ["+i+"] Sent.");
                        }
                    }
                }
                Bar.setString("Finished");
            } else if (Datas.size() == 1) {
                Bar.setMaximum(2);
                Bar.setValue(1);
                Bar.setString("Encoding " + Datas.get(0).getFullname());
                
                //Load Template
                Template = new WorkBook();
                iStream = new FileInputStream(Source);
                Template.readXLSX(iStream);
                
                //Encode Data
                BIR_Form = new Data2316();
                BIR_Form.setBIRAccount(Datas.get(0), Personel);
                BIR_Form.encodeTo(Template);
                
                //Prepare PDF Export;
                Output = ((Output.isEmpty()) ? System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "BIR2316" + File.separator + Datas.get(0).getTinNumber()
                        + ".pdf" : Output + File.separator + Datas.get(0).getTinNumber() + ".pdf");
                oStream = new FileOutputStream(Output);
                if (Print) {
                    Bar.setString("Printing 1 of 1");
                    Template.setPrintNumberOfCopies(Copy);
                    Template.print();
                }
                Bar.setString("Exporting PDF : " + Output);
                Template.exportPDF(oStream);
                
                Bar.setValue(2);
                Bar.setString("Process Done");
            }
        } catch (Exception er) {
            if(Button != null){
                Button.setText("Start");
            }
            Bar.setString(er.toString());
        } finally {
            try {
                if (iStream != null) {
                    iStream.close();
                }
                if (oStream != null) {
                    oStream.flush();
                    oStream.close();
                }
                if (PDFcopy != null){
                    PDFcopy.close();
                    document.close();
                    oStream_bulk.flush();
                    oStream_bulk.close();
                }
                BIR_Form = null;
                Datas = null;
                Template = null;
                iStream = null;
                oStream = null;
                PDFcopy = null;
                oStream = null;
                document = null;
                System.gc();
            } catch (Exception er) {
                Bar.setString(er.toString());
            }
        }
    }

    private void reset() {
        Bar.setString("");
        Bar.setValue(0);
    }

}

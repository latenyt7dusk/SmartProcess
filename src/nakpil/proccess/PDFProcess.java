/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

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

/**
 *
 * @author HERU
 */
public final class PDFProcess implements Runnable {

    private static List<BIRAccount> Datas;
    private static String Personel;
    private static WorkBook Template;
    private static String Output;
    private static String Source;
    private static JProgressBar Bar;
    private static JButton Button;
    private static InputStream iStream;
    private static Data2316 BIR_Form;
    private static OutputStream oStream;
    private static boolean Print;
    private static int Copy;

    public PDFProcess(List<BIRAccount> acc, String perso, boolean print, int cp, JProgressBar jpb, JButton b) {
        Datas = acc;
        Personel = perso;
        Bar = jpb;
        Button = b;
    }

    public final void setTemplate(String src) {
        Source = src;
    }

    public final void setOutput(String out) {
        Output = out;
    }

    @Override
    public void run() {
        try {

            reset();
            Button.setText("Stop");
            if (Datas.size() > 1) {
                Bar.setMaximum(Datas.size());
                int i = 0;
                for(BIRAccount Temp:Datas){
                    
                }
                
            } else if (Datas.size() == 1) {
                Bar.setMaximum(2);
                Bar.setValue(1);
                Bar.setString("Encoding "+Datas.get(0).getFullname());
                Template = new WorkBook();
                iStream = new FileInputStream(Source);
                Template.readXLSX(iStream);
                BIR_Form = new Data2316();
                BIR_Form.setBIRAccount(Datas.get(0), Personel);
                BIR_Form.encodeTo(Template);
                Output = ((Output.isEmpty()) ? System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "BIR2316" + File.separator + Datas.get(0).getTinNumber()
                        + ".pdf" : Output + File.separator + Datas.get(0).getTinNumber() + ".pdf");
                oStream = new FileOutputStream(Output);
                if(Print){
                    Bar.setString("Printing 1 of 1");
                    Template.setPrintNumberOfCopies(Copy);
                    Template.print();
                }
                Bar.setString("Exporting PDF : "+Output);
                Template.exportPDF(oStream);
                Bar.setValue(2);
                Bar.setString("Process Done");
            } else {

            }
        } catch (Exception er) {
            Button.setText("Start");

        } finally {
            try {
                if (iStream != null) {
                    iStream.close();
                }
                if(oStream != null){
                    oStream.flush();
                    oStream.close();
                }
                BIR_Form = null;
                Datas = null;
                Template = null;
                iStream = null;
                oStream = null;
                System.gc();
            } catch (Exception er) {

            }

        }
    }

    private void reset() {
        Bar.setString("");
        Bar.setValue(0);
    }

}

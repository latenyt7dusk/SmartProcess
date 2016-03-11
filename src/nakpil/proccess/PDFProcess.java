/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import com.smartxls.WorkBook;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import nakpil.core.cl.BIRAccount;

/**
 *
 * @author HERU
 */
public final class PDFProcess implements Runnable {

    private final List<BIRAccount> Datas;
    private final String Personel;
    private static WorkBook Template;
    private static String Output;
    private static String Source;
    private static JProgressBar Bar;
    private static JButton Button;

    public PDFProcess(List<BIRAccount> acc, String perso, boolean print, int cp, JProgressBar jpb,JButton b) {
        this.Datas = acc;
        this.Personel = perso;
        Bar = jpb;
    }

    public final void setTemplate(String src) {
        Source = src;
    }

    @Override
    public void run() {
        try {
            reset();
            Button.setText("Stop");
            if (Datas.size() > 1) {
                
            } else if (Datas.size() == 1) {
                
            } else {

            }
        } catch (Exception er) {
            Button.setText("Start");
        }
    }

    private void reset() {
        Bar.setString("");
        Bar.setValue(0);
    }

}

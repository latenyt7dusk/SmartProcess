/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import com.smartxls.WorkBook;
import java.util.List;
import nakpil.core.cl.BIRAccount;

/**
 *
 * @author HERU
 */
public final class PDFProcess implements Runnable{

    private final List<BIRAccount> Datas;
    private final String Personel;
    private static WorkBook Template;
    private static String Output;
    
    public PDFProcess(List<BIRAccount> acc,String perso,boolean print,int cp){
        this.Datas = acc;
        this.Personel = perso;
    }
    
    public void setTemplate(String source){
        
    }
    
    @Override
    public void run() {
        try{
            
        }catch(Exception er){
            
        }
    }
    
}

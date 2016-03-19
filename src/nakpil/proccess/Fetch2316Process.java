/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

import com.smartxls.WorkBook;
import java.io.File;

/**
 *
 * @author HERU
 */
public class Fetch2316Process implements Runnable{
    
    private static File SrcFile;
    private static WorkBook Source;
    
    public Fetch2316Process(File src){
        SrcFile = src;
    }

    @Override
    public void run() {
        
    }
    
    
    
}

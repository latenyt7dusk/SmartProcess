/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.cl;

import nakpil.income.Income;
import nakpil.work.JobInformation;


/**
 * @author HERU
 */
public class NewMain {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Throwable {
        // TODO code application logic here
        BIRAccount ss = new BIRAccount(new Income(),new JobInformation());
        Thread.sleep(10000);
    }
}

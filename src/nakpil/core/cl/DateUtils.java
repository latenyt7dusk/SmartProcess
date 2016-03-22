/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.cl;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author HERU
 */
public class DateUtils {
    
    private static final Calendar Cal = Calendar.getInstance();
    private static final long MILLI_SECONDS_YEAR = 31558464000L;
    private static final long MILLI_SECONDS_DAY = 86400000L;
    
    public static Date convertExcelDate(int val){
        try{
            Calendar d1 = (Calendar)Cal.clone();
            d1.set(Calendar.YEAR, 1900);
            d1.set(Calendar.DAY_OF_MONTH, 1);
            d1.set(Calendar.MONTH, 0);
            d1.set(Calendar.DATE, val-1);
            return d1.getTime();
        }catch(Exception er){
            return ((Calendar) Cal.clone()).getTime();
        }
    }
    
   public static int computeAge(Date sDate) {
        Date dbDate;
        try {
            dbDate = sDate;
            dbDate.setHours(0);
            long timeDiff = ((System.currentTimeMillis() <= dbDate.getTime())? 0: System.currentTimeMillis() - dbDate.getTime());
            int age = (int) (timeDiff / MILLI_SECONDS_YEAR);

            return age;
        } catch (Exception e) {
            return 0;
        }
    }
}

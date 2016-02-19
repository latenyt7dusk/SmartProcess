/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.ab;

/**
 *
 * @author Duskmourne
 */
public abstract interface Employee {
    
    public static final String COMPANY = "COMPANY";
    public static final String DEPARTMENT = "DEPARTMENT";
    public static final String POSITION = "POSITION";
    public static final String MINIMUM_DAILYWAGE = "MINIMUM_DAILYWAGE";
    public static final String MINIMUM_MONTHLYWAGE = "MINIMUM_MONTHLYWAGE";
    public static final String MINIMUM_ANNUALWAGE = "MINIMUM_ANNUALWAGE";
    public static final String DATE_HIRED = "DATE_HIRED";
    public static final String DATE_SEPARATED = "DATE_SEPARATED";
    
    public abstract void setCompany(String s);
    public abstract String getCompany();
    
    public abstract void setDepartment(String s);
    public abstract String getDepartment();
    
    public abstract void setPosition(String s);
    public abstract String getPosition();
    
    public abstract void setMinimumDailyWage(String s);
    public abstract String getMinimumDailyWage();
    
    public abstract void setMinimumMonthlyWage(String s);
    public abstract String getMinimumMonthlyWage();
    
    public abstract void setMinimumAnnualWage(String s);
    public abstract String getMinimumAnnualWage();
    
    public abstract void setDateHired(String s);
    public abstract String getDateHired();
    
    public abstract void setDateSaparated(String s);
    public abstract String getDateSeparated();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.ab.income;

/**
 *
 * @author Duskmourne
 */
public abstract interface NonTaxable {
    
    public static final String NONTAXABLE_GROSSPAY = "NONTAXABLE_GROSSPAY";
    public static final String NONTAXABLE_BASICPAY = "NONTAXABLE_BASICPAY";
    public static final String NONTAXABLE_HOLIDAYPAY = "NONTAXABLE_HOLIDAYPAY";
    public static final String NONTAXABLE_OVERTIMEPAY = "NONTAXABLE_OVERTIMEPAY";
    public static final String NONTAXABLE_NIGHTDIFFERENTIALPAY = "NONTAXABLE_NIGHTDIFFERENTIALPAY";
    public static final String NONTAXABLE_GOVERNMENTCONTRIBUTIONS = "NONTAXABLE_GOVERNMENTCONTRIBUTIONS";
    public static final String NONTAXABLE_OTHERSALARY = "NONTAXABLE_OTHERSALARY";
    public static final String NONTAXABLE_DEMINIMISBENEFITS = "NONTAXABLE_DEMINIMISBENIFITS";
    public static final String NONTAXABLE_13THMONTHPAY = "NONTAXABLE_13THMONTHPAY";
    public static final String NONTAXABLE_HAZARDPAY = "NONTAXABLE_HAZARDPAY";
    
    public abstract void setNonTaxableGrossPay(String s);
    public abstract String getNonTaxableGrossPay();
    
    public abstract void setNonTaxableBasicPay(String s);
    public abstract String getNonTaxableBasicPay();
    
    public abstract void setNonTaxableHolidayPay(String s);
    public abstract String getNonTaxableHolidayPay();
    
    public abstract void setNonTaxableOvertimePay(String s);
    public abstract String getNonTaxableOvertimePay();
    
    public abstract void setNonTaxableNightDifferentialPay(String s);
    public abstract String getNonTaxableNightDifferentialPay();
    
    public abstract void setNonTaxableGovermentContributions(String s);
    public abstract String getNonTaxableGovermentContributions();
    
    public abstract void setNonTaxableOtherSalary(String s);
    public abstract String getNonTaxableOtherSalary();
    
    public abstract void setNonTaxableDeminimisBenifits(String s);
    public abstract String getNonTaxableDeminimisBenifits();
    
    public abstract void setNonTaxable13thMonthPay(String s);
    public abstract String getNonTaxable13thMonthPay();
    
    public abstract void setNonTaxableHazardPay(String s);
    public abstract String getNonTaxableHazardPay();
    //
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.ab.income;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Duskmourne
 */
public class Income implements Taxable,NonTaxable{

    private Map<String,String> Data = new HashMap(){
        {
            //Taxable Income
            put(TAXABLE_GROSSPAY,"0");
            put(TAXABLE_BASICPAY,"0");
            put(TAXABLE_HOLIDAYPAY,"0");
            put(TAXABLE_OVERTIMEPAY,"0");
            put(TAXABLE_NIGHTDIFFERENTIALPAY,"0");
            put(TAXABLE_OTHERSALARY,"0");
            put(TAXABLE_GOVERNMENTCONTRIBUTIONS,"0");
            put(TAXABLE_DEMINIMISBENEFITS,"0");
            put(TAXABLE_13THMONTHPAY,"0");
            put(TAXABLE_HAZARDPAY,"0");
            
            //Non-Taxable Income
            put(NONTAXABLE_GROSSPAY,"0");
            put(NONTAXABLE_BASICPAY,"0");
            put(NONTAXABLE_HOLIDAYPAY,"0");
            put(NONTAXABLE_OVERTIMEPAY,"0");
            put(NONTAXABLE_NIGHTDIFFERENTIALPAY,"0");
            put(NONTAXABLE_OTHERSALARY,"0");
            put(NONTAXABLE_GOVERNMENTCONTRIBUTIONS,"0");
            put(NONTAXABLE_DEMINIMISBENEFITS,"0");
            put(NONTAXABLE_13THMONTHPAY,"0");
            put(NONTAXABLE_HAZARDPAY,"0");
    }};
    
    public void setData(Map<String,String> data){
        this.Data = data;
    }
    
    public Map<String,String> getData(){
        return Data;
    }
    
    @Override
    public void setTaxableGrossPay(String s) {
        this.Data.put(TAXABLE_GROSSPAY, s);
    }

    @Override
    public String getTaxableGrossPay() {
        return Data.get(TAXABLE_GROSSPAY);
    }

    @Override
    public void setTaxableBasicPay(String s) {
        this.Data.put(TAXABLE_BASICPAY, s);
    }

    @Override
    public String getTaxableBasicPay() {
        return Data.get(TAXABLE_BASICPAY);
    }

    @Override
    public void setTaxableHolidayPay(String s) {
        this.Data.put(TAXABLE_HOLIDAYPAY, s);
    }

    @Override
    public String getTaxableHolidayPay() {
        return Data.get(TAXABLE_HOLIDAYPAY);
    }

    @Override
    public void setTaxableOvertimePay(String s) {
        this.Data.put(TAXABLE_OVERTIMEPAY, s);
    }

    @Override
    public String getTaxableOvertimePay() {
        return Data.get(TAXABLE_OVERTIMEPAY);
    }

    @Override
    public void setTaxableNightDifferentialPay(String s) {
        this.Data.put(TAXABLE_NIGHTDIFFERENTIALPAY, s);
    }

    @Override
    public String getTaxableNightDifferentialPay() {
        return Data.get(TAXABLE_NIGHTDIFFERENTIALPAY);
    }

    @Override
    public void setTaxableGovermentContributions(String s) {
        this.Data.put(TAXABLE_GOVERNMENTCONTRIBUTIONS, s);
    }

    @Override
    public String getTaxableGovermentContributions() {
        return Data.get(TAXABLE_GOVERNMENTCONTRIBUTIONS);
    }

    @Override
    public void setTaxableOtherSalary(String s) {
        this.Data.put(TAXABLE_OTHERSALARY, s);
    }

    @Override
    public String getTaxableOtherSalary() {
        return Data.get(TAXABLE_OTHERSALARY);
    }

    @Override
    public void setTaxableDeminimisBenifits(String s) {
        this.Data.put(TAXABLE_DEMINIMISBENEFITS, s);
    }

    @Override
    public String getTaxableDeminimisBenifits() {
        return Data.get(TAXABLE_DEMINIMISBENEFITS);
    }

    @Override
    public void setTaxable13thMonthPay(String s) {
        this.Data.put(TAXABLE_13THMONTHPAY, s);
    }

    @Override
    public String getTaxable13thMonthPay() {
        return Data.get(TAXABLE_13THMONTHPAY);
    }
    
    @Override
    public void setTaxableHazardPay(String s) {
        this.Data.put(TAXABLE_HAZARDPAY, s);
    }

    @Override
    public String getTaxableHazardPay() {
        return Data.get(TAXABLE_HAZARDPAY);
    }

    @Override
    public void setNonTaxableGrossPay(String s) {
        this.Data.put(NONTAXABLE_GROSSPAY, s);
    }

    @Override
    public String getNonTaxableGrossPay() {
        return Data.get(NONTAXABLE_GROSSPAY);
    }

    @Override
    public void setNonTaxableBasicPay(String s) {
        this.Data.put(NONTAXABLE_BASICPAY, s);
    }

    @Override
    public String getNonTaxableBasicPay() {
        return Data.get(NONTAXABLE_BASICPAY);
    }

    @Override
    public void setNonTaxableHolidayPay(String s) {
        this.Data.put(NONTAXABLE_HOLIDAYPAY, s);
    }

    @Override
    public String getNonTaxableHolidayPay() {
        return Data.get(NONTAXABLE_HOLIDAYPAY);
    }

    @Override
    public void setNonTaxableOvertimePay(String s) {
        this.Data.put(NONTAXABLE_OVERTIMEPAY, s);
    }

    @Override
    public String getNonTaxableOvertimePay() {
        return Data.get(NONTAXABLE_OVERTIMEPAY);
    }

    @Override
    public void setNonTaxableNightDifferentialPay(String s) {
        this.Data.put(NONTAXABLE_NIGHTDIFFERENTIALPAY, s);
    }

    @Override
    public String getNonTaxableNightDifferentialPay() {
        return Data.get(NONTAXABLE_NIGHTDIFFERENTIALPAY);
    }

    @Override
    public void setNonTaxableGovermentContributions(String s) {
        this.Data.put(NONTAXABLE_GOVERNMENTCONTRIBUTIONS, s);
    }

    @Override
    public String getNonTaxableGovermentContributions() {
        return Data.get(NONTAXABLE_GOVERNMENTCONTRIBUTIONS);
    }

    @Override
    public void setNonTaxableOtherSalary(String s) {
        this.Data.put(NONTAXABLE_OTHERSALARY, s);
    }

    @Override
    public String getNonTaxableOtherSalary() {
        return Data.get(NONTAXABLE_OTHERSALARY);
    }

    @Override
    public void setNonTaxableDeminimisBenifits(String s) {
        this.Data.put(NONTAXABLE_DEMINIMISBENEFITS, s);
    }

    @Override
    public String getNonTaxableDeminimisBenifits() {
        return Data.get(NONTAXABLE_DEMINIMISBENEFITS);
    }

    @Override
    public void setNonTaxable13thMonthPay(String s) {
        this.Data.put(NONTAXABLE_13THMONTHPAY, s);
    }

    @Override
    public String getNonTaxable13thMonthPay() {
        return Data.get(NONTAXABLE_13THMONTHPAY);
    }

    @Override
    public void setNonTaxableHazardPay(String s) {
        this.Data.put(NONTAXABLE_HAZARDPAY, s);
    }

    @Override
    public String getNonTaxableHazardPay() {
        return Data.get(NONTAXABLE_HAZARDPAY);
    }
    
    
}

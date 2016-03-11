/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.cl;

import com.smartxls.WorkBook;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nakpil.income.Income;
import nakpil.personal.Dependent;
import nakpil.work.Employer;
import nakpil.work.JobInformation;

/**
 * @author HERU
 */
public class Data2316 {

    private static BIRAccount myEmployee;
    private static int SEQ_ID;
    private static Income myIncome;
    private static JobInformation JobInfo;
    private static Employer myEmployer;
    private static final SimpleDateFormat DateParser = new SimpleDateFormat("yyyy-MM-dd");
    private static Date TempDate;
    private static char[] myTIN;
    private char[] eTIN;
    private static List<Dependent> Dependents;
    private static String AuthorizedPersonel = "";
    private static double Result1;    
    private static double Result2;
    
    
    /**
     * @param bir - BIR 2316 Account
     * @param personel - Authorized Assignatory Personnel
     */
    public void setBIRAccount(BIRAccount bir,String personel) {
        myEmployee = bir;
        SEQ_ID = Integer.parseInt(myEmployee.getSequenceID());
        myIncome = myEmployee.getAnnualIncome();
        JobInfo = myEmployee.getJobInformation();
        myEmployer = JobInfo.getCompany();
        Dependents = myEmployee.getDependents();
        AuthorizedPersonel = personel;
    }

    /**
     *
     * @param temp - Excel Workboot Template of BIRForm-2316
     * @return
     */
    public boolean encodeTo(WorkBook temp) {
        try {
            if (temp != null) {

                //Printing Presets
                temp.setSheet(0);
                temp.setPrintHeader("");
                temp.setPrintFooter("");
                temp.setPrintArea("$A$1:$AQ$168");

                temp.setPrintPaperSize(12240, 18720);//18720);20160
                temp.setPrintTopMargin(0); //1.05   
                temp.setPrintBottomMargin(0); //1.03
                temp.setPrintHeaderMargin(0);
                temp.setPrintFooterMargin(0);
                temp.setPrintLeftMargin(0.1);
                temp.setPrintRightMargin(0.1);
                temp.setPrintHCenter(true);
                temp.setPrintScale(88);//68 for landscape & 66 for Protrait

                //Index and Year
                temp.setText("AO1", String.valueOf(SEQ_ID));
                temp.setText("J12", myEmployee.getScheduleYear());

                //Employment Period (7.1 & 7.5 Only)
                temp.setText("AI12", formatDate(JobInfo.getDateHired(), false));
                temp.setText("AO12", formatDate(JobInfo.getDateSeparated(), false));

                myTIN = myEmployee.getTinNumber().toCharArray();
                //Personal Information
                temp.setText("J17", String.valueOf(myTIN, 0, 2));
                temp.setText("N17", String.valueOf(myTIN, 3, 5));
                temp.setText("S17", String.valueOf(myTIN, 6, 8));
                temp.setText("X17", "000");
                temp.setText("B22", myEmployee.getFullname());
                temp.setText("D149", myEmployee.getFullname());
                temp.setText("AE165", myEmployee.getFullname());
                temp.setText("W22", myEmployer.getDistrictCode());
                temp.setText("B27", myEmployee.getAddress());
                temp.setText("W27", myEmployee.getZipCode());
                temp.setText("B42", formatDate(myEmployee.getBirthdate(), true));
                temp.setText("Q42", myEmployee.getContactNumber());
                temp.setText(((myEmployee.getCivilStatus().equalsIgnoreCase("Single")) ? "E47" : "O47"), "x");

                
                //Dependents
                if(Dependents.size() >= 1){
                    temp.setText("B54", Dependents.get(0).getFullname());
                    temp.setText("S54", formatDate(Dependents.get(0).getBirthdate(),true));
                }
                if(Dependents.size() >= 2){
                    temp.setText("B56", Dependents.get(1).getFullname());
                    temp.setText("S56", formatDate(Dependents.get(1).getBirthdate(),true));
                }
                if(Dependents.size() >= 3){
                    temp.setText("B58", Dependents.get(2).getFullname());
                    temp.setText("S58", formatDate(Dependents.get(2).getBirthdate(),true));
                }
                if(Dependents.size() >= 4){
                    temp.setText("B59", Dependents.get(3).getFullname());
                    temp.setText("S59", formatDate(Dependents.get(3).getBirthdate(),true));
                }
                           

                //IF Minimum use this(7.5) "Do not Fill if Above minimum earner"
                if (myEmployee.getSchedule().contains("7.5")) {
                    temp.setText("S63", JobInfo.getMinimumDailyWage());
                    temp.setText("S65", JobInfo.getMinimumMonthlyWage());
                    temp.setText("B68", "x");

                    //Minimum Wager Inputs(7.5)
                    temp.setText("AL65", myIncome.getNonTaxableGrossPay());//Non Taxable GROSS (41)
                    temp.setText("O104", myIncome.getNonTaxableGrossPay());//Non Taxable GROSS (21)
                    temp.setText("O107", myIncome.getNonTaxableGrossPay());//Non Taxable GROSS (22)
                    temp.setText("AL22", myIncome.getNonTaxableBasicPay());//BASIC (32)
                    temp.setText("AL27", myIncome.getNonTaxableHolidayPay());//HOLIDAY (33)
                    temp.setText("AL31", myIncome.getNonTaxableOvertimePay());//OVERTIME (34)
                    temp.setText("AL37", myIncome.getNonTaxableNightDifferentialPay());//NIGHT DIFFERENTIAL (35)
                    temp.setText("AL41", myIncome.getNonTaxableHazardPay());//HAZARDPAY (36)
                    temp.setText("AL46", myIncome.getNonTaxable13thMonthPay());//13TH MONTH PAY (37)
                    temp.setText("AL49", myIncome.getNonTaxableDeminimisBenifits());//DEMINIMIS BENEFITS (38)
                    temp.setText("AL54", myIncome.getNonTaxableGovermentContributions());//GOV'T CONTRIBUTIONS (39)
                    temp.setText("AL59", myIncome.getNonTaxableOtherSalary());//OTHER SALARY (40)
                    
                } else if(myEmployee.getSchedule().contains("7.1") || myEmployee.getSchedule().contains("7.3")){
                    
                    //Non Taxables
                    temp.setText("AL46", myIncome.getNonTaxable13thMonthPay());//13TH MONTH PAY (37)
                    temp.setText("AL49", myIncome.getNonTaxableDeminimisBenifits());//DEMINIMIS BENEFITS (38)
                    temp.setText("AL54", myIncome.getNonTaxableGovermentContributions());//GOV'T CONTRIBUTIONS (39)
                    temp.setText("AL59", myIncome.getNonTaxableOtherSalary());//OTHER SALARY (40)
                    temp.setText("AL65", myIncome.getNonTaxableCompensationIncome());//NON-TAXABLE COMPENSATION INCOME (41)
                    temp.setText("O107", myIncome.getNonTaxableCompensationIncome());//NON-TAXABLE COMPENSATION INCOME (22)
                    Result1= Double.parseDouble(myIncome.getNonTaxableGrossPay())+Double.parseDouble(myIncome.getTaxableGrossPay());
                    temp.setText("O104", String.valueOf(Result1));//NON-TAXABLE GROSS + TAXABLE GROSS (21)
                    
                    temp.setText("AL72", myIncome.getTaxableBasicPay());//TAXABLE BASIC (42)
                    temp.setText("O110", myIncome.getTaxableCompensationIncome());//TAXABLE COMPENSATION INCOME (23)
                    temp.setText("AL141", myIncome.getTaxableCompensationIncome());//TAXABLE COMPENSATION INCOME (55)
                    temp.setText("O116", myIncome.getTaxableGrossPay());//TAXABLE GROSS (25)
                    temp.setText("O119", myIncome.getTaxExcemptionAmount());//TAX EXCEMPTION AMOUNT (26)
                    temp.setText("O122", myIncome.getPremiumHealthExcemptionAmount());//PREMIUM HEALTH EXCEMPTION AMOUNT (27)
                    temp.setText("O125", myIncome.getNetCompensationIncome());//NET COMPENSATION INCOME (28)
                    
                    Result2 = Double.parseDouble(myIncome.getExcessAmountWithheld())-Double.parseDouble(myIncome.getOverWithheldAmount());
                    temp.setText("O128", String.valueOf(Result2));//EXCESS WITHHELD - OVERWITHHELD (29)
                    temp.setText("O132", String.valueOf(Result2));//EXCESS WITHHELD - OVERWITHHELD (30)
                    temp.setText("O141", String.valueOf(Result2));//EXCESS WITHHELD - OVERWITHHELD (31)
                    
                }

                //Employer Information 
                eTIN = myEmployer.getTinNumber().toCharArray();
                temp.setText("J72", String.valueOf(eTIN, 0, 2));
                temp.setText("N72", String.valueOf(eTIN, 3, 5));
                temp.setText("S72", String.valueOf(eTIN, 6, 8));
                temp.setText("W72", "000");
                temp.setText("B77", myEmployer.getName());
                temp.setText("B82", myEmployer.getAddress());
                temp.setText("W82", myEmployer.getZipCode());
                
                temp.setText("D146", AuthorizedPersonel);
                temp.setText("C161", AuthorizedPersonel);

            }
            return false;
        } catch (Exception er) {
            Logger.getLogger(Data2316.class.getName()).log(Level.SEVERE, null, er);
            return false;
        }
    }

    /**
     *
     * @param date - Date to be Formated
     * @param full - if True year will be included ex.(10/10/2015) else ex.(10 / 10)
     * @return
     */
    public String formatDate(String date, boolean full) {
        try {
            if (!date.isEmpty()) {
                TempDate = DateParser.parse(date);
                if (full) {
                    return ((TempDate.getMonth() + 1) + "/" + TempDate.getDate() + "/" + TempDate.getYear());
                } else {
                    return (TempDate.getMonth() + 1) + " / " + TempDate.getDate();
                }
            } else {
                return "";
            }
        } catch (Exception er) {
            Logger.getLogger(Data2316.class.getName()).log(Level.SEVERE, null, er);
            return "";
        }
    }

}

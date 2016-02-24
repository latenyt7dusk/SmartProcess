/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.cl;

import com.smartxls.WorkBook;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import nakpil.income.Income;
import nakpil.work.Employer;
import nakpil.work.JobInformation;

/**
 * @author HERU
 */
public class Data2316 {

    private BIRAccount myEmployee;
    private int SEQ_ID;
    private Income myIncome;
    private JobInformation JobInfo;
    private Employer myEmployer;
    private SimpleDateFormat DateParser = new SimpleDateFormat("yyyy-MM-dd");
    private Date TempDate;
    private char[] myTIN;
    private char[] eTIN;

    /**
     * @param seqID - Indicated Sequence ID of the current Account
     * @param bir - BIR 2316 Account
     */
    public void setBIRAccount(int seqID, BIRAccount bir) {
        this.SEQ_ID = seqID;
        this.myEmployee = bir;
        this.myIncome = myEmployee.getAnnualIncome();
        this.JobInfo = myEmployee.getJobInformation();
        this.myEmployer = JobInfo.getCompany();
    }

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
                temp.setText("B54", "Dependent 1");
                temp.setText("S54", "Dependent 1 Birthdate");
                temp.setText("B56", "Dependent 2");
                temp.setText("S56", "Dependent 2 Birthdate");
                temp.setText("B58", "Dependent 3");
                temp.setText("S58", "Dependent 3 Birthdate");
                temp.setText("B59", "Dependent 4");
                temp.setText("S59", "Dependent 4 Birthdate");

                //IF Minimum use this(7.5) "Do not Fill if Above minimum earner"
                if (myEmployee.getSchedule().contains("7.5")) {
                    temp.setText("S63", JobInfo.getMinimumDailyWage());
                    temp.setText("S65", JobInfo.getMinimumMonthlyWage());
                    temp.setText("B68", "x");

                    //Minimum Wager Inputs(7.5)
                    temp.setText("AL65", "Non Taxable GROSS (41)");
                    temp.setText("O104", "Non Taxable GROSS (21)");
                    temp.setText("O107", "Non Taxable GROSS (22)");
                    temp.setText("AL22", "BASIC (32)");
                    temp.setText("AL27", "HOLIDAY (33)");
                    temp.setText("AL31", "OVERTIME (34)");
                    temp.setText("AL37", "NIGHT DIFFERENTIAL (35)");
                    temp.setText("AL41", "HAZARDPAY (36)");
                    temp.setText("AL46", "13TH MONTH PAY (37)");
                    temp.setText("AL49", "DEMINIMIS BENEFITS (38)");
                    temp.setText("AL54", "GOV'T CONTRIBUTIONS (39)");
                    temp.setText("AL59", "OTHER SALARY (40)");
                    
                } else if(myEmployee.getSchedule().contains("7.1")){
                    
                    //Non Taxables
                    temp.setText("AL46", "NON-TAXABLE 13th Month Pay");
                    temp.setText("AL49", "NON-TAXABLE DEMINIMIS");
                    temp.setText("AL54", "NON-TAXABLE GOV'T CONTRI");
                    temp.setText("AL59", "NON-TAXABLE OTHER SALARY");
                    temp.setText("AL65", "NON-TAXABLE COMPENSATION INCOME");
                    temp.setText("O107", "NON-TAXABLE COMPENSATION INCOME");
                    temp.setText("O104", "NON-TAXABLE GROSS + TAXABLE GROSS");
                    
                    temp.setText("AL72", "TAXABLE BASIC");
                    temp.setText("O110", "TAXABLE COMPENSATION INCOME");
                    
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

            }
            return false;
        } catch (Exception er) {
            Logger.getLogger(Data2316.class.getName()).log(Level.SEVERE, null, er);
            return false;
        }
    }

    public void reset(WorkBook temp) {
        try {

        } catch (Exception er) {
            Logger.getLogger(Data2316.class.getName()).log(Level.SEVERE, null, er);
        }
    }

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

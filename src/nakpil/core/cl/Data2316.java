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
import nakpil.work.Company;
import nakpil.work.JobInformation;

/**
 *
 * @author HERU
 */
public class Data2316{

    private BIRAccount myEmployee;
    private int SEQ_ID;
    private Income myIncome;
    private JobInformation JobInfo;
    private Company myCompany;
    private SimpleDateFormat DateParser = new SimpleDateFormat("yyyy-MM-dd");
    private Date TempDate;
    private char[] myTIN;
    private char[] eTIN;
    
    /**
     * @param seqID - Indicated Sequence ID of the current Account
     * @param bir - BIR 2316 Account
     */
    public void setBIRAccount(int seqID,BIRAccount bir){
        this.SEQ_ID = seqID;
        this.myEmployee = bir;
        this.myIncome = myEmployee.getAnnualIncome();
        this.JobInfo = myEmployee.getJobInformation();
        this.myCompany = JobInfo.getCompany();
    }
    
    public boolean encodeTo(WorkBook temp){
        try{
            if(temp != null){
                
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
                temp.setText("J12", "SMW Year");
                
                //Employment Period (7.1 & 7.5 Only)
                temp.setText("AI12", this.formatDate(JobInfo.getDateHired(),false));
                temp.setText("AO12", this.formatDate(JobInfo.getDateSeparated(),false));
                
                myTIN = myEmployee.getTinNumber().toCharArray();
                //Personal Information
                temp.setText("J17", String.valueOf(myTIN, 0, 2));
                temp.setText("N17", String.valueOf(myTIN, 3, 5));
                temp.setText("S17", String.valueOf(myTIN, 6, 8));
                temp.setText("X17", "000");
                temp.setText("B22", "NAME (4)");
                temp.setText("D149", "NAME (57)");
                temp.setText("AE165", "NAME (59)");
                temp.setText("W22", "RDO CODE");
                temp.setText("B27", "REGISTERED ADDRESS");
                temp.setText("W27", "ZIP CODE");
                temp.setText("B42", "DATE OF BIRTH");
                temp.setText("Q42", "CONTACT NUMBER");
                temp.setText("E47", "X if Single");
                temp.setText("O47", "X if Married");
                
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
                temp.setText("S63", "Min Daily Wage");
                temp.setText("S65", "Min Monthly Wage");
                temp.setText("B68", "x if Minimum Waged");
                
                eTIN = myCompany.getTinNumber().toCharArray();
                //Employer Information 
                temp.setText("J72", "Employer TIN 1");
                temp.setText("N72", "Employer TIN 2");
                temp.setText("S72", "Employer TIN 3");
                temp.setText("W72", "Employer TIN 4");
                temp.setText("B77", "Employer Name");
                temp.setText("B82", "Employer Address");
                temp.setText("W82", "Employer ZIP Code");
                
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
                
                //Above Minimum Wager Inputs
                
            }
            return false;
        }catch(Exception er){
            Logger.getLogger(Data2316.class.getName()).log(Level.SEVERE, null, er);
            return false;
        }
    }
    
    public void reset(WorkBook temp){
        try{
            
        }catch(Exception er){
            Logger.getLogger(Data2316.class.getName()).log(Level.SEVERE, null, er);
        }
    }
    
    
    public String formatDate(String date,boolean full){
        try{
            TempDate = DateParser.parse(date);
            if(full){
                return (TempDate.getMonth()+1)+"/"+TempDate.getDate()+"/"+TempDate.getYear();
            }else{
                return (TempDate.getMonth()+1)+" / "+TempDate.getDate();
            }
        }catch(Exception er){
            Logger.getLogger(Data2316.class.getName()).log(Level.SEVERE, null, er);
            return "";
        }
    }
    
}

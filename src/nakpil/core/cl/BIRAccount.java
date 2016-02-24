/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.cl;

import java.util.ArrayList;
import java.util.List;
import nakpil.government.BIRInformation;
import nakpil.income.Income;
import nakpil.personal.Dependent;
import nakpil.personal.Person;
import nakpil.work.JobInformation;


/**
 * @author HERU
 */
public class BIRAccount extends Person implements BIRInformation {
   
    //For External Object Variables
    private String TinNumber,DistrictCode;
    private JobInformation MyJob;
    private Income AnnualIncome;
    private List<Dependent> dependents = new ArrayList();
    
    //Internal Object Variables
    private String Schedule,Year;
    
    public BIRAccount(Income i,JobInformation j){
        this.AnnualIncome = i;
        this.MyJob = j;
    }
    
    public void setSchedule(String s){
        this.Schedule = s;
    }
    public String getSchedule(){
        return Schedule;
    }
    public void setScheduleYear(String s){
        this.Year = s;
    }
    public String getScheduleYear(){
        return Year;
    }
    
    public void setDependents(List<Dependent> d){
        this.dependents = d;
    }
    
    public List<Dependent> getDependents(){
        return dependents;
    }
    
    public Dependent getDependent(int i){
        return dependents.get(i);
    }
    
    public void setAnnualIncome(Income income){
        this.AnnualIncome = income;
    }
    public Income getAnnualIncome(){
        return AnnualIncome;
    }
    
    public void setJobInformation(JobInformation j){
        this.MyJob = j;
    }
    public JobInformation getJobInformation(){
        return MyJob;
    }

    @Override
    public void setTinNumber(String s) {
        this.TinNumber = s;
    }

    @Override
    public String getTinNumber() {
        return TinNumber;
    }

    @Override
    public void setDistrictCode(String s) {
        this.DistrictCode = s;
    }

    @Override
    public String getDistrictCode() {
        return DistrictCode;
    }
        
}

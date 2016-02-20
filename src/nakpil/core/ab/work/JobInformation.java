/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.ab.work;

/**
 *
 * @author HERU
 */
public class JobInformation implements Job{

    private String Department,Position,MinDailyWage,MinMonthlyWage,MinAnnualWage,DateHired,DateSeparated,EmploymentStatus;
    private Company MyCompany;
    
    public JobInformation(){
        MyCompany = new Company();
    }
    
    public void setCompany(Company c){
        this.MyCompany = c;
    }
    public Company getCompany(){
        return MyCompany;
    }
    
    @Override
    public void setDepartment(String s) {
        this.Department = s;
    }

    @Override
    public String getDepartment() {
        return Department;
    }

    @Override
    public void setPosition(String s) {
        this.Position = s;
    }

    @Override
    public String getPosition() {
        return Position;
    }

    @Override
    public void setMinimumDailyWage(String s) {
        this.MinDailyWage = s;
    }

    @Override
    public String getMinimumDailyWage() {
        return MinDailyWage;
    }

    @Override
    public void setMinimumMonthlyWage(String s) {
        this.MinMonthlyWage = s;
    }

    @Override
    public String getMinimumMonthlyWage() {
        return MinMonthlyWage;
    }

    @Override
    public void setMinimumAnnualWage(String s) {
        this.MinAnnualWage = s;
    }

    @Override
    public String getMinimumAnnualWage() {
        return MinAnnualWage;
    }

    @Override
    public void setDateHired(String s) {
        this.DateHired = s;
    }

    @Override
    public String getDateHired() {
        return DateHired;
    }

    @Override
    public void setDateSaparated(String s) {
        this.DateSeparated = s;
    }

    @Override
    public String getDateSeparated() {
        return DateSeparated;
    }

    @Override
    public void setEmploymentStatus(String s) {
        this.EmploymentStatus = s;
    }

    @Override
    public String getEmploymentStatus() {
        return EmploymentStatus;
    }
    
    
}

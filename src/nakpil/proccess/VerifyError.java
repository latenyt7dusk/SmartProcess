/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.proccess;

/**
 *
 * @author HERU
 */
public class VerifyError {
    
    private String SName,FName,MName;
    private String Row,Sched,Tin,SSS;
    private String V1 = "";
    private String V2 = "";
    private String V3 = "";
    public void setSurname(String s){
        this.SName = s;
    }
    public String getSurname(){
        return SName;
    }
    public void setFirstname(String s){
        this.FName = s;
    }
    public String getFirstname(){
        return FName;
    }
    public void setMidlename(String s){
        this.MName = s;
    }
    public String getMidlename(){
        return MName;
    }
    public void setRowIndex(String s){
        this.Row = s;
    }
    public String getRow(){
        return Row;
    }
    public void setSchedule(String s){
        this.Sched = s;
    }
    public String getSchedule(){
        return Sched;
    }
    public void setTin(String s){
        this.Tin = s;
    }
    public String getTin(){
        return Tin;
    }
    public void setSSS(String s){
        this.SSS = s;
    }
    public String getSSS(){
        return SSS;
    }
    public void addVerificationError_1(String s){
        this.V1 = V1.concat(s+" ");
    }
    public String getVerificationError_1(){
        return V1;
    }
    public void addVerificationError_2(String s){
        this.V2 = V2.concat(s+" ");
    }
    public String getVerificationError_2(){
        return V2;
    }
    public void addVerificationError_3(String s){
        this.V3 = V3.concat(s+" ");
    }
    public String getVerificationError_3(){
        return V3;
    }
    
    
}

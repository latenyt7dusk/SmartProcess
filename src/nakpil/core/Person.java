/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core;

/**
 *
 * @author HERU
 */
public abstract class Person {
    
    private String sname,fname,mname;
    private String bdate,sex,status;
    
    public void setSurname(String s){
        this.sname = s;
    }
    public String getSurname(){
        return sname;
    }
    public void setFirstname(String s){
        this.fname = s;
    }
    public String getFirstname(){
        return fname;
    }
    public void setMiddlename(String s){
        this.mname = s;
    }
    public String getMiddlename(){
        return mname;
    }
    public void setBirthdate(String s){
        this.bdate = s;
    }
    public String getBirthdate(){
        return bdate;
    }
    public void setGender(String s){
        this.sex = s;
    }
    public String getGender(){
        return sex;
    }
    public void setCivilStatus(String s){
        this.status = s;
    }
    public String getCivilStatus(){
        return status;
    }
}

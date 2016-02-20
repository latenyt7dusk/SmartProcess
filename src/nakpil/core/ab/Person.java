/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.ab;


/**
 *
 * @author HERU
 */
public class Person {
    
    public static final String SURNAME = "SURNAME";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String MIDDLENAME = "MIDDLENAME";
    public static final String BIRTHDATE = "BIRTHDATE";
    public static final String GENDER = "GENDER";
    public static final String CIVILSTATUS = "CIVILSTATUS";
    public static final String ADDRESS = "ADDRESS";
    public static final String CONTACT = "CONTACT";
    public static final String EMAIL = "EMAIL";
    
    private String sname,fname,mname;
    private String bdate,sex,status;
    private String address,contact,email;
    
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
    public void setAddress(String s){
        this.address = s;
    }
    public String getAddress(){
        return address;
    }
    public void setContactNumber(String s){
        this.contact = s;
    }
    public String getContactNumber(){
        return contact;
    }
    public void setEmail(String s){
        this.email = s;
    }
    public String getEmail(){
        return email;
    }
}

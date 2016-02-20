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
public abstract interface Business {
        
    public abstract void setName(String s);
    public abstract String getName();
    
    public abstract void setBusinessLine(String s);
    public abstract String getBusinessLine();
    
    public abstract void setClassification(String s);
    public abstract String getClassification();
    
    public abstract void setCategory(String s);
    public abstract String getCategory();

}

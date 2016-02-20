/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.ab.phil.government;

/**
 *
 * @author HERU
 */
public abstract interface PHICInformation {
    
    public static final String PHIC_NUMBER = "PHIC_NUMBER";
    
    public abstract void setPhilhealthNumber(String s);
    public abstract String getPhilhealthNumber();
    
}

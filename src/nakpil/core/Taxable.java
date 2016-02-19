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
public abstract interface Taxable {
    
    public abstract void setTaxableAnualGross(String s);
    public abstract String getTaxableAnualGross();
    
    public abstract void setTaxableAnualBasic(String s);
    public abstract String getTaxableAnualBasic();
    
}

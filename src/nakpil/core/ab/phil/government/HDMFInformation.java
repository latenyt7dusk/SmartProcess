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
public abstract interface HDMFInformation {
    
    public static final String HDMF_Number = "HDMF_NUMBER";
    
    public abstract void setHDMFNumber(String s);
    public abstract String getHDMFNumber();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.cl;

import nakpil.core.ab.*;
import nakpil.core.ab.income.Income;
import nakpil.core.ab.phil.government.BIRInformation;
import nakpil.core.ab.phil.government.SSSInformation;
/**
 *
 * @author HERU
 */
public class BIRAccount extends Person implements BIRInformation,SSSInformation{
    
    private Income AnnualIncome = new Income();
    
    
    public void setAnnualIncome(Income income){
        this.AnnualIncome = income;
    }
    public Income getAnnualIncome(){
        return AnnualIncome;
    }

    @Override
    public void setTinNumber(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTinNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSSSNumber(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSSSNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

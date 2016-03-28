/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.core.cl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kelvin
 */
public class DBValidation {
    
    private List<String> Errs;
    private final DefaultTableModel TB;
    public static final String DRIVER = "org.h2.Driver";
    private final String SOURCE,USERNAME,PASSWORD;
    private Connection CONNECTION;
    private PreparedStatement PRESTATEMENT;
    private ResultSet RESULT;
    
    public DBValidation(DefaultTableModel tb,String sourcedb,String userdb,String passdb){
        this.TB = tb;
        this.SOURCE = sourcedb;
        this.USERNAME = userdb;
        this.PASSWORD = passdb;
    }
    
    public boolean checkConnection() throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USERNAME, PASSWORD);
                return CONNECTION.isValid(1000);
            }
            return false;
        } catch (ClassNotFoundException | SQLException er) {
            Logger.getLogger(DBValidation.class.getName()).log(Level.SEVERE, null, er);
            return false;
        } finally {
            if (CONNECTION != null) {
                CONNECTION.close();
                CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public List<String> validate(){
        try{
            Errs = new ArrayList();
            
            return Errs;
        }catch(Exception er){
            Logger.getLogger(DBValidation.class.getName()).log(Level.SEVERE, null, er);
            return Errs;
        }
    }
    
}

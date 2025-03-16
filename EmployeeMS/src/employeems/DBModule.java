/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeems;
import java.sql.*;
/**
 *
 * @author Muhammad Salman
 */
public class DBModule {
    static{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(Exception e){
            
        }
    }
    static Connection getConnection() throws Exception{
        try{
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EMS", "EMS123");
            return c;
        }catch(Exception e){
            throw e;
        }
    }
}

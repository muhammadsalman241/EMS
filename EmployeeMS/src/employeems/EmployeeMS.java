/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeems;

import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Muhammad Salman
 */
public class EmployeeMS {

    /**
     * @param args the command line arguments
     */
    EmployeeMS(){
        try{
            c = DBModule.getConnection();
            st = c.createStatement();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, 
                        "Cannot Connect to Database\n System Exiting!",
                        "Connection Erorr",
                        JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
    }
    boolean hireEmployee(String name, int salary, int age, String doa){
        try{   
            String query;
            query = "INSERT INTO EMPLOYEE(E_ID, E_NAME, E_AGE, E_SALARY, DATE_OF_APPOINTMENT) " +
                    " VALUES ( " +
                    "(SELECT MAX(e_id)+1 FROM employee) ," +
                    "\'" + name + " \' ,"+
                    age + "," +
                    salary + "," +
                    "\'" + doa + "\'" +
                    " )";
            st.executeQuery(query);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    String[] fireEmployee(int E_ID){
        try{
            String query;
            query = "SELECT * FROM employee WHERE E_ID = " + E_ID;
            ResultSet r = st.executeQuery(query);
            if(r.next()){
                String name = r.getString("E_NAME");
                int age = r.getInt("E_AGE");
                int salary = r.getInt("E_SALARY");
                Date doa = r.getDate("DATE_OF_APPOINTMENT");
                String s[] = {name, age + "", salary + "", doa.toString()};
                return s;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    boolean fireEmployee(String E_ID){
        try{
            String query;
            query = "DELETE FROM employee WHERE E_ID = " + E_ID;
            ResultSet r = st.executeQuery(query);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    String[] editEmployee(int E_ID){
        try{
            String query;
            query = "SELECT * FROM employee WHERE E_ID = " + E_ID;
            ResultSet r = st.executeQuery(query);
            if(r.next()){
                String name = r.getString("E_NAME");
                int age = r.getInt("E_AGE");
                int salary = r.getInt("E_SALARY");
                Date doa = r.getDate("DATE_OF_APPOINTMENT");
                String s[] = {name, age + "", salary + "", doa.toString()};
                return s;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    boolean editEmployee(String employee[]){
        try {
            String query;
            query = "UPDATE employee SET E_NAME = \'" + employee[1] + "\'" +
                    ", E_AGE = " + employee[2] +
                    ", E_SALARY = " + employee[3] +
                    " WHERE E_ID = " + employee[0];
            st.executeQuery(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    ResultSet GenerateReport(String rType){
        String query="";
        if(rType.equals("Fetch Employee Record"))
            query = "SELECT * FROM employee";
        else if(rType.equals("Accending Employee Names"))
            query = "SELECT * FROM employee ORDER BY E_NAME ASC";
        else if(rType.equals("Decending Employee Names"))
            query = "SELECT * FROM employee ORDER BY E_NAME DESC";
        else if(rType.equals("Employee with high & low salary"))
            query = "SELECT * FROM employee WHERE E_SALARY = (SELECT MAX(E_SALARY) FROM employee) OR E_SALARY = (SELECT MIN(E_SALARY) FROM employee)";
        else if(rType.equals("Employee with high & low age"))
            query = "SELECT * FROM employee WHERE E_AGE = (SELECT MAX(E_AGE) FROM employee) OR E_AGE = (SELECT MIN(E_AGE) FROM employee)";
        else if(rType.equals("Employees with ASC DOA"))
            query = "SELECT * FROM employee ORDER BY DATE_OF_APPOINTMENT ASC";
        else if(rType.contains("Search Employee With Name"))
            query = "SELECT * FROM employee WHERE E_NAME LIKE \'" + rType.substring(rType.indexOf('>')+1) + "%\'";
        else if(rType.equals("Show Salary Details"))
            query = "SELECT AVG(E_SALARY), MAX(E_SALARY), MIN(E_SALARY), SUM(E_SALARY), COUNT(*) FROM employee";
        else if(rType.equals("Summary on Employee\'s Record"))
            query = "SELECT COUNT(*), MAX(E_SALARY), MIN(E_SALARY), MAX(E_AGE), MIN(E_AGE), MAX(DATE_OF_APPOINTMENT), MIN(DATE_OF_APPOINTMENT) FROM employee";
        
        try {
            return st.executeQuery(query);
        } catch (SQLException ex) {
                Logger.getLogger(EmployeeMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    void closeSystem(){
        try {
            st.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeMS.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    private Connection c;
    private Statement st;
}

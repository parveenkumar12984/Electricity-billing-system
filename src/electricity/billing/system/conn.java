/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity.billing.system;

import java.sql.*;  

/**
 *
 * @author Parveen
 */
public class conn {
    Connection c;
    Statement s;
    public conn(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");           
            c =DriverManager.getConnection("jdbc:mysql:///electricity","root","root");   
            //c =DriverManager.getConnection("jdbc:mysql://db4free.net:3306/project12984","root12984","root12984");
            s =c.createStatement();  
            System.out.println("Connected successfully");
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }
    public static void main(String[] args){
       new conn();
    }
}
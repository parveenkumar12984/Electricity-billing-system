/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Parveen
 */
public class customer_details extends JFrame implements ActionListener{
    JTable t1;
    JButton b1,b2;
    JPanel p1;
    String x[] = {"Emp Name","Meter No","Address","State","City","Email","Phone"};
    String y[][] = new String[30][8];
    int i=0, j=0;
    customer_details(){
        super("Customer Details");
        setSize(1350,650);
        setLocation(10,10);
        b2=new JButton("Click here for Search the customer.");
        p1=new JPanel();       
        p1.add(b2);
        add(p1,"North");
       
        try{
            conn c1  = new conn();
            String s1 = "select * from emp";
            ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("name");
                y[i][j++]=rs.getString("meter");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("state");
                y[i][j++]=rs.getString("city");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("phone");
                i++;
                j=0;
            }
            t1 = new JTable(y,x);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        b1 = new JButton("Print");
        add(b1,"South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }
        
    public void actionPerformed(ActionEvent ae){
        String str=ae.getActionCommand();
        if(str.equals("Print")){
            try{
            t1.print();
            }catch(Exception e){}
        }
        else
        {
            try{        
            search se=new search();                    
            se.setVisible(true); 
            this.setVisible(false);           
        }catch(Exception e){
            e.printStackTrace();
        } 
          
        }
    }
    
    public static void main(String[] args){
        new customer_details().setVisible(true);
    }    
}

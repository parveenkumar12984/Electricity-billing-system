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
public class LastBill extends JFrame implements ActionListener{
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1;
    JPanel p1;
    LastBill(){
        setSize(600,650);
        setLayout(new BorderLayout());
        
        p1 = new JPanel();
        
        l1 = new JLabel("Generate Bill");
        
        c1 = new Choice();

        try{
            conn c  = new conn();
            String s1 = "select * from emp";
            ResultSet rs  = c.s.executeQuery(s1);
            while(rs.next()){
                c1.addItem(rs.getString("meter"));
            }            
        }catch(Exception e){
            e.printStackTrace();
        } 

        
        t1 = new JTextArea(50,15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));
        
        b1 = new JButton("Generate Bill");
        
        p1.add(l1);
        p1.add(c1);
        add(p1,"North");
        
        add(jsp,"Center");
        add(b1,"South");
        
        b1.addActionListener(this);
        
        setLocation(350,20);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            conn c = new conn();

            ResultSet rs = c.s.executeQuery("select * from emp where meter="+c1.getSelectedItem());
            t1.setEditable(false);
            t1.setText(" ");
            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("name"));
                t1.append("\n    Meter Number:  "+rs.getString("meter"));
                t1.append("\n    Address:            "+rs.getString("address"));
                t1.append("\n    State:                 "+rs.getString("state"));
                t1.append("\n    City:                   "+rs.getString("city"));
                t1.append("\n    Email:                "+rs.getString("email"));
                t1.append("\n    Phone Number  "+rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }

            t1.append("Details of the Last Bills:\n\n");
            
            rs = c.s.executeQuery("select * from bill where meter="+c1.getSelectedItem());
            
            while(rs.next()){
                t1.append("       "+ rs.getString("month") + "           " +rs.getString("amt") + "\n");
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new LastBill().setVisible(true);
    }
    
}

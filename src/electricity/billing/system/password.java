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
public class password extends Frame implements ActionListener{
    JLabel l1,l2,l3,l4;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2;
    JPanel p1,p2,p3,p4;
    password(){
        super("Login Page");
        
        l1 = new JLabel("Username");
        tf1 = new JTextField(15);
        l2 = new JLabel("Password");
        pf2 = new JPasswordField(15);
        
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/login.png"));
        Image i1 = ic1.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        b1 = new JButton("Login", new ImageIcon(i1));
        
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/cancel.jpg"));
        Image i2 = ic2.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        b2 = new JButton("Cancel",new ImageIcon(i2));   
        
        b1.addActionListener(this);
        b2.addActionListener(this);
       
        setLayout(new BorderLayout());
        
                
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        
        add(p1,BorderLayout.WEST);
     
        p2.add(l1);
        p2.add(tf1);
        p2.add(l2);
        p2.add(pf2);
        add(p2,BorderLayout.CENTER);
        
        p3.add(b1);
        p3.add(b2);
        add(p3,BorderLayout.SOUTH);
        
        p1.setBackground(Color.WHITE);
        p2.setBackground(Color.WHITE);
        p3.setBackground(Color.ORANGE);
        
        tf1.setEditable(false);
        setSize(440,250);
        setLocation(500,200);
        setVisible(true);
        
    }
    
     @Override
    public void actionPerformed(ActionEvent ae){
        String str=ae.getActionCommand();
            
        if(str.equals("Login")){
        try{        
            conn c1 = new conn();
            String a  = tf1.getText();
            String b  = pf2.getText();
            String q  = "select * from login where username = '"+a+"' and password = '"+b+"'";
            ResultSet rs = c1.s.executeQuery(q);
            if(rs.next()){
                new Project().setVisible(true);
                this.setVisible(false);
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid password");
                pf2.setText(null);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error: "+e);
        }
        }
        else{
           System.exit(0);
        }
    }
    public static void main(String[] args){
        new password().setVisible(true);
    }
}


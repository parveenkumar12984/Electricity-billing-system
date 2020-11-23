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
public class login extends JFrame implements ActionListener{
    JLabel l1,l3,l4,l5;
    JTextField tf1;
    JButton b1,b2;
    JPanel p1,p2,p3;
    JTextArea ta1;
    login(){
        super("Login Page");
        
        l1 = new JLabel("Username");
        tf1 = new JTextField(15);
        
        ta1=new JTextArea("\n\nPlzz ensure that your PC is\n connected to Internet Connection");
        ta1.setForeground(Color.BLUE);
        ta1.setFont(new Font("Aril Black",Font.BOLD,17));
        
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/login.png"));
        Image i1 = ic1.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        b1 = new JButton("Login", new ImageIcon(i1));
        
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/cancel.jpg"));
        Image i2 = ic2.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        b2 = new JButton("Cancel",new ImageIcon(i2));   
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/pop.png"));
        Image i3 = ic3.getImage().getScaledInstance(128, 128,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        l3 = new JLabel(icc3);
        
        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/parveen.jpg"));
        Image i4 = ic4.getImage().getScaledInstance(128, 128,Image.SCALE_DEFAULT);
        ImageIcon icc4 = new ImageIcon(i4);
        l4 = new JLabel(icc4);
        
        ImageIcon ic5 = new ImageIcon(ClassLoader.getSystemResource("electricity/billing/system/icon/pardeep.jpg"));
        Image i5 = ic5.getImage().getScaledInstance(128, 128,Image.SCALE_DEFAULT);
        ImageIcon icc5 = new ImageIcon(i5);
        l5 = new JLabel(icc5);
               
        setLayout(new BorderLayout());        
                
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        
        p1.add(l3);
        add(p1,BorderLayout.WEST);
     
        p2.add(l1);
        p2.add(tf1);
        p2.add(ta1);
        add(p2,BorderLayout.CENTER);
        
        p3.add(b1);
        p3.add(b2);
        add(p3,BorderLayout.SOUTH);
        
        p1.setBackground(Color.WHITE);
        p2.setBackground(Color.WHITE);
        p3.setBackground(Color.ORANGE);        
       
        setSize(440,250);
        setLocation(500,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               
    }
    
     @Override
    public void actionPerformed(ActionEvent ae){
        String str=ae.getActionCommand();
            
        if(str.equals("Login")){
        try{        
            conn c1 = new conn();
            String a  = tf1.getText();
            String q  = "select * from login where username = '"+a+"'";
            ResultSet rs = c1.s.executeQuery(q);
            if(rs.next()){
               password p= new password();
               if(a.equals("parveen")||a.equals("Parveen")){
                   p.p1.add(l4);
               }
               else{
                   p.p1.add(l5);
               }
               p.tf1.setText(a);
               p.setVisible(true);
               this.setVisible(false);
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid user name");
                tf1.setText(null);
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
        new login().setVisible(true);
    }
}

